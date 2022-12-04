window.addEventListener("load", () => {
    //Obtengo los elementos de la lista del html
    let campo = document.getElementById("campo");
    let list = document.querySelectorAll("#lista a");
    
    campo.onkeyup = () =>{
        let search = campo.value.toLowerCase();
        
        for(let i of list){
            let item = i.innerHTML.toLowerCase();
            if(item.indexOf(search) == -1){
                i.classList.add("hide");
            }else{
                i.classList.remove("hide");
            }
        }
        
    };   
});