<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/6b4767d6a5.js" crossorigin="anonymous"></script>     
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="estilos/estilos.css"/>
    </head>
    <!-- Header de la pagina -->
    <jsp:include page="WEB-INF/pages/common/header.jsp"/>
    <body>
        <!-- navegacion -->
        <jsp:include page="WEB-INF/pages/common/navegacionAbonos.jsp"/>
        <jsp:include page="/WEB-INF/pages/common/informacionEmpeno.jsp"/>
        <hr>
        <!-- Listado de abonos -->
        <h3 class="fs-4 ms-4">Historial de operaciones</h3>
        <jsp:include page="WEB-INF/pages/abono/tableAbono.jsp"/>

        <!--footer -->
        <jsp:include page="WEB-INF/pages/common/footer.jsp"/>
        <script src="js/totalFormated.js"></script>
        <script src="js/toasts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>


    </body>
</html>
