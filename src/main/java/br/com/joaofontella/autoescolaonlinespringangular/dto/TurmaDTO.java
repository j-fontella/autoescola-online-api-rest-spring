package br.com.joaofontella.autoescolaonlinespringangular.dto;

import br.com.joaofontella.autoescolaonlinespringangular.model.Turma;
import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;

public class TurmaDTO {
    private Long prk;
    private Integer vagas;
    private String disciplina;

    public static TurmaDTO converter(Turma t){
        TurmaDTO turma = new TurmaDTO();
        turma.setPrk(t.getPrk());
        turma.setVagas(t.getVagas());
        turma.setDisciplina(t.getDisciplina());
        return turma;
    }

    public Long getPrk() {
        return prk;
    }

    public void setPrk(Long prk) {
        this.prk = prk;
    }

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
}
