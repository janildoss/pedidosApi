package com.api.pedidosApi.models;

import com.api.pedidosApi.Enums.EstadoPagamento;
//import com.api.pedidosApi.Enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Pedido  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Date dataPedido;
    private Integer  estadoPagamento;
   // private EstadoPagamento estadoPagamento;
    private Integer  tipoPagamento;
    private Integer numParcela;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ElementCollection
    @CollectionTable(name="ESTADOPAGAMENTO")
    private Set<String>estPagamento = new HashSet<>();

    public Pedido(){
    }


    public Pedido(Integer id, Date dataPedido, EstadoPagamento estadoPagamento, Integer tipoPagamento, Integer numParcela) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.estadoPagamento = estadoPagamento.getCod() ;
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

            public EstadoPagamento getEstadoPagamento() {
                  return EstadoPagamento.toEnum(estadoPagamento);
            }

            public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
                    this.estadoPagamento = estadoPagamento.getCod();
            }

    public Date getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public boolean isPendente(){
       return (getEstadoPagamento().getCod() == 1 );
    }
    public boolean isQuitado(){
        return (getEstadoPagamento().getCod() == 2) ;
    }
    public boolean isCancelado(){
        return (getEstadoPagamento().getCod() == 3) ;
    }

    public void setQuitado() {
        this.estadoPagamento = EstadoPagamento.QUITADO.getCod();;
    }
    public void setCancelado() {
        this.estadoPagamento = EstadoPagamento.CANCELADO.getCod();;
    }
    public void setPendente() {
        this.estadoPagamento = EstadoPagamento.PENDENTE.getCod();
    }

    public boolean isAvista(){
        return (tipoPagamento == 1);
    }
    public boolean isCartaoAvista(){
        return (tipoPagamento == 2);
    }
    public boolean isCartaoAprazo(){
        return (tipoPagamento == 3);
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
