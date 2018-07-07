<?php
    require('login.php');

    if($_SERVER['REQUEST_METHOD'] == 'GET')
    {
        //Obtiene todos los datos del login
        $resultado = Registro::obtenerTodosLosUsuarios();
        echo json_encode($resultado);
    }
?>