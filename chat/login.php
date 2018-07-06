<?php
    require 'Database.php';

    //Manda llamar los elementos de la consulta
    Registro::obtenerTodosLosUsuarios();

    class Registro
    {
        function __construct()
        {
            
        }

        public static function obtenerTodosLosUsuarios()
        {
            $consulta = "SELECT * FROM login";

            //Prepara la consulta y la ejecuta
            $resultado = Database::getInstance()->getDB()->prepare($consulta);
            $resultado->execute();

            $json = $resultado->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode($json);
    
        }
    }
?>