import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Turma } from 'src/app/models/turma';
import { TurmaService } from 'src/app/service/turma.service';

@Component({
  selector: 'app-aula',
  templateUrl: './aula.component.html',
  styleUrls: ['./aula.component.scss']
})
export class AulaComponent implements OnInit {

  public usuarioLogado = "";
  public prkUsuarioLogado = 0;

  constructor(
    public turmaService : TurmaService,
    private route: Router,
  ){}

   public listaTurmas: Turma[] = [];


  ngOnInit(): void {
    let rota : any = this.route
    let texto = rota.lastSuccessfulNavigation.extras.state?.nome
    let prk = rota.lastSuccessfulNavigation.extras.state?.prk
    this.usuarioLogado = texto
    this.prkUsuarioLogado = prk
    this.getTurmas();
  }

  public getTurmas(){
    this.turmaService.getTurmasPorUsuario(this.prkUsuarioLogado).subscribe(data => {
      this.listaTurmas = data as unknown as Turma[];
   })
}



}
