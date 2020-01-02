<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	<style>
	    .text-center {
		text-align: center;
	    }

	    label {
		width: 7em;
		font-weight: bold;
		float: left;
		margin-bottom: 0.5em;
	    }

	    input[type="text"],input[type="email"] {
		width: 15em;
		margin-left: 0.25em;
		margin-bottom: 0.5em;
	    }

	    form{
		width: 400px;
		margin: 0 auto;
		padding: 20px 0 20px 0;
	    }

	    input[type='submit']{
		width: 80px;
		height: 30px;
		margin: 0 auto;
		display: block;
		border-radius: 1px;
		margin-top: 30px;
	    }

	</style>
    </head>
    <body>
        <c:if test="${user == null}">
	    <h1 class='text-center'>Add User</h1>    
	</c:if>
        <c:if test="${user != null}">
	    <h1 class='text-center'>Edit User</h1>    
	</c:if>
        <form action="user" method="post">

            <label>Email: </label>
            <c:if test="${user==null}">
                <input type="email" name="email" required></br>
            </c:if>
            <c:if test="${user!=null}">
                <input type="email" name="email" value="${user.email}"></br>
            </c:if>
            <label>First Name</label>
            <input type="text" name="firstname" value="${user.firstname}" required></br>
            <label>Last Name</label>
            <input type="text" name="lastname" value="${user.lastname}" required></br>
            <c:if test="${user==null}">
                <input hidden type="text" name="action" value="add">
                <input type="submit" value="add">
            </c:if>
            <c:if test="${user!=null}">
                <input hidden type="text" name="action" value="edit">
                <input type="submit" value="edit">
            </c:if>


        </form>

    </body>
</html>
