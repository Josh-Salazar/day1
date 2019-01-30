<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hubbub&trade; &raquo; Login</title>
        <style type="text/css">
            .flash {color:red;}
        </style>
    </head>
    <body>
        <p><center><img src="images/hubbub_logo.png"/></center></p>
        <h1>Hubbub&trade; Log-in</h1>
        <a href="main?action=timeline">Back to the Timeline</a> |
        <a href="main?action=join">I'd Like to Sign Up</a>
        <h2>Get Hackin':</h2>
        <c:if test="${not empty flash}">
        <h2 class="flash">${flash}</h2>
        </c:if>
        <form method="POST" action="main">
            <input type="hidden" name="action" value="login"/>
            Username: <input type="text" name="username" required/><br/>
            Password: <input type="password" name="password" required/><br/>
            <input type="submit" value="Hack Away!"/>
        </form>
    </body>
</html>
