<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Poll</title>
</head>
<body>
    <h2>Poll List</h2>

        <form action="/poll/search" method="POST">
			    <input type="text" placeholder="Search By Poll Name" name="theSearchName" value="${theSearchName}">
			    <input type="submit" value="Search"/>
        </form>

    <table border = "1">
        <thead>
        <tr>
        <th>S No. </th>
            <th>Poll Name
       	        &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=pollName,desc"> Desc </a>
                &nbsp &nbsp <a href= "/search?page=0&size=5&theSearchName=${theSearchName}&sort=pollName"> Asc </a>
            </th>
            <th>Add Options</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
   			<c:set var="index" value="${page * 5 + 1}" />

        <c:forEach items="${polls}" var="poll">
        <c:url var="updateLink" value="/poll/update">
            <c:param name="pollId" value="${poll.pollId}"></c:param>
        </c:url>

            <tr>
					<td>${index}</td>
                <td>${poll.pollName}</td>
                <td><a href="/pollOption/add/${poll.pollId}">Add Options</a> </td>
                <td><a href="${updateLink}">Update</a></td>
                <td>
                    <a href="/poll/deletePoll/${poll.pollId}">Delete</a>
                </td>
				<c:set var="index" value="${index + 1}" />

            </tr>
        </c:forEach>
        </tbody>
    </table>

		        <br><br>
                	<c:choose>
                        <c:when test="${totalPage == 0}">
                            No Record Found
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="0" end="${totalPage-1}" varStatus="loop">
                                    &nbsp &nbsp<a href="/search?page=${loop.index}&size=5&theSearchName=${theSearchName}&sort=${sortBy}">${loop.index + 1}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>


<br><br>
    <a href="/poll/add">Add More Polls</a>


</body>
</html>

