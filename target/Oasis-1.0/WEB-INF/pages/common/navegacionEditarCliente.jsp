<section id="actions" class="py-4 mb-4 bg-light">
   
        <div class="row ms-4 me-4">
            <div class="col-md-6">
                <h4 class="display-6">
                    Editar Cliente
                </h4>
                 <nav aria-label="breadcrumb">
                <ol class="breadcrumb bg-white rounded w-75">
                    <li class="breadcrumb-item ms-2 mt-2 mb-2"><a href="${pageContext.request.contextPath}/ServletControladorCliente?insertado=2"">Clientes</a></li>
                    <li class="breadcrumb-item ms-2 mt-2 mb-2"><a href="${pageContext.request.contextPath}/ServletControladorEmpeno?idCliente=${cliente.idCliente}">Informacion del cliente</a></li>
                    <li class="breadcrumb-item active ms-2 mt-2 mb-2" aria-current="page">Editar cliente</li>
                </ol>
            </nav>
                <h5 style="font-style: italic">  
                 <i class="fa-solid fa-id-card"></i> ${cliente.nombre} ${cliente.apellido}
                </h5>
            </div>
             <div class="col-md-4">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fas fa-check"></i>
                    Guardar Cliente
                </button>
            </div>
             <div class="col-md-2">
                <a href="#" class="btn btn-danger">
                   <i class="fa-solid fa-close"></i>
                    Cancelar
                </a>
            </div>
        </div>
   
</section>