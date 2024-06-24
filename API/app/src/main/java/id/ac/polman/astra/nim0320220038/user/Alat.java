package id.ac.polman.astra.nim0320220038.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alat {
    @SerializedName("alt_id")
    @Expose
    private int altId;

    @SerializedName("alt_merk")
    @Expose
    private String altMerk;

    public Alat(){
    }

    public Alat(int altId, String altMerk, int rgnId){
        this.altId = altId;
        this.altMerk = altMerk;
        this.rgnId = rgnId;
    }

    public int getAltId() {
        return altId;
    }

    public void setAltId(int altId) {
        this.altId = altId;
    }

    public String getAltMerk() {
        return altMerk;
    }
    public void setAltMerk(String altMerk) {
        this.altMerk = altMerk;
    }

    @SerializedName("rgn_id")
    @Expose
    private int rgnId;

    public int getRgnId() {
        return rgnId;
    }

    public void setRgnId(int rgnId) {
        this.rgnId = rgnId;
    }
}
