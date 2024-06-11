<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Poll</title>
</head>
<body>
    <h2>Add Poll</h2>
	<form:form action="/poll/handleForm" modelAttribute="poll" method="POST">
            <label for="pollName">Poll Name:</label>
			<form:input path="pollName" name="pollName" />
			<form:errors path="pollName"/>

			<input type="submit" value="Save"/>
	</form:form>
    <a href="/poll/list">Back</a>
</div>
</body>
</html>

