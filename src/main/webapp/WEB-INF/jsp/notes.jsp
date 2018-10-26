<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp"%>

<h1>Notes</h1>

<form class="form-inline my-2 my-lg-0 float-right" method="get" action="${pageContext.request.contextPath}/notes">
    <div class="form-group mb-2">
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" value="${param.search}" name="search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </div>
</form>

<table class="table table-hover table-striped">
    <thead>
    <tr>
        <th scope="col" width="70%">Description</th>
        <th scope="col" width="5%">Avatar</th>
        <th scope="col" width="20%">Created date</th>
        <th scope="col" width="5%"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${notes}" var="note" varStatus="сounter">
        <tr>
            <td class="align-middle">
                <c:out value="${note.description}" />
            </td>
            <td class="align-middle">
                <img class="img-thumbnail" style="width: 40px; height: 40px;" src="data:image/svg+xml;base64, ${note.avatarAsBase64}" />
            </td>
            <td class="align-middle">
                <fmt:formatDate value="${note.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
            <td class="align-middle">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/notes/${note.id}" role="button">Edit</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/notes/add" class="btn btn-primary" role="button" aria-pressed="true">Add new note</a>

<%@ include file="footer.jsp"%>
