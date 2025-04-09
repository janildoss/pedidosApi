package com.api.pedidosApi.models;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date dataPagto;
    private Integer parcelaPagto;
    private Integer tipoRecebimento;
    private Integer idPedido;

    public Pagamento(){

    }

    public Pagamento(Integer id, Double valor, Date dataPagto, Integer parcelaPagto, Integer tipoRecebimento, Integer idPedido) {
        this.id = id;
        this.valor = valor;
        this.dataPagto = dataPagto;
        this.parcelaPagto = parcelaPagto;
        this.tipoRecebimento = tipoRecebimento;
        this.idPedido = idPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(Date dataPagto) {
        this.dataPagto = dataPagto;
    }

    public Integer getParcelaPagto() {
        return parcelaPagto;
    }

    public void setParcelaPagto(Integer parcelaPagto) {
        this.parcelaPagto = parcelaPagto;
    }

    public Integer getTipoRecebimento() {
        return tipoRecebimento;
    }

    public void setTipoRecebimento(Integer tipoRecebimento) {
        this.tipoRecebimento = tipoRecebimento;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
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
