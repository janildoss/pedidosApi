package com.api.pedidosApi.models;

import com.api.pedidosApi.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dataPedido;
    private Integer  estadoPagamento;
    private Integer  tipoPagamento;
    private Integer numParcela;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="ESTADOPAGAMENTO")
    private Set<String>estPagamento = new HashSet<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemPedido>itens = new HashSet<>();

    public Pedido(){
    }

    public Pedido(Integer id, LocalDateTime dataPedido, EstadoPagamento estadoPagamento, Integer tipoPagamento, Integer numParcela) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.estadoPagamento = estadoPagamento.getCod() ;
        this.tipoPagamento = tipoPagamento;
        this.numParcela = numParcela;
    }

    public void setEstadoPagamento(Integer estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItens() {
         return itens ;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Set<String> getEstPagamento() {
        return estPagamento;
    }

    public void setEstPagamento(Set<String> estPagamento) {
        this.estPagamento = estPagamento;
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

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
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

