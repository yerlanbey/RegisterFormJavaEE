<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
<div class="list-group">

  <a href="#" class="list-group-item list-group-item-action">
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1">Yerlan Sharofidinov</h5>
      <small class="text-muted">Posted on February 22, 2021 12:00pm</small>
    </div>
    <p class="mb-1" >Hello, my name is Yerlan Sharofidinov and I'm a backend developer</p>
    <div class="d-flex w-100 justify-content-between">
      <h5 class="mb-1" style="margin-left:100px;">Mikhail Lomtadze</h5>
      <small class="text-muted">Posted on February 22, 2021 12:35pm</small>
    </div>
    <p class="mb-1" style="margin-left:100px;">Hello Yerlan nice to meet you.</p>
    	<form>
		      <div class="form-group" style="margin-left: 150px;">
				    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" style="width:400px;"></textarea>
				    	    <button type="submit" class="btn btn-primary mb-2">Comment</button>
		  	  </div>
  		</form>
  </a>
</div>
	<form>
	  <div class="form-group">
	    <label for="newpost">New post</label>
	    <textarea class="form-control" id="newpost" rows="3"></textarea>
	    <button type="submit" class="btn btn-primary mb-2">Submit</button>
	  </div>
	</form>
    </main>
</body>
</html>