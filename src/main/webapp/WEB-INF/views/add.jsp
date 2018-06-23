<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Добавление детали</title>

		<style> .error { color: red } </style>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
     </head>
     <body>
      <br><br><br><br><br><br><br><br>
      <div class="container">
        <a href="./" class="btn btn-primary">Назад к списку</a>
        <br><br>

		<h1>Добавить деталь:</h1>
		<form:form action="add" method="post" modelAttribute="part" accept-charset="UTF-8">
			<table class="table">
				<tr>
					<td>Наименование: </td>
					<td>
						<form:input path="name" class="form-control"/> <br />
						<form:errors path="name" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td>Необходимость: </td>
					<td>
						<form:checkbox path="isRequired" /> <br />
						<form:errors path="isRequired" cssClass="error" />
					</td>
				</tr>
				<tr>
                    <td>Количество: </td>
                    <td>
                        <form:input type="number" path="count" class="form-control" /> <br />
                        <form:errors path="count" cssClass="error" />
                    </td>
                </tr>
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-success">Добавить</button></td>
				</tr>
			</table>
		</form:form>
	  </div>
	</body>
</html>