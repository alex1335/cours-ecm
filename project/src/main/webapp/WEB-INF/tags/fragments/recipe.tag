<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mataglib" uri="/WEB-INF/tld/util.tld" %>


<%@ attribute name="recipe" required="true" rtexprvalue="true" type="fr.cmm.domain.Recipe"%>

<div class="row">
    <div class="col-xs-12 col-sm-4">
        <div class="thumbnail">
            <img src="/image/${recipe.imageId}" alt="${fn:escapeXml(recipe.title)}">
        </div>
    </div>
    <div class="col-xs-12 col-sm-8">
        <h1>${fn:escapeXml(recipe.title)}</h1>
        <p>${fn:escapeXml(recipe.intro)}</p>

        <c:if test="${not empty recipe.tags}">
            <ul>
                <c:forEach var="tag" items="${recipe.tags}">
                    <span class="label label-primary">${fn:escapeXml(tag)}</span>
                </c:forEach>
            </ul>
        </c:if>

        <p> <fmt:formatDate value="${recipe.date}" pattern="dd/MM/yyyy" /> </p>
        <c:if test="${not empty recipe.ingredients}">
            <ul>
                <c:forEach var="ingredient" items="${recipe.ingredients}">
                    <li>${fn:escapeXml(ingredient.name)} : ${fn:escapeXml(ingredient.quantity)} ${fn:escapeXml(ingredient.unit)}</li>
                </c:forEach>
            </ul>
        </c:if>
        <p>${fn:escapeXml(recipe.text)}</p>
    </div>
</div>