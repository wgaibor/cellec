package ec.com.cellecapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ec.com.cellecapp.R;
import ec.com.cellecapp.util.Utils;

public class DetalleActivity extends AppCompatActivity implements View.OnClickListener {

    String urlImages, description;
    ImageView ivProducto;
    TextView tvDetalle;
    Button btnComprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            urlImages = extras.getString("_imagen");
            description = extras.getString("_detalle");
        }
        ivProducto = findViewById(R.id.iv_Producto);
        tvDetalle = findViewById(R.id.tv_Detalle);
        Utils.loadImage(urlImages, ivProducto);
        tvDetalle.setText(description);
        btnComprar = findViewById(R.id.btn_comprar);
        btnComprar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_comprar:
                mostrarMensaje();
                break;
        }
    }

    private void mostrarMensaje() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Compra exitosa");
        alertDialogBuilder.setMessage("La compra se ha realizado con exito");
        alertDialogBuilder.setPositiveButton("Gracias", (dialogInterface, i) -> finish());
        alertDialogBuilder.create().show();
    }
}