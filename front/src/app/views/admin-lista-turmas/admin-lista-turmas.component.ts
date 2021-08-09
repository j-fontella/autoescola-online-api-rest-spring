import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Turma } from 'src/app/models/turma';
import { TurmaService } from 'src/app/service/turma.service';

@Component({
  selector: 'app-admin-lista-turmas',
  templateUrl: './admin-lista-turmas.component.html',
  styleUrls: ['./admin-lista-turmas.component.scss']
})
export class AdminListaTurmasComponent implements OnInit {

  constructor(
    public turmaService : TurmaService,
    private route: Router,
  ){}

   public listaTurmas: Turma[] = [];


  ngOnInit(): void {
    this.getTurmas();
  }

  public getTurmas(){
    this.turmaService.getTurmas().subscribe(data => {
      this.listaTurmas = data as unknown as Turma[];
   })
}

public enviarTurma(prk : Number, nome : String){
  this.route.navigate(['turma'], { state: { "prk": prk, "nome" : nome } });
}


}
