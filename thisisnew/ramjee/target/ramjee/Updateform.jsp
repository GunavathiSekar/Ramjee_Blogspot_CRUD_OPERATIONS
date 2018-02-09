<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>

            <title> Application</title>
        </head>



        <body>
        
            <center>
            
                <h1>Management</h1>
            
            </center>
        
            <div align="center">
                ${listCustomere}
                 <table border="1" cellpadding="5">
                
                    <caption>
                        <h2>List of Login Names</h2>
                    </caption>
                
                    <tr>
                    
                        <th>FIRST NAME</th>
                    
                        <th>LAST NAME</th>
                    </tr>
                
                    <c:forEach var="cust" items="${listCustomere}">
                    
                        <tr>
                        
                            <td>
                                <c:out value="${cust.firstName}" />
                            </td>
                        
                            <td>
                                <c:out value="${cust.lastName}" />
                            </td>
                        
                            <td>
                            
                                <a href="/edit?id=<c:out value='${cust.firstName}' />">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="/delete?id=<c:out value='${cust.firstName}' />">Delete</a>
                            </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
        </body>

        </html>