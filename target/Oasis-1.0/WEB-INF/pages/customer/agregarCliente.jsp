<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Cliente</h5>
                <button class="btn-close" data-bs-dismiss="modal" aria-label="Close">
            
                </button>
            </div>

            <form action="${pageContext.request.contextPath}/ServletControladorCliente?accion=insertar"
                  method="POST" class="was-validated" >
            
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required/>        
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido" required/>        
                    </div><!-- comment -->
                    <div class="form-group">
                        <label for="cedula">Cedula</label>
                        <input type="number" class="form-control" name="cedula"/>        
                    </div>
                    <div class="form-group">
                        <label for="telefono">Telefono</label>
                        <input type="tel" class="form-control" name="telefono"/>        
                    </div>
                    <div class="form-group">
                        <label for="direccion">Direccion</label>
                        <textarea class="form-control" name="direccion">
                            
                        </textarea>       
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" type="submit">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    </div> 
</div> 