package ru.sbrf.android.dayphoto.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseOuterEntity extends BaseEntity {
    @Expose
    @SerializedName("id")
    private long id;

    public BaseOuterEntity(long id) {
        this.id = id;
    }


    public BaseOuterEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        BaseOuterEntity baseEntity = (BaseOuterEntity) o;
        return id == (baseEntity.id);
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
