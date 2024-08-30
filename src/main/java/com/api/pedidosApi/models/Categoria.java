package com.api.pedidosApi.models;
<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
>>>>>>> 86b7e346c90ad3f2f37eaad86035b98477a82ae2
import java.io.Serializable;
<<<<<<< HEAD
import java.util.Objects;
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> ea656e771e7208f1728e382f8c41beb77eb61961
@Entity
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Integer id;
    private String nome;
    @ManyToMany(mappedBy="categorias")

    @JsonIgnore
    //@JsonManagedReference
    //@JsonBackReference
    private List<Produto> produtos = new ArrayList();

    public Categoria() {
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

   public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;

        Categoria categoria = (Categoria) o;
        return getId().equals(categoria.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
