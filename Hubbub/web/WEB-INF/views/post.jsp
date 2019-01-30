<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hubbub&trade; &raquo; Post</title>
        <style>.flash {color:red;}</style>
    </head>
    <body>
        <p><center><img src="images/hubbub_logo.png"/></center></p>
        <h1>What Are You Hackin' on Today?</h1>
        <a href="main?action=timeline">Back to the Timeline</a> |
        <a href="main?action=wall">Go to My Wall</a> |
        <a href="main?action=logout">Log Me Out</a>
        <c:if test="${not empty flash}">
        <h2 class="flash">${flash}</h2>
        </c:if>
        <form method="POST" action="main">
            <input type="hidden" name="action" value="post"/>
            <textarea cols="50" rows="6" name="content">${content}</textarea><br/>
            <input type="submit" value="Tell the World"/>
        </form>
    </body>
</html>
