<div class="modal fade" id="cancelarEmpenoModal">
      <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
             <h4 class="modal-title">Confirmacion</h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
        </div>
        <div class="modal-body">
            <p>Se cancelara el Empeno de codigo #: <b>${empeno.idEmpeno}</b></p>
        </div>
        <div class="modal-footer">
            <a href="${pageContext.request.contextPath}//ServletControladorAbono?accion=cancelar&idEmpeno=${empeno.idEmpeno}&idCliente=${empeno.idCliente}&alerta=empenoCancelado" type="button" class="btn btn-danger">Cancelar Empeño</a>
          <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Volver</button>   
        </div>
      </div>
    </div>
  </div>
</div> 