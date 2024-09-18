package com.api.pedidosApi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToMany(mappedBy = "produtos")
    private List<Categoria> categorias = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(Integer id, String nome, List<Categoria> categorias) {
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return id != null && id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }
}