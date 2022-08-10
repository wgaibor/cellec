# cellec  

## Marvel App  
https://marvelapp.com/prototype/ich306a/screen/88071555  

## Documentación de php  
https://www.php.net/manual/en/mysqli-stmt.bind-param.php  

## Métodos del web services  

```
curl --location --request POST 'http://localhost:3005/cellec.php' \
--header 'Content-Type: application/json' \
--data-raw '{
    "op": "guardarProductos",
    "data": {
        "marca" : "Samsung",
        "modelo" : "A22",
        "descripcion" : "Samsung Galaxy A22",
        "precio": "290,99",
        "estado" : "Activo",
        "imagen": "https://celumania.com.ec/wp-content/uploads/2021/10/SAMSUNG-GALAXY-A22.png"
    }
}' 
  

curl --location --request POST 'http://localhost:3005/cellec.php' \
--header 'Content-Type: application/json' \
--data-raw '{
    "op": "obtenerProductos",
    "data": {
        "estado" : "Activo"
    }
}'  
  
curl --location --request POST 'http://localhost:3005/cellec.php' \
--header 'Content-Type: application/json' \
--data-raw '{
    "op": "actualizarProducto",
    "data": {
        "marca" : "Samsung",
        "modelo" : "A22",
        "descripcion" : "Samsung Galaxy A22",
        "precio" : "290,99",
        "estado" : "Activo",
        "imagen" : "https://celumania.com.ec/wp-content/uploads/2021/10/SAMSUNG-GALAXY-A22.png",
        "id" : 5
    }
}'  
  
curl --location --request POST 'http://localhost:3005/cellec.php' \
--header 'Content-Type: application/json' \
--data-raw '{
    "op": "eliminarProducto",
    "data": {
        "id" : 5
    }
}'  
  


  
