<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Список деталей</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
     </head>
     <body>
      <br><br>
      <div class="container">
        <a href="add" class="btn btn-primary">Добавить деталь</a>
        <br><br>
        <h1>Список деталей:</h1>

        <div class="btn-group" role="group" aria-label="Basic example">
          <div class="input-group-prepend">
            <div class="input-group-text" id="btnGroupAddon">Показывать</div>
          </div>
          <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${currentFilter.label()}
          </button>
          <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
            <c:forEach items="${allFilters}" var="f">
                <c:if test="${f != currentFilter}">
                    <a class="dropdown-item" href="./?filter=${f}&query=${currentQuery}">${f.label()}</a>
                </c:if>
            </c:forEach>
          </div>
        </div>
        <br><br>

        <div>
            <form:form action="./" method="get" accept-charset="UTF-8" class="form-inline">
                <label class="form-group mb-2" style="margin-right: 10px">Поиск по названию:</label>
                <input type="hidden" name="filter" value="${currentFilter}"/>
                <input type="text" name="query" value="${currentQuery}" class="form-control mb-2" style="margin-right: 10px"/>
                <input type="submit" value="Поиск" class="btn btn-primary mb-2"/>
            </form:form>
        </div>
        <br>

        <p class="lead">
            Из деталей в наличии можно собрать <b>${totalComputers}</b> компьютеров
        </p>

        <p class="lead">
            Найдено деталей: <b>${totalParts}</b>
        </p>
        <br>

		<table class="table">
			<tr>
				<td><strong>Наименование</strong></td>
				<td><strong>Необходимость</strong></td>
                <td><strong>Количество</strong></td>
                <td></td>
                <td></td>
			</tr>
			<c:forEach items="${parts}" var="p">
				<tr>
					<td>${p.name}</td>
					<td>${p.isRequired ? "да" : "нет"}</td>
                    <td>${p.count}</td>
                    <td>
                        <a class="btn btn-link" href="update?id=${p.id}">Изменить</a>
                    </td>
                    <td>
                        <a elementName="a" class="btn btn-link" href="delete?id=${p.id}">Удалить</a>
                    </td>
				</tr>
			</c:forEach>
		</table>

		<div class="btn-group" role="group" aria-label="Basic example">
		    <c:forEach var = "i" begin = "1" end = "${totalPages}">
                 <a href="./?filter=${currentFilter}&page=${i}&query=${currentQuery}" class="btn btn-${i == page ? 'secondary' : 'light'}">${i}</a>
            </c:forEach>
        </div>
	  </div>
	  <br><br>
	</body>
</html>