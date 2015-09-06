package com.slingmodels.core

import com.day.cq.search.PredicateGroup
import com.day.cq.search.Query
import com.day.cq.search.QueryBuilder
import com.day.cq.search.result.Hit
import groovy.util.logging.Slf4j
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.models.annotations.Default
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Source
import org.apache.sling.models.annotations.Via

import javax.inject.Inject
import javax.jcr.Session

@Slf4j
@Model(adaptables = SlingHttpServletRequest.class)

class RecentArticle {

    LinkedHashMap articlePageMap = [:]
    javax.jcr.Node resultPage = null

    private SlingHttpServletRequest request

    public RecentArticle(SlingHttpServletRequest request) {
        this.request = request
    }

    @Inject
    @Via("resource")
    @Default(values = "5")
    private String maxResults

    public String getMaxResults() {
        return maxResults
    }

    // Inject OSGi services
    @Inject
    @Source("osgi-services")
    private QueryBuilder queryBuilder

    public Map getArticlePages() {
        int maxRes = maxResults as Integer
        int count = 0
        Map map = ["path": "/content/geometrixx-media", "orderby": "@jcr:content/jcr:created", "orderby.sort": "desc", "type": "cq:Page"]
        Session session = request.resourceResolver.adaptTo(Session)
        Query query = queryBuilder.createQuery(PredicateGroup.create(map), session)
        List<Hit> resultList = query.result?.hits
        int maximumAllowedResults = maxRes <= resultList.size() ? maxRes : resultList.size()

        resultList?.eachWithIndex { Hit hit, index ->
            try {
                if (count < maximumAllowedResults) {
                    resultPage = (javax.jcr.Node) hit.node
                    articlePageMap[resultPage.path] = request.resourceResolver.resolve(resultPage.path).name
                    count++
                }
            }
            catch (Exception e) {
                log.error("Error: " + e)
            }
        }
        return articlePageMap
    }

}


