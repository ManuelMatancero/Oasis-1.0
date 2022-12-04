
<div class="modal fade" id="agregarEmpenoPrendaModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Nuevo Empeño</h5>
                <button class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form action="${pageContext.request.contextPath}/ServletControladorEmpeno?accion=insertarEmpeno&idCliente=${cliente.idCliente}"
                  method="POST" class="was-validated" >

                <div class="modal-body">
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-8">
                                <label for="nombreArticulo"><b>Nombre del articulo:</b></label>
                                <input type="text" class="form-control" placeholder="Nombre del articulo" name="nombrePrenda" required>
                            </div>
                            <div class="col-md-4">
                                <label for="peso"><b>Peso:</b></label>
                                <input type="text" class="form-control" placeholder="Peso" name="peso">
                            </div>
                        </div>       
                    </div>
                    <div class="form-group">
                        <label for="valorEmpeno"><b>Valor de empeño:</b></label>
                        <input type="number" class="form-control" placeholder="Valor de empeño" name="valorEmpeno" required>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="meses"><b>Meses:</b></label>
                                <select class="form-control" aria-label="Default select example" name="meses" required>
                                    <option value="3">3 meses</option>
                                    <option value="4">4 meses</option>
                                    <option value="5">5 meses</option>
                                    <option value="5">6 meses</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="interes"><b>Interes:</b></label>
                                <select class="form-control" aria-label="Default select example" name="interes" required>
                                    <option value="10">10%</option>
                                    <option value="6">6%</option>
                                    <option value="7">7%</option>
                                    <option value="8">8%</option>
                                    <option value="9">9%</option>
                                    <option value="11">11%</option>
                                    <option value="12">12%</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="tipoPago"><b>Tipos de cuotas:</b></label>
                                <select class="form-control" aria-label="Default select example" name="tipoPago" required>
                                    <option value="Q">Quincenal</option>
                                    <option value="S">Semanal</option>
                                    <option value="M">Mensual</option>
                                    <option value="A">Anual</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="row">
                            <div class="col">
                                <label for="categoria"><b>Categoria:</b></label>
                                <select class="form-control" aria-label="Default select example" name="categoria" required>
                                    <option selected>Categoria</option>
                                    <option value="Joya">Joya</option>
                                    <option value="Electrodomestico">Electrodomestico</option>
                                    <option value="Otro">Otro</option>
                                </select>
                            </div>
                            <div class="col">
                                <label for="descripcion"><b>Descripcion:</b></label>
                                <input type="text" class="form-control" placeholder="Descripcion" name="descripcion">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>
        </div>
        </form>
    </div>
</div> 
</div> 