<%@page import="data.AbonoDaoJDBC"%>
<%@page import="models.Abono"%>
<%@page import="java.util.List"%>
<%@page import="data.EmpenoDaoJDBC"%>
<%@page import="models.Empeno"%>
<%@page import="models.Cliente"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <input class="form-control me-2" id="campo" type="text" placeholder="Buscar Cliente">
                </br>    
                <c:set var="insertado" value="${insertado}" />
                <% String insertado = (String) pageContext.getAttribute("insertado");%>
                <c:if test="<%= insertado.equals("1")%>">
                    <div class="container" id="added"></div>
                </c:if>  

                <c:if test="<%= insertado.equals("0")%>">
                    <div class="container" id="error"></div>
                </c:if>  

                <c:if test="<%= insertado.equals("3")%>">
                    <div class="container" id="deleted"></div>
                </c:if> 



                <div id="lista" class="list-group">
                    <c:forEach var="cliente" items="${clientes}">

                        <%
                            //Codigo para saber cuantos pagos pendientes tiene cada cliente
                            int contador = 0;
                            Cliente customer = (Cliente) pageContext.getAttribute("cliente");
                            List<Empeno> empenos = new EmpenoDaoJDBC().listEmpeno(customer);

                            for (Empeno empeno : empenos) {
                                //se verifica solo si los empenos estan activos o vencidos
                                if (empeno.getEstado().equalsIgnoreCase("activo") || empeno.getEstado().equalsIgnoreCase("vencido")) {
                                    List<Abono> abonos = new AbonoDaoJDBC().listAbono(empeno);
                                    for (Abono abono : abonos) {
                                        if (abono.getEstado().equalsIgnoreCase("pendiente")) {
                                            contador++;
                                        }

                                    }
                                }

                            }

                        %>
                        <a href="${pageContext.request.contextPath}/ServletControladorEmpeno?idCliente=${cliente.idCliente}" class="list-group-item list-group-item-action">
                            <b>${cliente.nombre} ${cliente.apellido}</b>    <span class="badge rounded-pill text-bg-primary position-absolute top-1 end-0""><%=contador%></span>
                            <div class="d-flex w-100 justify-content-between">
                                <p class="mb-2">${cliente.direccion}<p>
                                    <small>Telefono: <b>${cliente.telefono}</b></small>
                            </div>

                        </a>
                    </c:forEach>
                </div>
            </div>
            <!-- Inicio Targetas para los totales -->
            <div class="col-md-4">

                <div class="card text-center bg-info text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-6">
                            <i class="fas fa-users"></i>
                            ${totalClientes}
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Capital Prestado</h3>
                        <h4 class="display-6">
                            <i class="fa-solid fa-sack-dollar"></i>
                            <fmt:formatNumber value="${capital}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-secondary text-white mb-3">
                    <div class="card-body">
                        <h3>Capital Cobrado</h3>
                        <h4 class="display-6">
                            <i class="fa-solid fa-file-invoice-dollar"></i>
                            <fmt:formatNumber value="${capitalCobrado}" type="currency"/>
                        </h4>
                    </div>
                </div>
                <div class="card text-center bg-warning text-white mb-3">
                    <div class="card-body">
                        <h3>Intereses Generados</h3>
                        <h4 class="display-6">
                            <i class="fa-solid fa-money-bill-trend-up"></i>
                            <fmt:formatNumber value="${interes}" type="currency"/>
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Interes Cobrado</h3>
                        <h4 class="display-6">
                            <i class="fa-solid fa-receipt"></i>
                            <fmt:formatNumber value="${interesCobrado}" type="currency"/>
                        </h4>
                    </div>
                </div>


            </div>
            <!-- Final Targetas para los totales -->
        </div>
    </div>
</div>
</section>
<!--JSP agregar clientes modal-->
<jsp:include page="/WEB-INF/pages/customer/agregarCliente.jsp"/>