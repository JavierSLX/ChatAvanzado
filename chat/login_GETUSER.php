<?php
    require('login.php');

    if($_SERVER['REQUEST_METHOD'] == 'GET')
    {
        $contenedor = array();
        if(isset($_GET['user']))
        {
            //Obtiene todos los datos del login
            $resultado = Registro::obtenerDatosPorUser($_GET['user']);

            //Forma el JSON (Haya datos en la consulta o no)
            if($resultado)
            {
                $contenedor["resultado"] = "CC";
                $contenedor["datos"] = $resultado;
            }
            else
            {
                $contenedor["resultado"] = "SU";
                $contenedor["datos"] = array("resultado" => "El usuario no existe");
            }
        }
        //Cuando no se le mando un usuario
        else
        {
            $contenedor["resultado"] = "SD";
            $contenedor["datos"] = array("resultado" => "Coloque el usuario");
        }

        echo json_encode($contenedor);
    }
?>