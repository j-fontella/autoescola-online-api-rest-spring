package br.com.joaofontella.autoescolaonlinespringangular.model;


import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prk")
    private Long prk;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;



    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPrk(Long prk) {
        this.prk = prk;
    }

    public Long getPrk() {
        return prk;
    }
}
