<section>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <div class="row">
                            <div class="col-md-6">
                                <p class="fs-5"><b> <i class="fa-solid fa-circle-info"></i> ${cliente.nombre} ${cliente.apellido}</b></p>
                            </div>                   
                            <div class="col-md-6">                            
                                <a href="${pageContext.request.contextPath}/ServletControladorCliente?accion=editar&idCliente=${cliente.idCliente}" class="btn btn-warning rounded-pill btn-sm">
                                    <i class="fa-solid fa-user-pen"></i>
                                    Editar
                                </a>
                                <a href="#" class="btn btn-danger rounded-pill btn-sm" 
                                   data-bs-toggle="modal" data-bs-target="#eliminarClienteModal">
                                    <i class="fa-solid fa-trash"></i>
                                    Eliminar
                                </a>
                            </div>
                        </div>                        
                    </div>
                    <div class="card-body">
                        <div class="container">                            
                            <div class="row">
                                <div class="col-md-6">
                                    <p><b>Cedula: </b></p>
                                </div>
                                <div class="col-md-6">
                                    <i><p>${cliente.cedula}</p></i>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <p><b>Telefono: </b></p>
                                </div>
                                <div class="col-md-6">
                                    <i><p>${cliente.telefono}</p></i>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <p><b>Direccion: </b></p>
                                </div>
                                <div class="col-md-6">
                                    <i><p>${cliente.direccion}</p></i>
                                </div>
                            </div>
                        </div>                       
                    </div> 
                </div>
            </div>
            <div class="col-md-6">
                <p class="fs-6"><b>Informacion de empeños</b></p>
                <div class="row">
                    <div class="col-md-4">

                        <div class="card text-center bg-white text-black mb-3 shadow-sm">
                            <a href="#empenos" class="btn">
                                <div class="card-body">
                                    <p class="fs-5">Activos</p>
                                    <p class="fs-4">
                                        <i class="fa-regular fa-circle-check"></i>
                                        ${activos}
                                    </p>
                                </div>
                            </a>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center bg-warning text-white mb-3 shadow-sm">
                            <a href="#" class="btn">
                                <div class="card-body">
                                    <p class="fs-5">Liquidados</p>
                                    <p class="fs-4">
                                        <i class="fa-regular fa-face-smile-beam"></i>
                                        ${liquidados}
                                    </p>
                                </div>
                            </a>

                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-center bg-danger text-white mb-3 shadow-sm">
                            <a href="#" class="btn">
                                <div class="card-body">
                                    <p class="fs-5">Vencidos</p>
                                    <p class="fs-4">
                                        <i class="fa-regular fa-calendar-xmark"></i>
                                        ${vencidos}
                                    </p>
                                </div>  
                            </a>

                        </div>
                    </div>
                </div>
            </div>                   
        </div>
</section>