package com.api.pedidosApi.models;

import com.api.pedidosApi.Enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

   // @JsonManagedReference
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    //private List<Pedido> pedidos  = new ArrayList<>();;
   //@OneToMany(mappedBy = "cliente")

   //@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
   //@JsonIgnore
   //private List<Pedido> pedidos = new ArrayList<>();

    //@JsonManagedReference

   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private Set<Endereco>enderecos = new HashSet<>();

   //private List<Endereco> enderecos = new ArrayList<>();

    //@JsonIgnore
   @ElementCollection(fetch=FetchType.EAGER)
   @CollectionTable(name = "telefone",
           joinColumns = @JoinColumn(name = "cliente_id"))
   private Set<String> telefones = new HashSet<>();

    public Cliente(){
    }

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCod() ;
    }


   // @ElementCollection
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

   // public List<Pedido> getPedidos() {
   //     return pedidos;
   // }

   // public void setPedidos(List<Pedido> pedidos) {
   //     this.pedidos = pedidos;
  //  }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo) ;
    }
    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod() ;
    }

   public Set<Endereco> getEnderecos() {
       return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
       this.telefones = telefones;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;

        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    //public void setTipo(Integer tipo) {
     //   this.tipo = tipo;
  // }

    //public List<Pedido> getPedidos() {
     //   return pedidos;
    //}

   // public void setPedidos(List<Pedido> pedidos) {
   //     this.pedidos = pedidos;
 //   }

}
