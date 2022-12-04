function mostrarAlertaAdded(){
    
    return '<div class="alert alert-success alert-dismissible fade show" role="alert"> Cliente agregado correctamente. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}

function mostrarAlertaError(){
    
    return '<div class="alert alert-danger alert-dismissible fade show" role="alert"> Error al realizar la operacion. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}

function mostrarAlertaDeleted(){
    
    return '<div class="alert alert-danger alert-dismissible fade show" role="alert"> Cliente eliminado correctamente. <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div> ';
}


let added = document.getElementById("added");
let errorAdding = document.getElementById("error");
let deleted = document.getElementById("deleted");

if(added){
    added.innerHTML = mostrarAlertaAdded();
}

if(errorAdding){
    errorAdding.innerHTML = mostrarAlertaError();
}

if(deleted){
    deleted.innerHTML = mostrarAlertaDeleted();
}
