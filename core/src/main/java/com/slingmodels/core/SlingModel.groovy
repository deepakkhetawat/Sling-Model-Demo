package com.slingmodels.core
import groovy.util.logging.Slf4j
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Default
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Optional

import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Slf4j
@Model(adaptables = Resource.class)
class SlingModel {
    //@Inject marks a field or method as injectable
    @Inject
    private String name

    // A default value can be provided (for Strings & primitives)
    @Inject
    @Default(values = "AEM")
    private String technology

    // Inject a property whose name does NOT match the Model field name  i.e The @Named annotation allows to naming the injected variable differently .
    @Inject
    @Named("title")
    private String designationTitle

    // @Injected fields/methods are assumed to be required. To mark them as optional, use @Optional:
    @Inject
    @Optional
    private String otherName

    private String value

    public String getTechnology() {
        return technology
    }

    public String getName() {
        return name
    }

    public String getDesignationTitle() {
        return designationTitle
    }

    public String getOtherName() {
        return otherName
    }

    @PostConstruct

    public String getDesignationInfo() {
        value = "My Designation is " + designationTitle
        return value
    }

}
