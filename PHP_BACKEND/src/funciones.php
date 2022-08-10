<?php

function guardarProducto($arrayParametro){
    try{
        $arrayResponse = array();
        require 'database.php';
        $strSql = "INSERT INTO info_producto (marca, modelo, descripcion, precio, estado) 
                    VALUES (?, ?, ?, ?, ?)";
        $stmt = $db->prepare($strSql);
        $stmt->bind_param('sssss', $marca, 
                                   $modelo, 
                                   $descripcion,
                                   $precio, 
                                   $estado);
        $marca          = $arrayParametro['marca'];
        $modelo         = $arrayParametro['modelo']; 
        $descripcion    = $arrayParametro['descripcion'];
        $precio         = $arrayParametro['precio']; 
        $estado         = $arrayParametro['estado'];
        $boolGuardo = $stmt->execute();
        if($boolGuardo){
            $arrayResponse = array("mensaje"    => "Se guardo correctamente",
                                    "status"    => "ok",
                                    "code"      => 200);
        }else{
            $arrayResponse = array("mensaje"    => "No se pudo guardar",
                                   "status"     => "error",
                                   "code"       => 500);
        }
        return $arrayResponse;
    } catch (\Throwable $th) {
        echo 'guardarProducto()   '.$th->getMessage();
    }
}

function obtenerProductos($arrayParametro) {
    try{
        $arrayResponse = array();
        require 'database.php';
        $strSql = "SELECT * FROM info_producto where estado = ? ORDER BY id ASC";
        $stmt = $db->prepare($strSql);
        $stmt->bind_param('s', $estado);
        $estado = $arrayParametro['estado'];
        $stmt->execute();
        $result = $stmt->get_result();
        $arrayResponse = $result->fetch_all(MYSQLI_ASSOC);
        return $arrayResponse;
    } catch (\Throwable $th) {
        echo 'guardarProducto()   '.$th->getMessage();
    }
}