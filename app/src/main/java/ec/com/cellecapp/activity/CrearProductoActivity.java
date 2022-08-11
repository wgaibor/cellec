package ec.com.cellecapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ec.com.cellecapp.CellecApplication;
import ec.com.cellecapp.R;
import ec.com.cellecapp.entity.ItemEntity;
import ec.com.cellecapp.entity.RequestEntity;
import ec.com.cellecapp.entity.ResponseEntity;
import ec.com.cellecapp.interfaces.ICellec;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearProductoActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etMarca;
    EditText etModelo;
    EditText etDescripcion;
    EditText etPrecio;
    EditText etImagen;

    Button btnGuardar;
    Button btnCancelar;

    Call<ResponseEntity> callMetodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);
        etMarca = findViewById(R.id.et_marca);
        etModelo = findViewById(R.id.et_modelo);
        etDescripcion = findViewById(R.id.et_descripcion);
        etPrecio = findViewById(R.id.et_precio);
        etImagen = findViewById(R.id.et_imagen);

        btnCancelar = findViewById(R.id.btn_cancelar);
        btnGuardar = findViewById(R.id.btn_guardar);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_guardar:
                llamarAlWs();
                break;
            case R.id.btn_cancelar:
                finish();
                break;
        }
    }

    private void llamarAlWs() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setMarca(etMarca.getText().toString());
        itemEntity.setModelo(etModelo.getText().toString());
        itemEntity.setDescripcion(etDescripcion.getText().toString());
        itemEntity.setPrecio(etPrecio.getText().toString());
        itemEntity.setEstado(getString(R.string.estadoActivo));
        itemEntity.setImagen(etImagen.getText().toString());
        RequestEntity request = new RequestEntity();
        request.setOp(getString(R.string.op_guardarProductos));
        request.setData(itemEntity);
        ICellec iCellec = CellecApplication.getmInstance().getRestAdapter().create(ICellec.class);
        callMetodo = iCellec.invocarMetodo(request);
        callMetodo.enqueue(new Callback<ResponseEntity>() {
            @Override
            public void onResponse(Call<ResponseEntity> call, Response<ResponseEntity> response) {
                if(response.isSuccessful()){
                    ResponseEntity objRespuesta = response.body();
                    mostrarMensaje(objRespuesta);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity> call, Throwable t) {
                Log.e("*******", t.getMessage());
            }
        });
    }

    private void mostrarMensaje(ResponseEntity objRespuesta) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(objRespuesta.getStatus());
        alertBuilder.setMessage(objRespuesta.getMensaje());
        alertBuilder.setPositiveButton("OK", (dialogInterface, i) -> finish());
        alertBuilder.create().show();
    }
}