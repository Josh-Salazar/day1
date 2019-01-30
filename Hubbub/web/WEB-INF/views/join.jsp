<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hubbub&trade; &raquo; Join</title>
        <style>
            .flash {color:red;}
        </style>
    </head>
    <body>
        <p><center><img src="images/hubbub_logo.png"/></center></p>
        <h1>Join Our Community</h1>
        <a href="main?action=login">I'm a Member</a> |
        <a href="main?action=timeline">Back to the Timeline</a>
        <c:if test="${not empty flash}">
            <h2 class="flash">${flash}</h2>
        </c:if>
            <form method="POST" action="main">
                <input type="hidden" name="action" value="join"/>
                Choose a Username: <input type="text" name="username" required placeholder="6-12 characters"/><br/>
                Pick a Strong Password: <input type="password" name="password1" required placeholder="8-20 characters"/><br/>
                Repeat That Password: <input type="password" name="password2" required placeholder="same as above"/><br/>
                <input type="submit" value="Let's Get Hackin'"/>
            </form>
    </body>
</html>
