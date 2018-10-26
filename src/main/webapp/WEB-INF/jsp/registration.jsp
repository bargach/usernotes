<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>usernotes</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://getbootstrap.com/docs/4.1/examples/sign-in/signin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>

<body class="text-center">
<form class="form-signin" action="${pageContext.request.contextPath}/registration" method="post">
    <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>
    <label for="username" class="sr-only">Username</label>
    <input type="text" id="username" class="form-control" placeholder="Username" required autofocus name="username">
    <label for="name" class="sr-only">Name</label>
    <input type="text" id="name" class="form-control" placeholder="Name" required autofocus name="name">
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" class="form-control" placeholder="Password" required name="password">

    <c:if test="${param.error}">
        <div class="alert alert-danger" role="alert">
            User already exist
        </div>
    </c:if>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    <hr>
    <a class="btn btn-lg btn-info btn-block" href="${pageContext.request.contextPath}/login" role="button">Return to login</a>
    <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
</form>
</body>

</html>
