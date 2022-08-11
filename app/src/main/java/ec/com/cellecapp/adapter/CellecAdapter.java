package ec.com.cellecapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ec.com.cellecapp.R;
import ec.com.cellecapp.activity.DetalleActivity;
import ec.com.cellecapp.entity.ItemEntity;
import ec.com.cellecapp.util.Utils;

public class CellecAdapter extends RecyclerView.Adapter<CellecAdapter.ViewHolder>{

    private List<ItemEntity> lstItems;
    private Context _ctx;

    public CellecAdapter(List<ItemEntity> lstItems, Context _ctx) {
        this.lstItems = lstItems;
        this._ctx = _ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cellec_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemEntity itemEntity = lstItems.get(position);
        holder.tvDescripcion.setText(itemEntity.getDescripcion());
        holder.tvPrecio.setText(itemEntity.getPrecio());
        Utils.loadImage(itemEntity.getImagen(), holder.ivImagenMobile);
        holder.cvProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_ctx.getApplicationContext(), DetalleActivity.class);
                intent.putExtra("_imagen", itemEntity.getImagen());
                intent.putExtra("_detalle", itemEntity.getDescripcion());
                _ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivImagenMobile;
        private TextView tvDescripcion;
        private TextView tvPrecio;
        private CardView cvProductos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImagenMobile = (ImageView) itemView.findViewById(R.id.iv_ImagenMobile);
            this.tvDescripcion = (TextView) itemView.findViewById(R.id.tv_Descripcion);
            this.tvPrecio = (TextView) itemView.findViewById(R.id.tv_Precio);
            this.cvProductos = (CardView) itemView.findViewById(R.id.cv_Productos);
        }
    }
}
