package com.api.pedidosApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pedido  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Date dataPedido;
    private Integer estadoPagamento;
    private Integer  tipoPagamento;
    private Integer numParcela;

    public Pedido(){
    }

    public Pedido(Integer id, Date dataPedido, Integer estadoPagamento, Integer tipoPagamento, Integer numParcela) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.estadoPagamento = estadoPagamento;
        this.tipoPagamento = tipoPagamento;
        this.numParcela = numParcela;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Integer getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Integer tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getEstadoPagamento() {
        return estadoPagamento;
    }

    public void setEstadoPagamento(Integer estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;
        return getId().equals(pedido.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
