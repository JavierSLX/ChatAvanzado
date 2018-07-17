<?php
    require ('login.php');

    if($_SERVER['REQUEST_METHOD'] == 'POST')
    {
        //Recibe un JSON y los descompone
        $datos = json_decode(file_get_contents("php://input"), true);
        echo $datos["usuario"];
        echo $datos["pass"];

        //Inserta un nuevo elemento
        $respuesta = Registro::insertarNuevoUsuario($datos["usuario"], $datos["pass"]);
        if($respuesta)
            echo "Se insertaron los datos correctamente";
        else
            echo "Sucedió un error";
    }
?>