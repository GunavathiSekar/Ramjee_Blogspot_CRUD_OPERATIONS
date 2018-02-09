<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books Store Application</title>
</head>
<body>
    <center>
        <h1>Books Management</h1>
        
    </center>
    <div align="center">
        <c:if test="${book != null}">
            <form action="editupdate" method="post">
        </c:if>
        <c:if test="${book == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${book != null}">
                        Edit Book
                    </c:if>
                    <c:if test="${book == null}">
                        Add New Book
                    </c:if>
                </h2>
            </caption>
            <c:if test="${book != null}">
                    <input type="hidden" name="id" value="<c:out value='${book.firstName}' />" />
                </c:if> 
                     
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="firstname" size="45"
                            value="<c:out value='${book.firstName}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Author: </th>
                <td>
                    <input type="text" name="LastName" size="45"
                            value="<c:out value='${book.lastName}' />"
                    />
                </td>
            </tr>            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>