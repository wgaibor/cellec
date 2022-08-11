package ec.com.cellecapp.entity;

import java.util.List;

public class ProductoRespEntity {
    private List<ItemEntity> data;

    public List<ItemEntity> getData() {
        return data;
    }

    public void setData(List<ItemEntity> data) {
        this.data = data;
    }
}
