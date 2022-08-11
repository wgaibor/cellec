package ec.com.cellecapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ec.com.cellecapp.CellecApplication;
import ec.com.cellecapp.R;
import ec.com.cellecapp.adapter.CellecAdapter;
import ec.com.cellecapp.entity.ItemEntity;
import ec.com.cellecapp.entity.ProductoRespEntity;
import ec.com.cellecapp.entity.RequestEntity;
import ec.com.cellecapp.interfaces.ICellec;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VentasActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    RecyclerView rvItem;
    SwipeRefreshLayout srlActualizar;
    Call<ProductoRespEntity> callLstProductos;
    Button btnCrearServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        rvItem = findViewById(R.id.rv_Item);
        srlActualizar = findViewById(R.id.srl_actualizar);
        btnCrearServicio = findViewById(R.id.btn_crear_servicio);
        srlActualizar.setOnRefreshListener(this);
        btnCrearServicio.setOnClickListener(this);
        llamarAlWs();
    }

    private void llamarAlWs() {
        ICellec iCellec = CellecApplication.getmInstance().getRestAdapter().create(ICellec.class);
        ItemEntity itemRequest = new ItemEntity();
        itemRequest.setEstado("Activo");
        RequestEntity objRequestEntity = new RequestEntity();
        objRequestEntity.setOp("obtenerProductos");
        objRequestEntity.setData(itemRequest);
        callLstProductos = iCellec.getProductos(objRequestEntity);
        callLstProductos.enqueue(new Callback<ProductoRespEntity>() {
            @Override
            public void onResponse(Call<ProductoRespEntity> call, Response<ProductoRespEntity> response) {
                if(response.isSuccessful()){
                    ProductoRespEntity productos = response.body();
                    llenarListado(productos.getData());
                    srlActualizar.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ProductoRespEntity> call, Throwable t) {
                Log.e(" >>>>>>> ", t.getMessage());
                srlActualizar.setRefreshing(false);
            }
        });
    }

    private void llenarListado(List<ItemEntity> lstServicios) {
        CellecAdapter cellecAdapter = new CellecAdapter(lstServicios, this);
        rvItem.setHasFixedSize(true);
        rvItem.setAdapter(cellecAdapter);
    }

    @Override
    public void onRefresh() {
        llamarAlWs();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_crear_servicio:
                Intent intent = new Intent(this, CrearProductoActivity.class);
                startActivity(intent);
                break;
        }
    }
}