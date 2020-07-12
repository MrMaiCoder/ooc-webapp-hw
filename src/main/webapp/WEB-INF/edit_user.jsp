<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Edit Page</title>
<div align="center">
    <body>
    <h2>
        Username: ${username}
    </h2>
    <h2>
        First name: ${firstName}
    </h2>
    <h2>
        Last name: ${lastName}
    </h2>
    <p>
        ${error}
    </p>
    <form method="post">
        <input type="text" placeholder="Enter new username" name="newUsername" /><br>
        <input type="password" placeholder="Enter password" name="password" required/><br>
        <input type="password" placeholder="Confirm password" name="confirmPassword" required/><br>
        <input type="password" placeholder="Enter new password" name="newPassword" ><br>
        <input type="text" placeholder="Enter new first name" name="firstName" /><br>
        <input type="text" placeholder="Enter new last name" name="lastName" /><br>
        <input type="submit" value="Edit user" name="editUser" required/>
    </form>
    <p>
        ${usernameMessage}
    </p>
    <p>
        ${passwordMessage}
    </p>
    <p>
        ${firstNameMessage}
    </p>
    <p>
        ${lastNameMessage}
    </p>
    <form method="post">
        <input type="submit" value="Back" name="back" />
        <input type="submit" value="Log Out" name="logout" />
    </form>
    </body>
</div>
</html>