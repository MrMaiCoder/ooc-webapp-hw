
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Home Page</title>
<div align="center">
    <body>
    <h1> Home Page </h1>
    <h2>Welcome, ${username}</h2>
    <h2>${firstName} ${lastName}</h2>
    <form method="post">
        <input type="submit" value="User List" name="user_list" />
        <input type="submit" value="Log Out" name="logout" />
    </form>

    </body>
</div>
</html>