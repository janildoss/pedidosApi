package com.api.pedidosApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


    @Entity
    public class Produto implements Serializable {
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String nome;

        @NotNull
        @Min(value = 0, message = "O preço deve ser maior que zero")
        private Double preco;

        @JsonBackReference
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "PRODUTO_CATEGORIA",
                joinColumns = @JoinColumn(name = "produto_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id")
        )
        private List<Categoria> categorias = new ArrayList<>();

        public Produto() {}

        public Produto(Integer id, @NotNull String nome, @NotNull Double preco) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public @NotNull @Min(value = 0, message = "O preço deve ser maior que zero") Double getPreco() {
            return preco;
        }

        public void setPreco(@NotNull @Min(value = 0, message = "O preço deve ser maior que zero") Double preco) {
            this.preco = preco;
        }

        public List<Categoria> getCategorias() {
            return categorias;
        }

        public void setCategorias(List<Categoria> categorias) {
            this.categorias = categorias;
        }


        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Produto)) return false;

            Produto produto = (Produto) o;
            return getId().equals(produto.getId());
        }

        @Override
        public int hashCode() {
            return getId().hashCode();
        }

    }
