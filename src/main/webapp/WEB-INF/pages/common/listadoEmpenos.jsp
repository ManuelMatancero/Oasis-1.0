<%@page import="data.AbonoDaoJDBC"%>
<%@page import="models.Abono"%>
<%@page import="java.util.List"%>
<%@page import="models.Empeno"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <h3 class="display-6" id="empenos">Empeños</h3>
                <c:set var="emp" value="${empenos}"/>
                <jsp:useBean id="emp" type="java.util.ArrayList"/>
                
                <!-- Si el arreglo de los empwnos esta vacio se mostrara un mensaje para que grege un nuevo empeno -->
                <c:if test="<%= emp.isEmpty()%>" >
                    <br>
                    <div class="container">
                        <div class="alert alert-primary" role="alert">
                            <h4 class="alert-heading"><i class="fa-solid fa-circle-exclamation"></i> Sin Empeños</h4>
                            <br>
                            <p>Para agregar un empeño seleccione <b><a href="#" data-bs-toggle="modal" data-bs-target="#agregarEmpenoPrendaModal">  + Nuevo Empeño</b></a></p>
                        </div>
                    </div>
                </c:if>
                <div class="list-group">
                    <c:forEach var="empeno" items="${empenos}">

                        <%
                            //Pagos pendientes por empeno
                            int contador = 0;
                            Empeno empe = (Empeno) pageContext.getAttribute("empeno");
                            List<Abono> abonos = new AbonoDaoJDBC().listAbono(empe);                           
                            for (Abono abono : abonos) {
                                if (abono.getEstado().equalsIgnoreCase("pendiente")) {
                                    contador++;
                                }

                            }

                        %>

                        <!-- Aqui se compara si el estado es activo, vencido o liquidado para cambiar el color del header del card -->
                        <c:set var="estado" value="${empeno.estado}" />
                        <jsp:useBean id="estado" type="java.lang.String" />
                        <c:if test="<%= estado.equalsIgnoreCase("Vencido")%>">
                            <a href="${pageContext.request.contextPath}/ServletControladorAbono?alerta=nula&idEmpeno=${empeno.idEmpeno}" class="list-group-item list-group-item-action bg-danger bg-opacity-50 rounded shadow" aria-current="true">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${empeno.nombrePrenda} - ${empeno.idEmpeno}</h5>
                                    <small><fmt:formatNumber value="${empeno.valorEmpeno}" type="currency"/></small>
                                </div>
                             
                                <p class="mb-1"><fmt:formatDate type="date" dateStyle = "long" value = "${empeno.fechaEmpeno}"/> - <tags:localDate date="${empeno.fechaVencimiento}" pattern="yyyy-MM-dd" /></b></p>

                                <small>${empeno.estado}</small>
                            </a> 
                            <br/>
                        </c:if>
                        <c:if test="<%= estado.equalsIgnoreCase("Activo")%>">
                            <a href="${pageContext.request.contextPath}/ServletControladorAbono?alerta=nula&idEmpeno=${empeno.idEmpeno}" class="list-group-item list-group-item-action rounded shadow" aria-current="true">
                                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                    <%=contador%>
                                    <span class="visually-hidden">unread messages</span>
                                </span>
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${empeno.nombrePrenda} - ${empeno.idEmpeno}</h5>
                                    <small><fmt:formatNumber value="${empeno.valorEmpeno}" type="currency"/></small>
                                </div>
                                <p class="mb-1"><fmt:formatDate type="date" dateStyle = "long" value = "${empeno.fechaEmpeno}"/> - <tags:localDate date="${empeno.fechaVencimiento}" pattern="yyyy-MM-dd" /></b></p>

                                <small>${empeno.estado}</small>

                            </a> 
                            <br/>
                        </c:if>
                        <c:if test="<%= estado.equalsIgnoreCase("Liquidado")%>">
                            <a href="${pageContext.request.contextPath}/ServletControladorAbono?alerta=nula&idEmpeno=${empeno.idEmpeno}" class="list-group-item list-group-item-action bg-warning bg-opacity-50 rounded shadow" aria-current="true">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${empeno.nombrePrenda} - ${empeno.idEmpeno}</h5>
                                    <small><fmt:formatNumber value="${empeno.valorEmpeno}" type="currency"/></small>
                                </div>
                                <p class="mb-1"><fmt:formatDate type="date" dateStyle = "long" value = "${empeno.fechaEmpeno}"/> - <tags:localDate date="${empeno.fechaVencimiento}" pattern="yyyy-MM-dd" /></b></p>
                                <small>${empeno.estado}</small>
                            </a> 
                            <br/>
                        </c:if>
                             <c:if test="<%= estado.equalsIgnoreCase("Cancelado")%>">
                            <a href="${pageContext.request.contextPath}/ServletControladorAbono?alerta=nula&idEmpeno=${empeno.idEmpeno}" class="list-group-item list-group-item-action bg-secondary bg-opacity-50 rounded shadow" aria-current="true">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${empeno.nombrePrenda} - ${empeno.idEmpeno}</h5>
                                    <small><fmt:formatNumber value="${empeno.valorEmpeno}" type="currency"/></small>
                                </div>
                                <p class="mb-1"><fmt:formatDate type="date" dateStyle = "long" value = "${empeno.fechaEmpeno}"/> - <tags:localDate date="${empeno.fechaVencimiento}" pattern="yyyy-MM-dd" /></b></p>
                                <small>${empeno.estado}</small>
                            </a> 
                            <br/>
                        </c:if>

                    </c:forEach>                 
                </div>
            </div>          
            <div class="col-md-4">

            </div>           
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/pages/empeno/agregarEmpenoPrenda.jsp"/>