package com.api.pedidosApi.models;

import com.api.pedidosApi.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

   //@JsonIgnore
   @ElementCollection(fetch=FetchType.EAGER)
   @CollectionTable(name="ESTADOPAGAMENTO")
   private Set<String>estPagamento = new HashSet<>();

   /*          @JsonIgnore
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemPedido>itens = new HashSet<>();
   //*********************************************************************************************
   @JsonIgnore
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Pagamento> pagamentos = new HashSet<>();
*/
    // Relação com Endereco
          //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    public Pedido(){
    }

    public Pedido(Integer id, LocalDateTime dataPedido, Integer estadoPagamento, Integer tipoPagamento, Integer numParcela, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.estadoPagamento = estadoPagamento;
        this.tipoPagamento = tipoPagamento;
        this.numParcela = numParcela;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

   /* public Integer getEstadoPagamento() {
        return estadoPagamento;
    }

    public void setEstadoPagamento(Integer estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }*/
    public EstadoPagamento getEstadoPagamento() {return EstadoPagamento.toEnum(estadoPagamento);  }
    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {this.estadoPagamento = estadoPagamento.getCod();  }

    public Integer getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Integer tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }
    public boolean isPendente(){ return (getEstadoPagamento().getCod() == 1 );    }
    public boolean isQuitado(){
        return (getEstadoPagamento().getCod() == 2) ;
    }
    public boolean isCancelado(){
        return (getEstadoPagamento().getCod() == 3) ;
    }
    public boolean isCartaoAprazo(){
        return (tipoPagamento == 3);
    }
    public boolean isAvista(){
        return (tipoPagamento == 1);
    }
    public boolean isCartaoAvista(){
        return (tipoPagamento == 2);
    }
    public void setQuitado() { this.estadoPagamento = EstadoPagamento.QUITADO.getCod();  }
    public void setCancelado() {     this.estadoPagamento = EstadoPagamento.CANCELADO.getCod();  }
    public void setPendente() {   this.estadoPagamento = EstadoPagamento.PENDENTE.getCod();    }

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

