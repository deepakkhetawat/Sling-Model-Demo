<%@page session="false" %>
<%@page import="com.slingmodels.core.SlingModel" %>
<%@include file="/apps/slingmodels/global.jsp" %>

<c:set var="slingModelJSP" value="<%= resource.adaptTo(SlingModel.class)%>"/>
Designation :     ${slingModelJSP.designationInfo}  <br/>
technology :      ${slingModelJSP.technology}   <br/>
Name :            ${slingModelJSP.name}          <br/>
