package com.api.pedidosApi.DTO;


public class ItemPedidoDTO {
    private Double desconto;
    private Integer quantidade;
    private Double preco;
    private Integer idPedido;
    private Integer idProduto;

    public ItemPedidoDTO(Double desconto, Integer quantidade, Double preco, Integer idPedido, Integer idProduto) {
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
    }

    // Getters e setters
    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }

}

