<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<%@page import="hw4.webapp.javaEE.datamean.User"%>

<%@page import="java.util.List"%>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    
    <%
		List<String> errors = (List<String>) request.getAttribute("errors");
		if (errors != null) {
			for (String error : errors) {
%>		
				<h3 style="color:red"> <%= error %> </h3>
<%
			}
		}
%>	
<style><%@include file="style.css"%></style>
<%
	User[] users = (User[]) request.getAttribute("users");
%>    
<table id="users">
<tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Password</th>
    <th>Action</th>
  </tr>
<%
        for (int i = 0; i < users.length; i++) {
            User user = users[i];
%>
  <tr>

    <td><%= user.getFirst_name() %></td>
    <td><%= user.getLast_name() %></td>
    <td><%= user.getEmail() %></td>
    <td><%= user.getPassword () %></td>
        <form class="delete-form" method="POST" action="Delete">
            <input type="hidden" name="email" value="<%= user.getEmail() %>">
    		<td><button type="submit" class="btn btn-danger">Delete</button></td>
    	</form>
  </tr>
 <%
        }
%>
</table>
</body>
</html>