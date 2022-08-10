<?php

function guardarProducto($arrayParametro){
    try{
        $arrayResponse = array();
        require 'database.php';
        $strSql = "INSERT INTO info_producto (marca, modelo, descripcion, precio, estado, imagen) 
                    VALUES (?, ?, ?, ?, ?, ?)";
        $stmt = $db->prepare($strSql);
        $stmt->bind_param('ssssss', $marca, 
                                   $modelo, 
                                   $descripcion,
                                   $precio, 
                                   $estado,
                                   $imagen);
        $marca          = $arrayParametro['marca'];
        $modelo         = $arrayParametro['modelo']; 
        $descripcion    = $arrayParametro['descripcion'];
        $precio         = $arrayParametro['precio']; 
        $estado         = $arrayParametro['estado'];
        $imagen         = $arrayParametro['imagen'];
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

function actualizarProducto($arrayParametro){
    try{
        $arrayResponse = array();
        require 'database.php';
        $strSql = "UPDATE info_producto 
                   SET marca = ?, 
                   modelo = ?, 
                   descripcion = ?, 
                   precio = ?, 
                   estado = ?, 
                   imagen = ?
                   WHERE id = ?";
        $stmt = $db->prepare($strSql);
        $stmt->bind_param('ssssssi', $marca, 
                                    $modelo, 
                                    $descripcion,
                                    $precio, 
                                    $estado,
                                    $imagen,
                                    $id);
        $marca          = $arrayParametro['marca'];
        $modelo         = $arrayParametro['modelo'];
        $descripcion    = $arrayParametro['descripcion'];
        $precio         = $arrayParametro['precio'];
        $estado         = $arrayParametro['estado'];
        $imagen         = $arrayParametro['imagen'];
        $id             = $arrayParametro['id'];
        $boolGuardo = $stmt->execute();
        if($boolGuardo){
            $arrayResponse = array("mensaje"    => "Se actualizo correctamente",
                                    "status"    => "ok",
                                    "code"      => 200);
        }else{
            $arrayResponse = array("mensaje"    => "No se pudo actualizar",
                                   "status"     => "error",
                                   "code"       => 500);
        }
        return $arrayResponse;
    } catch (\Throwable $th) {
        echo 'guardarProducto()   '.$th->getMessage();
    }
}

function eliminarProducto($arrayParametro){
    try{
        $arrayResponse = array();
        require 'database.php';
        $strSql = "UPDATE info_producto 
                   SET estado = ?
                   WHERE id = ?";
        $stmt = $db->prepare($strSql);
        $stmt->bind_param('si', $estado, $id);
        $estado = 'Eliminado';
        $id     = $arrayParametro['id'];
        $boolElimino = $stmt->execute();
        if($boolElimino){
            $arrayResponse = array("mensaje"    => "Se elimino correctamente",
                                    "status"    => "ok",
                                    "code"      => 200);
        }else{
            $arrayResponse = array("mensaje"    => "No se pudo eliminar",
                                   "status"     => "error",
                                   "code"       => 500);
        }
        return $arrayResponse;
    } catch (\Throwable $th) {
        echo 'guardarProducto()   '.$th->getMessage();
    }
}