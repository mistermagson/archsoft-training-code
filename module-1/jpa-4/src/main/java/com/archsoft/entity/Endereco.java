package com.archsoft.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB002_ENDERECO")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 338330163884724277L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_endereco")
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "RUA", nullable = false)
    private String rua;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "CIDADE", nullable = false)
    private String cidade;

    @Column(name = "ESTADO", nullable = false)
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getRua() + ", " + getCidade() + ", " + getEstado() + ", " + getCep();
    }
}
