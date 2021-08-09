import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminCadastroComponent } from './views/admin-cadastro/admin-cadastro/admin-cadastro.component';
import { AdminListaTurmasComponent } from './views/admin-lista-turmas/admin-lista-turmas.component';
import { AdminComponent } from './views/admin/admin/admin.component';
import { AulaComponent } from './views/aulas/aula.component';
import { HomeComponent } from './views/home/home.component';
import { TurmaComponent } from './views/turma/turma.component';
import {AuthguardService} from "./service/authguard.service";

const routes: Routes = [
  {
    path : '',
    component : HomeComponent
  },
  {
    path : 'aulas',
    component : AulaComponent,
    canActivate : [AuthguardService]
  },
  {
    path : 'admin',
    component : AdminComponent,
    canActivate : [AuthguardService]
  },
  {
    path : 'admincadastro',
    component : AdminCadastroComponent,
    canActivate : [AuthguardService]
  },
  {
    path : 'adminturmalista',
    component : AdminListaTurmasComponent,
    canActivate : [AuthguardService]
  },
  {
    path : "turma",
    component : TurmaComponent,
    canActivate : [AuthguardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
