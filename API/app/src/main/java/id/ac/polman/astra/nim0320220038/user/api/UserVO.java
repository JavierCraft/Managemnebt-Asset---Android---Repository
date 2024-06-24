package id.ac.polman.astra.nim0320220038.user.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class UserVO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("proId")
    @Expose
    private int proId;

    public UserVO() {
        this.id = UUID.randomUUID().toString();
        this.username = "";
        this.nama = "";
        this.email = "";
        this.role = "";
        this.status = 0;
        this.proId = 0;
    }

    public UserVO(String id, String username, String nama, String email, String role, int status, int proId) {
        this.id = id;
        this.username = username;
        this.nama = nama;
        this.email = email;
        this.role = role;
        this.status = status;
        this.proId = proId;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }
}
