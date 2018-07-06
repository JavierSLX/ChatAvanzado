<?php
    error_reporting(E_ALL ^ E_DEPRECATED);
    require_once('config.php');

    class Database
    {
        private static $db = null;
        private static $pdo;

        //Constructor de la clase
        final private function __construct()
        {
            try
            {
                self::getDB();
            }catch(PDOException $e)
            {

            }
        }

        public static function getInstance()
        {
            if(self::$db === null)
                self::$db = new self();

            return self::$db;
        }

        public function getDB()
        {
            if(self::$pdo == null)
            {
                self::$pdo = new PDO('mysql:dbname='.DB_DATABASE.';host='.DB_SERVER.';port:3306;', DB_USER, DB_PASS, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
                self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            }

            return self::$pdo;
        }

        final protected function __clone()
        {
            
        }

        function _destructor()
        {
            self::$pdo = null;
        }
    }
?>