<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hubbub&trade; &raquo; Timeline</title>
    </head>
    <body>
    	<p><center><img src="images/hubbub_logo.png"/></center></p>
        <h1>
            Welcome to Hubbub&trade;<c:if test="${not empty user}">, ${user}</c:if>!
        </h1>
        <c:choose>
            <c:when test="${not empty user}">
                <a href="main?action=post">Post Yer Hack Status</a> |
                <a href="main?action=wall&for=${user}">Show Me My Wall</a> |
                <a href="main?action=logout">Log Me Out</a>
            </c:when>
            <c:otherwise>
                <a href="main?action=login">Log Me In!</a> |
                <a href="main?action=join">Sign Me Up!</a>
            </c:otherwise>
        </c:choose>
        <h2>Here's what our users are hackin' on:</h2>
        <ul>
        <c:forEach var="post" items="${posts}">
            <li>
                Posted by
                <span class="author" title="${post.author.joinDate}">
                    <c:choose>
                        <c:when test="${not empty user}">
                            <a href="main?action=profile&for=${post.author}">
                                ${post.author}
                            </a>
                        </c:when>
                        <c:otherwise>
                            ${post.author}
                        </c:otherwise>
                    </c:choose>
                </span>
                <span class="postdate">${post.postDate}</span>
                <p class="content">${post.content}</p>
            </li>
        </c:forEach>
        </ul>
        <a href="main?action=timeline&page=0&size=${size}">Top of Timeline</a>
        <c:if test="${lastPage ge page}">
        | <a href="main?action=timeline&page=${page + 1}&size=${size}">Next Page</a>
        </c:if>
        <c:if test="${page gt 0}">
        | <a href="main?action=timeline&page=${page - 1}&size=${size}">Previous Page</a>
        </c:if>
    </body>
</html>
