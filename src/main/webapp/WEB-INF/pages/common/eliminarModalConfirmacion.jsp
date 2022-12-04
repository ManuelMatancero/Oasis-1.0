<div class="modal fade" id="eliminarClienteModal">
      <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
             <h4 class="modal-title">Confirmacion</h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
        </div>
        <div class="modal-body">
            <p>Se eliminara el cliente <b>${cliente.nombre} ${cliente.apellido}</b></p>
        </div>
        <div class="modal-footer">
            <a href="${pageContext.request.contextPath}/ServletControladorEmpeno?accion=eliminarCliente&idCliente=${cliente.idCliente}" type="button" class="btn btn-danger">Eliminar</a>
          <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cancelar</button>   
        </div>
      </div>
    </div>
  </div>
</div> 