<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>ReservAR</title>
  <jsp:include page="_Links.jsp" />
</head>
<body>
<jsp:include page="_Header.jsp" />

<div class="container" id ="container">
  <c:if test="${not empty error}">
    <h5>${error}</h5>
  </c:if>
  <c:if test="${not empty restaurant}">
    <div class="row">
      <div class="col s12 m10 offset-m1">
        <h5 class="center-align">
          ${restaurant.getNombre()}
        </h5>

      </div>
    </div>

    <div class="row">
      <div class="input-field col s12 m4">
        <select id="filtroDeIngredientes" multiple>
          <option value="" disabled selected>Selecciona ingrediente</option>
        </select>
        <label>Filtrar ingredientes</label>
      </div>
      <a href="/reservar?restaurant=${restaurant.getId()}" class="btn-floating btn-large waves-effect waves-light #9ccc65 light-green lighten-1 right pulse"><i class="material-icons">add</i></a>
    </div>
    <div class="row">
      <c:forEach items="${restaurant.getListaDeMenues()}" var="menu">
        <div class="card menu" data-ingredientes="${String.join(',', menu.getIngredientes())}">
          <div class="card-content" >
            <span class="card-title">${menu.getNombre()}</span>
            <span style="float:right">$${menu.getPrecio()}</span>
            <p>${menu.getDescripcion()}</p>
          </div>
          <div class="card-action">
            <c:forEach items="${menu.getIngredientes()}" var="ingrediente">
              <div class="chip">${ingrediente}</div>
            </c:forEach>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>
</div>

<script type="application/javascript" src="/js/restaurant.js"></script>
<jsp:include page="_Footer.jsp" />
</body>
</html>