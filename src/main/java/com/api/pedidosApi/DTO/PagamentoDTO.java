package com.api.pedidosApi.DTO;

import com.api.pedidosApi.models.Pagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;


public class PagamentoDTO {
    private Integer id;
    private Double valor;
    @JsonFormat(pattern="dd/MM/yyyy ")
    private LocalDateTime dataPagto;
    private Integer parcelaPagto;
    private Integer tipoRecebimento;

    public PagamentoDTO(Integer id, Double valor, LocalDateTime dataPagto, Integer parcelaPagto, Integer tipoRecebimento) {
        this.id = id;
        this.valor = valor;
        this.dataPagto = dataPagto;
        this.parcelaPagto = parcelaPagto;
        this.tipoRecebimento = tipoRecebimento;
    }

    /* public PagamentoDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.valorPago = pagamento.getValorPago(); // Exemplo
        this.dataPagamento = pagamento.getDataPagamento(); // Exemplo
    }*/

    // Getters e setters


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

    public LocalDateTime getDataPagto() {
        return dataPagto;
    }

    public void setDataPagto(LocalDateTime dataPagto) {
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
}
