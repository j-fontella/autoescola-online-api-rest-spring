package br.com.joaofontella.autoescolaonlinespringangular.dto;


import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;

public class UsuarioCadastroDTO {

    private String nome;
    private Long prk;
    private Long frkTurma;

    public UsuarioCadastroDTO(){

    }

    public UsuarioCadastroDTO(String nome, Long prk, Long frkTurma) {
        this.nome = nome;
        this.prk = prk;
        this.frkTurma = frkTurma;
    }

    public UsuarioCadastroDTO(String nome, Long prk) {
        this.nome = nome;
        this.prk = prk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPrk() {
        return prk;
    }

    public void setPrk(Long prk) {
        this.prk = prk;
    }

    public Long getFrkTurma() {
        return frkTurma;
    }

    public void setFrkTurma(Long frkTurma) {
        this.frkTurma = frkTurma;
    }
}
