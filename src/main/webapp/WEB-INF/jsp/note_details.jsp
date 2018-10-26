<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp"%>

<h1>Note</h1>

<div class="card" style="width: 18rem; margin: 0 auto;">
    <img class="card-img-top" src="data:image/svg+xml;base64, ${note.avatarAsBase64}">
    <div class="card-body">
        <h5 class="card-title">Note</h5>
        <p class="card-text">${note.description}</p>
        <p class="card-text"><fmt:formatDate value="${note.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
        <form action="${pageContext.request.contextPath}/notes/${note.id}/delete" method="post">
            <a class="btn btn-info" href="${pageContext.request.contextPath}/notes" role="button" >Back</a>
            <button type="submit" class="btn btn-danger pull-right">Delete note</button>
        </form>
    </div>
</div>

<%@ include file="footer.jsp"%>
