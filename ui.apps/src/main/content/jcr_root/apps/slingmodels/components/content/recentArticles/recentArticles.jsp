<%@page session="false" %>
<%@page import="com.slingmodels.core.RecentArticle" %>
<%@include file="/libs/foundation/global.jsp" %>

<c:set var ="recentArticle" value="<%= slingRequest.adaptTo(RecentArticle.class)%>" />
<c:set var ="recentArticleList" value="${recentArticle.articlePages}" />
<ul>
    <c:forEach var="results" items="${recentArticleList}" >
        <li> <a href="${results.key}.html">${results.value} </a> </li> <br/>
    </c:forEach>
</ul>

