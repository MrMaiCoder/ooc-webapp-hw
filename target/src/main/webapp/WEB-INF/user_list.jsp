<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>User List Page</title>
<div align="center">
    <body>
        <h2>
            Hello ${username}
        </h2>

        <table border="1">
            <tr>
                <th>Username</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Actions</th>
            </tr>
            <c:forEach items="${usersList}" var="user">
                <tbody style="vertical-align: center">
                <tr>
                    <td>
                            ${user.getUsername()}
                    </td>
                    <td>
                            ${user.getFirstName()}
                    </td>
                    <td>
                            ${user.getLastName()}
                    </td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="usernameString" value="${user.getUsername()}" />
                            <c:choose>
                                <c:when test="${user.getUsername()!=username}">
                                    <input type="submit" name="removeUser" value="remove" onclick="{return confirm('Are you sure you want to remove this user?')}"/>
                                </c:when>
                            </c:choose>
                            <input type="submit" name="editUserPage" value="edit" />
                        </form>
                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <form method="post">
            <input type="submit" value="Add" name="addUser" />
            <input type="submit" value="Back" name="back" />
            <input type="submit" value="Log Out" name="logout" />
        </form>
    </body>
</div>
</html>