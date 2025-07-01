package com.api.pedidosApi.DTO;


public class PedidoStatusDTO {
    private Integer pedidoId;
    private String statusAtual;
    private String mensagem;

    public PedidoStatusDTO() {
    }

    public PedidoStatusDTO(Integer pedidoId, String statusAtual, String mensagem) {
        this.pedidoId = pedidoId;
        this.statusAtual = statusAtual;
        this.mensagem = mensagem;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
