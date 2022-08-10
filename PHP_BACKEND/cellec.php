<?php
require 'src/funciones.php';
$objRequest = file_get_contents('php://input');
$arrayRequest = json_decode($objRequest, true);
$arrayResponse = array();
switch($arrayRequest['op']){
    case 'guardarProductos':
        $arrayResponse = guardarProducto($arrayRequest['data']);
        break;
    case 'obtenerProductos':
        $arrayResponse = obtenerProductos($arrayRequest['data']);
        break;
    case 'actualizarProducto':
        $arrayResponse = actualizarProducto($arrayRequest['data']);
        break;
    case 'eliminarProducto':
        $arrayResponse = eliminarProducto($arrayRequest['data']);
        break;
}
echo json_encode($arrayResponse, JSON_UNESCAPED_UNICODE);