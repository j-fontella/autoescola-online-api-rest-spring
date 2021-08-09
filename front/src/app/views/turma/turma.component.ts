import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TurmaService } from 'src/app/service/turma.service';

@Component({
  selector: 'app-turma',
  templateUrl: './turma.component.html',
  styleUrls: ['./turma.component.scss']
})
export class TurmaComponent implements OnInit {


  constructor(
    public turmaService : TurmaService,
    private route: Router,
  ){}

  public listaAlunos: any[] = [];
  public listaPrks : Number[] = [];
  public listaElegiveis : any[] = [];
  public usuarioCadastrado : any = [];
  public prkTurma : number = 0




  ngOnInit(): void {
    this.asd();
  }


public asd(){
  let prk : any = this.route
    let titulo = document.querySelector("#tituloturma") as HTMLInputElement
    let texto = prk.lastSuccessfulNavigation.extras.state?.nome
    if(texto){
        titulo.innerHTML = texto
    }
    let prk2 = prk.lastSuccessfulNavigation.extras.state?.prk;
    if(!isNaN(prk2)){
      this.prkTurma = prk2
      this.turmaService.getAlunosTurma(prk2).subscribe(data =>{
        this.listaAlunos = data as unknown as String[];
        this.listaAlunos.forEach((i) =>{
          this.listaPrks.push(i?.prk)
        })
        console.log(this.listaPrks)
        this.cadastrarAlunos();
      },error =>{
        alert(error.error)
      })

    }
}

public cadastrarAlunos(){
  this.turmaService.getElegiveis(this.listaPrks).subscribe(data =>{
    let el : any = data
    this.listaElegiveis = el
    console.log(this.listaElegiveis)
  },error =>{
    alert(error.error)
  })
}

public excluirAluno(prk : any){
  this.turmaService.removerAlunoTurma(this.prkTurma,prk).subscribe(data =>{
    alert("Usuário removido")
    this.route.navigate(["adminturmalista"])
  },error =>{
    alert(error.error)
  })

}

public cadastrarAlunoTurma(){
  let inputid = document?.querySelector("#elegs") as HTMLInputElement;
  if(inputid && !isNaN(this.prkTurma)){
    console.log(parseInt(inputid.value))
    console.log(this.prkTurma)
    this.turmaService.cadastrarAlunoTurma(parseInt(inputid.value), this.prkTurma).subscribe(data =>{
      alert("Usuário cadastrado")
      this.route.navigate(["adminturmalista"])
    }, error => {
      alert(error.error)
      console.log(error)
    })
  }else{
    alert("Preencha os campos corretamente")
  }
}




}
