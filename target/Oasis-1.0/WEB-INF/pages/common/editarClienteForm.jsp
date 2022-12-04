<section>
    
        <form action="${pageContext.request.contextPath}/ServletControladorCliente?accion=modificar&idCliente=${cliente.idCliente}"
              method="POST" class="was-validated" >
            <jsp:include page="/WEB-INF/pages/common/navegacionEditarCliente.jsp"/>
            <div class="modal-body">
                <div class="form-group w-50 ms-4 me-4">
                    <label for="nombre">Nombre</label>
                    <input type="text" class="form-control" name="nombre" value="${cliente.nombre}" required/>        
                </div>
                <div class="form-group w-50 ms-4 me-4">
                    <label for="apellido">Apellido</label>
                    <input type="text" class="form-control" name="apellido" value="${cliente.apellido}" required/>        
                </div><!-- comment -->
                <div class="form-group w-50 ms-4 me-4">
                    <label for="cedula">Cedula</label>
                    <input type="number" class="form-control" name="cedula" value="${cliente.cedula}" required/>        
                </div>
                <div class="form-group w-50 ms-4 me-4">
                    <label for="telefono">Telefono</label>
                    <input type="tel" class="form-control" name="telefono" value="${cliente.telefono}" requred/>        
                </div>
                <div class="form-group w-50 ms-4 me-4">
                    <label for="direccion">Direccion</label>
                    <input type="text" class="form-control" name="direccion" value="${cliente.direccion}" />       
                </div>
            </div>
        </form>
   
</section>



