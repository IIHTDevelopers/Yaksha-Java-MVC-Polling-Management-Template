<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Option</title>
</head>
<body>
    <h2>Add Option for ${poll.pollName}</h2>
    <table border = "1">
        <thead>
        <tr>
            <th>Option Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${options}" var="option">
            <tr>
                <td>${option.optionName}</td>
                <td>
                    <a href="/pollOption/deletePollOption/${option.pollId}/${option.optionId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br>
    <form:form action="/pollOption/handleForm" modelAttribute="option" method="post">
            <label for="optionName">Add Option</label>
			<form:input path="optionName" name="optionName" />
			<form:errors path="optionName"/>
             <input type="hidden" required name="pollId" value="${poll.pollId}">
			<input type="submit" value="Save"/>
        <a href="/poll/list">Back</a>
    </form:form>

</body>
</html>
