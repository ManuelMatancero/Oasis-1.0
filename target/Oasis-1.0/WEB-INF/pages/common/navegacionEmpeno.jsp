<section id="actions" class="py-2 mb-2 bg-light">

    <div class="row ms-4 me-4">
        <div class="col-md-6">
            <h3 class="display-6">
                Informacion del cliente
            </h3>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-white rounded w-50">
                    <li class="breadcrumb-item ms-2 mt-2 mb-2"><a href="${pageContext.request.contextPath}/ServletControladorCliente?insertado=2"">Clientes</a></li>
                    <li class="breadcrumb-item active ms-2 mt-2 mb-2" aria-current="page">Informacion del cliente</li>
                </ol>
            </nav>
        </div>
        <div class="col-md-6">
            <div class="d-grid gap-2 mt-4 d-md-flex justify-content-md-end">
                <a href="#" class="btn btn-primary rounded-pill shadow float-right btn-lg" 
                   data-bs-toggle="modal" data-bs-target="#agregarEmpenoPrendaModal">
                    <i class="fas fa-plus"></i>
                    <b>Nuevo Empeño</b>
                </a>  
            </div>

            <!--  <div class="card shadow-sm">
                 <div class="card-header bg-light">
                     <div class="row">
                         <div class="col-md-6">
 
                             <h4>${cliente.nombre} ${cliente.apellido}</h4>
                         </div>
                         <div class="col-md-6">
                             <a href="${pageContext.request.contextPath}/ServletControladorCliente?accion=editar&idCliente=${cliente.idCliente}" class="btn btn-warning rounded-pill">
                                 <i class="fa-solid fa-user-pen"></i>
                                 Editar
                             </a>
                             <a href="#" class="btn btn-danger rounded-pill" 
                                data-bs-toggle="modal" data-bs-target="#eliminarClienteModal">
                                 <i class="fa-solid fa-trash"></i>
                                 Eliminar
                             </a>
                         </div>
                     </div>
                 </div>
                 <div class="card-footer">
                     <a href="#" class="btn btn-primary rounded-pill shadow" 
                        data-bs-toggle="modal" data-bs-target="#agregarEmpenoPrendaModal">
                         <i class="fas fa-plus"></i>
                         <b>Nuevo Empeño</b>
                     </a>
                 </div>
 
             </div>-->
        </div>
    </div>

</section>
<jsp:include page="/WEB-INF/pages/common/eliminarModalConfirmacion.jsp"/>