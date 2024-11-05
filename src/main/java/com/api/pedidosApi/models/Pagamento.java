package com.api.pedidosApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double valor;
    private Date dataPagto;
    private Integer parcelaPagto;
    private Integer tipoRecebimento;

    public Pagamento(){

    }

    public Pagamento(Integer id, Integer tipoRecebimento, Integer parcelaPagto, Date dataPagto, Double valor) {
        this.id = id;
        this.tipoRecebimento = tipoRecebimento;
        this.parcelaPagto = parcelaPagto;
        this.dataPagto = dataPagto;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoRecebimento() {
        return tipoRecebimento;
    }

    public void setTipoRecebimento(Integer tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
    }

    public Integer getParcelaPagto() {
        return parcelaPagto;
    }

    public void setParcelaPagto(Integer parcelaPagto) {
        this.parcelaPagto = parcelaPagto;
    }

    public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
        this.dataPagto = dataPagto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;

        Pagamento pagamento = (Pagamento) o;
        return getId().equals(pagamento.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
