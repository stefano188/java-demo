package models;

import java.io.Serializable;


public class PKZ3TBGERE implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cod_gruppo;
    private String cod_abi_istit;
    private Long id_disp_reale;
    private Long id_posi;
    private Long id_tito;

    public String getCod_gruppo() {
        return cod_gruppo;
    }
    public void setCod_gruppo(String cod_gruppo) {
        this.cod_gruppo = cod_gruppo;
    }
    public String getCod_abi_istit() {
        return cod_abi_istit;
    }
    public void setCod_abi_istit(String cod_abi_istit) {
        this.cod_abi_istit = cod_abi_istit;
    }
    public Long getId_disp_reale() {
        return id_disp_reale;
    }
    public void setId_disp_reale(Long id_disp_reale) {
        this.id_disp_reale = id_disp_reale;
    }
    public Long getId_posi() {
        return id_posi;
    }
    public void setId_posi(Long id_posi) {
        this.id_posi = id_posi;
    }
    public Long getId_tito() {
        return id_tito;
    }
    public void setId_tito(Long id_tito) {
        this.id_tito = id_tito;
    }



}
