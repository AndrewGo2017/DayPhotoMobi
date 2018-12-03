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
}
