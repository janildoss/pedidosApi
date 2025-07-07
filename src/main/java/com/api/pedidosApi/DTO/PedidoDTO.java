package com.api.pedidosApi.DTO;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.models.Pedido;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PedidoDTO implements Serializable {
    private Integer id;
    private LocalDateTime dataPedido;
    private String estadoPagamento;
    private Integer tipoPagamento;
    private Integer numParcela;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @ManyToOne
   @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="ESTADOPAGAMENTO")
    private Set<String>estPagamento = new HashSet<>();

    public PedidoDTO() {
    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.estadoPagamento = pedido.getEstadoPagamento().getDescricao();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.numParcela = pedido.getNumParcela();
        this.enderecoDeEntrega = pedido.getEnderecoDeEntrega();
        this.cliente = pedido.getCliente();
    }
    // Getters e Setters
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

    public String getEstadoPagamento() {
        return estadoPagamento.toString();

    }

    public void setEstadoPagamento(String estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }

    public String getTipoPagamento() {
        if (tipoPagamento == null) return null;
        switch (tipoPagamento) {
            case 1: return "À VISTA";
            case 2: return "CARTÃO À VISTA";
            case 3: return "CARTÃO PARCELADO";
            default: return "DESCONHECIDO";
        }

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

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private String tipoPagamentoToString(Integer tipo) {
        if (tipo == null) return null;
        switch (tipo) {
            case 1: return "À VISTA";
            case 2: return "CARTÃO À VISTA";
            case 3: return "CARTÃO PARCELADO";
            default: return "DESCONHECIDO";
        }
    }
}
