import { Component, OnInit } from '@angular/core';
import { TurmaService } from 'src/app/service/turma.service';

@Component({
  selector: 'app-admin-cadastro',
  templateUrl: './admin-cadastro.component.html',
  styleUrls: ['./admin-cadastro.component.scss']
})
export class AdminCadastroComponent implements OnInit {

  constructor(public turmaSerice : TurmaService) { }

  ngOnInit(): void {
  }

  public registrarTurma(){
    let disciplinaInput = document.querySelector("#disciplina") as HTMLInputElement;
    let vagasInput = document.querySelector("#vagas") as HTMLInputElement;

    if(disciplinaInput && vagasInput && disciplinaInput.value && vagasInput.value){
      let disciplina = disciplinaInput.value;
      let vagas = parseInt(vagasInput.value);
      this.turmaSerice.cadastrarTurma(vagas, disciplina).subscribe(data =>{
        alert("Turma cadastrada")
      },error =>{
        alert(error.error)
      })

    }else{
      alert("Para efetuar um registro de turma você deve inserir disciplina e numero de vagas válidos.");
      return;
    }
  }


}
