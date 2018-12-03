package ru.sbrf.android.dayphoto.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseOuterEntity {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("email")
    private String email;

    public User() {

    }

    public User(Long id, String name, String password, String email) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
