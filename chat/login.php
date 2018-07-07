<?php
    require 'Database.php';

    class Registro
    {
        function __construct()
        {
            
        }

        //Metodo que regresa todos los elementos
        public static function obtenerTodosLosUsuarios()
        {
            $consulta = "SELECT * FROM login";

            //Prepara la consulta y la ejecuta
            $resultado = Database::getInstance()->getDB()->prepare($consulta);
            $resultado->execute();

            $json = $resultado->fetchAll(PDO::FETCH_ASSOC);

            return $json;
        }

        //Método que regresa los elementos por su usuario
        public static function obtenerDatosPorUser($usuario)
        {
            $consulta = "SELECT * FROM login WHERE usuario = ?";

            //Prepara la consulta y la ejecuta
            try
            {
                $resultado = Database::getInstance()->getDB()->prepare($consulta);
                $resultado->execute(array($usuario));

                $json = $resultado->fetch(PDO::FETCH_ASSOC);
                return $json;
            }catch(PDOException $e)
            {
                //Cuando no obtiene nada regresa un false
                return false;
            }
        }
    }
?>