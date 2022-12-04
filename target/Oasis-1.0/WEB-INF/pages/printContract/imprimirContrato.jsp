<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/6b4767d6a5.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="estilos/estilos.css"/>
        <title>JSP Page</title>
    </head>

    <body>
        <!--Cabecero-->
        <jsp:include page="/WEB-INF/pages/common/header.jsp"/>
        <div class="container mt-2">
            <div class="row">
                <div class="col-md-6">
                    <h4>Nombre:  <b>${nombreCliente}</b></h4>
                </div>
                <div class="col-md-6">
                    <h4>Cod. empeño:  <b>${idEmpeno}</b></h4> 
                </div>
            </div>

        </div>
        <hr>
        <section class="ms-3 mt-2 me-3">

            <div class="row">
                <div class="col-md-5">
                    <h3>Que desea hacer con el contrato?</h3>
                    <br>
                    <div class="d-grid gap-2">
                        <button class="btn btn-secondary" type="button" onclick="printPdf()" >
                            <i class="fa-solid fa-print"></i>
                            Imprimir contrato
                        </button>
                        <button class="btn btn-secondary" type="button">
                            <i class="fa-solid fa-download"></i>
                            Descargar contrato
                        </button>
                    </div>
                </div>
                <div class="col-md-7">
                    <iframe src="${pageContext.request.contextPath}/ServletControladorEmpeno?accion=imprimirBoleto&idCliente=${empeno.idCliente}&idEmpeno=${empeno.idEmpeno}" width="100%" height="410" style="border:1px solid black;" id="printf" name="printf"></iframe>
                </div>
            </div>

        </section>
        <div class="d-grid gap-2">
            <a href="${pageContext.request.contextPath}/ServletControladorCliente?insertado=2" class="btn btn-primary position-fixed bottom-0 start-50 translate-middle-x w-100 rounded-0">
                <h3><i class="fa-solid fa-arrow-left"></i></h3>
                Volver al inicio</a>
        </div>

        <script src="js/printIframe.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    </body>
</html>
