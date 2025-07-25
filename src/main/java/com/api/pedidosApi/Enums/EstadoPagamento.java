package com.api.pedidosApi.Enums;

public enum EstadoPagamento {
    PENDENTE(1, "PENDENTE"),
    QUITADO(2,"QUITADO"),
    CANCELADO(3,"CANCELADO");
    private int cod;
    private String descricao;

    private EstadoPagamento(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(EstadoPagamento itemEstPagamento : EstadoPagamento.values()){
            if(cod.equals(itemEstPagamento.getCod())){
                return itemEstPagamento;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }

}
