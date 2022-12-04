<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section id="actions" class="py-2 mb-4">
   
    <div class="row ms-2 me-2">
        <div class="col-md-9">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Fecha</th>                  
                        <th scope="col">Estado</th>
                        <th scope="col">Operacion</th>
                        <th scope="col">Cargo</th>
                        <th scope="col">Abono</th>
                        <th scope="col">Saldo</th> 
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="abono" items="${abonos}">
                        <c:set var="estado" value="${abono.estado}"/>
                        <% String estado = (String) pageContext.getAttribute("estado");%>
                        <c:if test="<%=estado.equalsIgnoreCase("pendiente")%>">
                            <tr class="table-danger">
                                <td><fmt:formatDate type="date" dateStyle = "long" value = "${abono.fechaAbono}"/></td>           
                                <td class="text-uppercase"><b>${abono.estado}</b></td>
                                <td class="text-uppercase">${abono.operacion}</td>
                                <td><fmt:formatNumber value="${abono.cargo}" type="currency"/></td>
                                <td><fmt:formatNumber value="${abono.abono}" type="currency"/></td>
                                <td><b><fmt:formatNumber value="${abono.saldo}" type="currency"/></b></td>
                                <td><a href="${pageContext.request.contextPath}/ServletControladorAbono?accion=pagarAbono&idAbono=${abono.idAbono}&idEmpeno=${idEmpeno}&idCliente=${idCliente}&alerta=pagoagregado&interesGenerado=${abono.cargo}" class="btn btn-primary btn-sm">
                                        <i class="fas fa-cash-register"></i>
                                        Saldar
                                    </a></td>
                            </tr>
                        </c:if>
                        <c:if test="<%=estado.equalsIgnoreCase("saldado")%>">
                            <tr class="table-success">
                                <td><fmt:formatDate type="date" dateStyle = "long" value = "${abono.fechaAbono}"/></td>           
                                <td class="text-uppercase"><b>${abono.estado}</b></td>
                                <td class="text-uppercase">${abono.operacion}</td>
                                <td><fmt:formatNumber value="${abono.cargo}" type="currency"/></td>
                                <td><fmt:formatNumber value="${abono.abono}" type="currency"/></td>
                                <td><b><fmt:formatNumber value="${abono.saldo}" type="currency"/></b></td>
                                <td> <a href="${pageContext.request.contextPath}/ServletControladorAbono?accion=obtenerDatos&idAbono=${abono.idAbono}&idEmpeno=${empeno.idEmpeno}" class="btn btn-info btn-sm">
                                        <i class="fa-solid fa-print"></i>
                                        Imprimir
                                    </a>
                                    <a href="#" class="btn btn-secondary btn-sm">
                                        <i class="fa-solid fa-rotate-left"></i>
                                        Revertir
                                    </a>
                                </td>
                            </tr>
                        </c:if>

                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="6"><h4 class="float-right"> Total: <fmt:formatNumber value="${saldoTotal}" type="currency"/></h4></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>

</section>





























