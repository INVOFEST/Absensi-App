package invofest.com.app.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("nama") private String nama;
    @SerializedName("asal_institusi") private String asal_institusi;
    @SerializedName("id_peserta") private String id_peserta;
    @SerializedName("jenis_workshop") private String jenis_workshop;

    public User() {
    }

    public User(String nama, String asal_institusi, String id_peserta, String jenis_workshop) {
        this.nama = nama;
        this.asal_institusi = asal_institusi;
        this.id_peserta = id_peserta;
        this.jenis_workshop = jenis_workshop;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsal_institusi() {
        return asal_institusi;
    }

    public void setAsal_institusi(String asal_institusi) {
        this.asal_institusi = asal_institusi;
    }

    public String getId_peserta() {
        return id_peserta;
    }

    public void setId_peserta(String id_peserta) {
        this.id_peserta = id_peserta;
    }

    public String getJenis_workshop() {
        return jenis_workshop;
    }

    public void setJenis_workshop(String jenis_workshop) {
        this.jenis_workshop = jenis_workshop;
    }
}
