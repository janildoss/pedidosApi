package com.api.pedidosApi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double valor;

    @JsonFormat(pattern="dd/MM/yyyy ")
    private LocalDateTime dataPagto;
    private Integer parcelaPagto;
    private Integer tipoRecebimento;

//@JsonIgnore
    @ManyToOne
    @JoinColumn(name="pedido_id") // FK no banco
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, Double valor, LocalDateTime dataPagto, Integer parcelaPagto, Integer tipoRecebimento, Pedido pedido) {
        this.id = id;
        this.valor = valor;
        this.dataPagto = dataPagto;
        this.parcelaPagto = parcelaPagto;
        this.tipoRecebimento = tipoRecebimento;
        this.pedido = pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Integer getId() {return id;  }

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
