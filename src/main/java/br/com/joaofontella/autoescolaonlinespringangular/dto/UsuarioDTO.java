package br.com.joaofontella.autoescolaonlinespringangular.dto;

import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;

public class UsuarioDTO {
    private Long prk;
    private String usuario;
    private String senha;

    public Long getPrk() {
        return prk;
    }

    public static UsuarioDTO converter(Usuario u){
        UsuarioDTO user = new UsuarioDTO();
        user.setPrk(u.getPrk());
        user.setUsuario(u.getUsuario());
        user.setSenha(u.getSenha());
        return user;
    }

    public void setPrk(Long prk) {
        this.prk = prk;
    }

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
}
