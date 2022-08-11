package ec.com.cellecapp.interfaces;

import ec.com.cellecapp.entity.ProductoRespEntity;
import ec.com.cellecapp.entity.RequestEntity;
import ec.com.cellecapp.entity.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ICellec {

    @POST("cellec.php")
    Call<ProductoRespEntity> getProductos(@Body RequestEntity request);

    @POST("cellec.php")
    Call<ResponseEntity> invocarMetodo(@Body RequestEntity request);
}
