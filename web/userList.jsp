<%-- 
    Document   : userList
    Created on : Oct 18, 2019, 8:16:21 PM
    Author     : XV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	<style>
	    table {
		    border: 1px solid black;
		    border-collapse: collapse;
		    margin: 0 auto;
	    }
	    th, td {
		    border: 1px solid black;
		    text-align: left;
		    padding: .5em;
	    }
	    .align_right {
		    text-align: right;
	    }
	    .center {
		text-align: center;
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
        <h1 class='center'>User List Information</h1>
	<table>
	     <tr>
		<th>Email</th>
		<th>First Name</th> 
		<th>Last Name</th>
		<th></th>
		<th></th>
	      </tr>
	      <c:forEach var = "userItem" items="${userList}">
		   <tr>
		    <td>${userItem.email}</td>
		    <td>${userItem.firstname}</td>
		    <td>${userItem.lastname}</td>
		    <td>
			<a 
                            href="<c:url value="admin?action=get-edit&email=${userItem.email}"/>">
			    Edit
			</a>
                            
                            
		    </td>
                    
		    <td>
                        <a 
                            href="admin?action=delete&email=${userItem.email}">delete
                        </a>
                    </td>
		  </tr>
	      </c:forEach>
	</table>
	<form action="admin">
	    <input hidden type="text" name='action' value = 'get-input'>
	    <input type="submit" value="Add">    
	</form>
    </body>
</html>
