package br.com.joaofontella.autoescolaonlinespringangular.model;

import javax.persistence.*;

@Entity
@Table(name = "Turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prk")
    private Long prk;

    @Column(name = "vagas")
    private Integer vagas;

    @Column(name = "disciplina")
    private String disciplina;


    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setPrk(Long prk) {
        this.prk = prk;
    }

    public Long getPrk() {
        return prk;
    }
}
