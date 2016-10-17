package com.mantambakberas.iamantaras.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by winnerawan on 10/17/16.
 */

public class Users {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("angkatan")
    @Expose
    private Integer angkatan;
    @SerializedName("jurusan")
    @Expose
    private String jurusan;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     *
     * @param foto
     * The foto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     *
     * @return
     * The angkatan
     */
    public Integer getAngkatan() {
        return angkatan;
    }

    /**
     *
     * @param angkatan
     * The angkatan
     */
    public void setAngkatan(Integer angkatan) {
        this.angkatan = angkatan;
    }

    /**
     *
     * @return
     * The jurusan
     */
    public String getJurusan() {
        return jurusan;
    }

    /**
     *
     * @param jurusan
     * The jurusan
     */
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

}
