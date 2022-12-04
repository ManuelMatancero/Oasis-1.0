let interesGenerado = document.getElementById("interesGenerado").value;
let capital = document.getElementById("capital").value;
let total = document.getElementById("total");
let capitalTotal = document.getElementById("capitalTotal").value;
let capToInt = parseInt(capitalTotal);
let cap = 0;

const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'

            // These options are needed to round to whole numbers if that's what you want.
            //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
            //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
});
function sumar() {

    let interes = parseInt(interesGenerado);
    cap = parseInt(document.getElementById("capital").value);
    if (cap >= capToInt) {


        let cambio = document.getElementById("capital").value = capToInt;
        cap = cambio;
    }
    
    let total1 = (interes + cap);
    total.innerHTML = formatter.format(total1);



}
;



