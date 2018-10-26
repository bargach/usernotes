<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/notes/add" method="post">
    <div class="form-group">
        <label for="description">Description</label>
        <textarea required class="form-control" name="description" id="description" rows="3"></textarea>
        <br>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/notes" role="button">Back</a>
        <button type="submit" class="btn btn-primary">Add</button>
    </div>
</form>

<%@ include file="footer.jsp"%>
