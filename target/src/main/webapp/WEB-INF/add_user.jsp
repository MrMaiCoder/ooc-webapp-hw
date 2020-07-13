<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Add User Page</title>
<div align="center">
<body>
<h2>
    Hello ${username}
</h2>
<form method="post">
    <input type="text" placeholder="Enter username" name="username" required /><br>
    <input type="password" placeholder="Enter password" name="password" required/><br>
    <input type="password" placeholder="Confirm password" name="confirmPassword" required><br>
    <input type="text" placeholder="Enter first name" name="firstName" /><br>
    <input type="text" placeholder="Enter last name" name="lastName" /><br>
    <input type="submit" value="Add user" name="addUser" required/>
</form>
<p>
    ${message}
</p>

<form method="post">
    <input type="submit" value="Back" name="back" />
</form>

</form>
</body>
</div>
</html>