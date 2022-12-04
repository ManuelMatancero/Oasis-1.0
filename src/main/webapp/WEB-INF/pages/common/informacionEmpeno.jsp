<%@page import="data.AbonoDaoJDBC"%>
<%@page import="models.Abono"%>
<%@page import="java.util.List"%>
<%@page import="models.Empeno"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!-- Aqui se compara si el estado es activo o vencido para cambiar el color del header del card -->
<c:set var="estado" value="${empeno.estado}" />
<jsp:useBean id="estado" type="java.lang.String" />
<section>
    <div class="container">
        <div class="row">
            <c:set var="alerta" value="${alerta}" />
            <% String alerta = (String) pageContext.getAttribute("alerta");%>
            <c:if test="<%= alerta.equalsIgnoreCase("pagoagregado")%>">
                <div class="container" id="alerta"></div>

            </c:if> 
            <c:if test="<%= alerta.equalsIgnoreCase("empenoCancelado")%>">
                <div class="container" id="alertaCancel"></div>

            </c:if> 
            <c:if test="<%= alerta.equalsIgnoreCase("empenoArchivado")%>">
                <div class="container" id="alertaArchivado"></div>

            </c:if> 
            <div class="col-md-6">
                <div class="card mb-4 bg-light" id="cardEmpeno">
                    <!--Si esta vencido muestra el empeno como vencido-->
                    <c:if test="<%= estado.equalsIgnoreCase("Vencido")%>">
                        <div class="card-header bg-danger">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4 class="text-white">${empeno.nombrePrenda}</h4>
                                </div>
                                <div class="col-md-4">      
                                    <a href="#" class="btn btn-info btn-sm shadow">
                                        <i class="fa-solid fa-file-pen"></i>
                                        Editar</a>
                                    <a href="#" class="btn btn-control btn-sm" data-bs-toggle="modal" data-bs-target="#cancelarEmpenoModal" data-placement="top" title="Cancelar Prestamo">
                                        <img src="resources/closeIcon.png" width="20" height="20">
                                    </a>
                                </div>

                            </div>     
                        </div>
                    </c:if>
                    <c:if test="<%= estado.equalsIgnoreCase("Activo")%>">
                        <div class="card-header bg-success">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4 class="text-white">${empeno.nombrePrenda}</h4>
                                </div>
                                <div class="col-md-4"> 
                                    <a href="#" class="btn btn-info btn-sm shadow">
                                        <i class="fa-solid fa-file-pen"></i>
                                        Editar</a>
                                    <a href="#" class="btn btn-control btn-sm" data-bs-toggle="modal" data-bs-target="#cancelarEmpenoModal" data-placement="top" title="Cancelar Prestamo" >
                                        <img src="resources/closeIcon.png" width="20" height="20">
                                    </a>
                                </div>

                            </div>     
                        </div>
                    </c:if>
                    <c:if test="<%= estado.equalsIgnoreCase("Liquidado")%>">
                        <div class="card-header bg-warning">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4>${empeno.nombrePrenda}</h4>
                                </div>
                                <div class="col-md-4"> 
                                    <a href="#" class="btn btn-control btn-sm" data-bs-toggle="modal" data-bs-target="#cancelarEmpenoModal" data-placement="top" title="Cancelar Prestamo">
                                        <img src="resources/closeIcon.png" width="20" height="20">
                                    </a>
                                </div>

                            </div>     
                        </div>
                    </c:if>
                    <c:if test="<%= estado.equalsIgnoreCase("Cancelado")%>">
                        <div class="card-header bg-secondary">
                            <div class="row">
                                <div class="col-md-8">
                                    <h4>${empeno.nombrePrenda}</h4>
                                </div>


                            </div>     
                        </div>
                    </c:if>

                    <div class="card-body">
                        <!--Me quede en el apartado de empeno traer la informacion de lso empenos-->
                        <div class="row">
                            <div class="col-md-6">
                                <p>Codigo:</p>
                            </div>
                            <div class="col-md-6">
                                <p><b>${empeno.idEmpeno}</b></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <p>Peso:</p>
                            </div>
                            <div class="col-md-6">
                                <p><b>${empeno.peso}</b></p>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <p>Fecha de empeño:</p>
                            </div>
                            <div class="col-md-6">
                                <p><b><fmt:formatDate type="date" dateStyle = "long" value = "${empeno.fechaEmpeno}"/></b></p>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <p>Fecha de vencimiento:</p>
                            </div>
                            <div class="col-md-6">
                                <p><b><tags:localDate date="${empeno.fechaVencimiento}" pattern="yyyy-MM-dd" /></b></p>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <p>Pago de cuotas:</p>
                            </div>
                            <div class="col-md-6">
                                <p><b>${empeno.tipoPago}</b></p>
                            </div>

                        </div>
                        <div class="row">
                            <c:if test="<%= estado.equalsIgnoreCase("Activo")%>">
                                <div class="col-md-6">
                                    <p>Estado:</p>
                                </div>
                                <div class="col-md-6">
                                    <p class="text-success"><b>${empeno.estado}</b></p>
                                </div> 
                            </c:if>
                            <c:if test="<%= estado.equalsIgnoreCase("Vencido")%>">
                                <div class="col-md-6">
                                    <p>Estado:</p>
                                </div>
                                <div class="col-md-6">
                                    <p class="text-danger"><b>${empeno.estado}</b></p>
                                </div> 
                            </c:if>

                        </div>      
                        <div class="row">
                            <div class="col-md-6">
                                <h5 class="fs-4">Prestamo:</h5> 
                            </div>
                            <div class="col-md-6">
                                <p style="font-size: 25px; font-family: 'Open Sans', sans-serif;"><b><fmt:formatNumber value="${empeno.valorEmpeno}" type="currency"/></b></p> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <h5 class="fs-4">Intereses generados:</h5> 
                            </div>
                            <div class="col-md-6">
                                <p style="font-size: 25px; font-family: 'Open Sans', sans-serif;"><b><fmt:formatNumber value="${empeno.interesEmpeno}" type="currency"/></b></p> 
                            </div>
                        </div>
                        <c:if test="<%= estado.equalsIgnoreCase("Activo") || estado.equalsIgnoreCase("Vencido")%>">
                            <div class="card-footer">
                                <!-- Este es el boton que hace referencia a pagar de cada card -->
                                <div class="btn-group dropup">
                                    <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                                        <i class="fa-solid fa-sack-dollar"></i>
                                        Pagar
                                    </button>
                                    <!--La variable contador me genera un espacio unico identificado para cada modal de cada empeno-->
                                    <ul class="dropdown-menu">
                                        <li> <a class="dropdown-item" href="#"
                                                data-bs-toggle="modal" data-bs-target="#abonoInteresModal">Abono interes</a></li>
                                        <li> <a class="dropdown-item" href="#"
                                                data-bs-toggle="modal" data-bs-target="#abonoCapitalModal">Abono capital</a></li>
                                    </ul>
                                </div>
                                <a href="#" class="btn btn-success shadow-sm">
                                    <i class="fa-solid fa-cash-register"></i>
                                    Liquidar
                                </a>
                                <a href="${pageContext.request.contextPath}/ServletControladorEmpeno?accion=datosBoleto&idCliente=${empeno.idCliente}&idEmpeno=${empeno.idEmpeno}" class="btn btn-warning">
                                    <i class="fa-solid fa-print"></i>
                                    Imprimir

                                </a>
                            </div> 
                        </c:if>

                        <c:if test="<%= estado.equalsIgnoreCase("Liquidado") || estado.equalsIgnoreCase("cancelado")%>">
                            <div class="card-footer">
                                <a href="${pageContext.request.contextPath}/ServletControladorAbono?accion=archivar&idEmpeno=${empeno.idEmpeno}&idCliente=${empeno.idCliente}&alerta=empenoArchivado" class="btn btn-secondary">
                                    <i class="fa-solid fa-folder-open"></i>
                                    Archivar
                                </a>
                                <a href="${pageContext.request.contextPath}/ServletControladorEmpeno?accion=datosBoleto&idCliente=${empeno.idCliente}&idEmpeno=${empeno.idEmpeno}" class="btn btn-warning">
                                    <i class="fa-solid fa-print"></i>
                                    Imprimir

                                </a>
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-info text-white mb-3 shadow">
                    <div class="card-header">
                        <h3 class="fs-4">Cliente Info.</h3>
                    </div>
                    <div class="card-body">
                        <h4 class="fs-4 text-center">
                            <i class="fa-solid fa-circle-info"></i>
                            ${cliente.nombre} ${cliente.apellido}
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-warning text-white mb-3 shadow">
                    <div class="card-header">
                        <h3 class="fs-4">Pagos pendientes</h3>
                    </div>
                    <div class="card-body">

                        <h4 class="display-4 text-center">
                            <i class="fa-solid fa-hand-holding-dollar"></i>
                            ${pagosPendientes}
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-success text-white mb-3 shadow">
                    <div class="card-header">
                        <h3 class="fs-4">Pagos saldados</h3>
                    </div>
                    <div class="card-body">                    
                        <h4 class="display-4 text-center">
                            <i class="fa-solid fa-handshake-angle"></i>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--Modal para abonar interes de cada empeno-->
<div class="modal fade" id="abonoInteresModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Abonar a Interes de:  ${empeno.idEmpeno} del cliente, ${cliente.nombre}</h5>
                <button class="btn-close" data-bs-dismiss="modal" aria-label="Close">

                </button>
            </div>
            <!--Con el empeno actual obtengo sus respectivos abonos o cargos generados -->
            <c:set var="Empeno" value="${empeno}"></c:set>
            <%
                Empeno empeno = (Empeno) pageContext.getAttribute("Empeno");
                List<Abono> abonos = new AbonoDaoJDBC().listAbono(empeno);
                request.setAttribute("abonos", abonos);
            %> 
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <c:if test="<%= abonos.isEmpty()%>">
                                <div class="alert alert-secondary" role="alert">
                                    No se han generado intereses.
                                </div>
                            </c:if>
                            <table class="table">
                                <thead class="thead-light">
                                    <tr>
                                        <th scope="col">Fecha</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Cargo</th>
                                        <th scope="col">Pagar</th>
                                    </tr>
                                </thead>
                                <tbody>  
                                    <!--Muesto cada abono de ese empeno con su respectivo boton de pago-->
                                    <c:forEach var="abono" items="${abonos}">
                                        <c:set var="estado" value="${abono.estado}"></c:set>
                                        <% String estado1 = (String) pageContext.getAttribute("estado");%>
                                        <c:if test="<%= estado1.equalsIgnoreCase("pendiente")%>">
                                            <tr>
                                                <td>${abono.fechaAbono}</td>
                                                <td>${abono.estado}</td>
                                                <td colspan="3">
                                                    <form action="${pageContext.request.contextPath}/ServletControladorAbono?accion=pagarAbono&idAbono=${abono.idAbono}&idEmpeno=${empeno.idEmpeno}&idCliente=${idCliente}&alerta=pagoagregado"
                                                          method="POST" class="was-validated">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <input class="form-control" type="text" value="${abono.cargo}" name="interesGenerado" readonly>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <button type="submit" class="btn btn-success" >Pagar</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </td>
                                                <td></td> 
                                            </tr>  
                                        </c:if>

                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>                                     
                <div class="modal-footer">
                    <button class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>

        </div>
    </div> 
</div>

<!--Modal para abonar Capital de cada empeno-->
<div class="modal fade" id="abonoCapitalModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">Abonar capital a: ${empeno.idEmpeno} del cliente -> ${cliente.nombre}</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/ServletControladorAbono?accion=abonarCapital&idEmpeno=${empeno.idEmpeno}&idCliente=${idCliente}&alerta=pagoagregado"
                      method="POST" class="was-validated" >
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="interesGenerado">Interes total: </label>
                                <input class="form-control" id="interesGenerado" type="text" value="${empeno.interesEmpeno}" name="interesGenerado" disabled="true">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="capitalTotal">Capital total: </label>
                                <input class="form-control" id="capitalTotal" type="text" value="${empeno.valorEmpeno}" name="capitalTotal" disabled="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label for="capital">Capital para abonar: </label>
                                <input class="form-control" id="capital" onkeyup="sumar()" type="number" placeholder="$0.00" name="capital" required="true" >
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-8">
                            <h3 class="fs-4" name="total">Total:</h3> <h3 id="total"></h3> 
                        </div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-success">Pagar</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div> 

<jsp:include page="/WEB-INF/pages/common/cancelarEmpenoConfirmacion.jsp"/>