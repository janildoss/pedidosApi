package com.api.pedidosApi.DTO;

import com.api.pedidosApi.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PedidoPostDTO {


    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime dataPedido;
    private Integer estadoPagamento;
    private Integer tipoPagamento;
    private Integer numParcela;
    private Integer clienteId;
    private Integer enderecoDeEntregaId;

    public PedidoPostDTO() {
    }

    public LocalDateTime  getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime  dataPedido) {
        this.dataPedido = dataPedido;
    }

    public EstadoPagamento getEstadoPagamento() {return EstadoPagamento.toEnum(estadoPagamento);  }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.estadoPagamento = estadoPagamento.getCod();  }

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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getEnderecoDeEntregaId() {
        return enderecoDeEntregaId;
    }

    public void setEnderecoDeEntregaId(Integer enderecoDeEntregaId) {
        this.enderecoDeEntregaId = enderecoDeEntregaId;
    }
}
