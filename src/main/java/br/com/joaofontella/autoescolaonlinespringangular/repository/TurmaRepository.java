package br.com.joaofontella.autoescolaonlinespringangular.repository;

import br.com.joaofontella.autoescolaonlinespringangular.dto.UsuarioCadastroDTO;
import br.com.joaofontella.autoescolaonlinespringangular.model.Turma;
import br.com.joaofontella.autoescolaonlinespringangular.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {


    @Query(value = "SELECT usuario, frkUsuario FROM alunocadastradoturma INNER JOIN Usuario on alunocadastradoturma.frkUsuario = Usuario.prk WHERE alunocadastradoturma.frkTurma = :frkTurma", nativeQuery = true)
    Optional<List<String>> findByTurma(Long frkTurma);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "insert into alunocadastradoturma (frkUsuario,frkTurma) "
            + "VALUES (:#{#us.prk},:#{#us.frkTurma})", nativeQuery = true)
     void cadastrarUsuario(@Param("us") UsuarioCadastroDTO us);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update turma set vagas = vagas - 1 where prk = :prkTurma", nativeQuery = true)
    void removerVaga(Long prkTurma);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from alunocadastradoturma where frkturma = :frkTurma and frkUsuario = :frkUsuario", nativeQuery = true)
    void removerAlunoTurma(Long frkTurma, Long frkUsuario);

    @Query(value = "SELECT disciplina FROM alunocadastradoturma INNER JOIN Usuario on alunocadastradoturma.frkUsuario = Usuario.prk inner join Turma on Turma.prk = alunocadastradoturma.frkTurma WHERE Usuario.prk = :frkAluno", nativeQuery = true)
    Optional<List<String>> findByAluno(Long frkAluno);



}
