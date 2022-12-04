
function mostrarAlerta(){
    
    return '<div class="alert alert-success alert-dismissible fade show" role="alert"> El pago se ha realizado correctamente. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}

function mostrarAlertaCancelado(){
    
    return '<div class="alert alert-secondary alert-dismissible fade show" role="alert"> El empeno se ha cancelado correctamente. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}
function mostrarAlertaArchivado(){
    
    return '<div class="alert alert-warning alert-dismissible fade show" role="alert"> El empeno se ha archivado correctamente. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}

let alerta = document.getElementById("alerta");

let alertaCancel = document.getElementById("alertaCancel");

let alertaArchivado = document.getElementById("alertaArchivado");

if(alerta){
    alerta.innerHTML = mostrarAlerta();
}
if(alertaCancel){
    alertaCancel.innerHTML = mostrarAlertaCancelado();
}

if(alertaArchivado){
    alertaArchivado.innerHTML = mostrarAlertaArchivado();
}