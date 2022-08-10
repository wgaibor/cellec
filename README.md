# cellec  

## Marvel App  
https://marvelapp.com/prototype/ich306a/screen/88071555  

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
        "estado" : "Activo"
    }
}' 
  

curl --location --request POST 'http://localhost:3005/cellec.php' \
--header 'Content-Type: application/json' \
--data-raw '{
    "op": "obtenerProductos",
    "data": {
        "estado" : "Activo"
    }
}' ```  
  
