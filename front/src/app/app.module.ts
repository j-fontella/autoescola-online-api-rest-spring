import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {CardModule} from 'primeng/card';
import {ButtonModule} from 'primeng/button';
import {HttpClientModule} from '@angular/common/http'
import {OrderListModule} from 'primeng/orderlist';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './views/home/home.component';
import { AulaComponent } from './views/aulas/aula.component';
import { AdminComponent } from './views/admin/admin/admin.component';
import { AdminCadastroComponent } from './views/admin-cadastro/admin-cadastro/admin-cadastro.component';
import { AdminListaTurmasComponent } from './views/admin-lista-turmas/admin-lista-turmas.component';
import { TurmaComponent } from './views/turma/turma.component';
import { CadastraAlunoTurmaComponent } from './views/cadastra-aluno-turma/cadastra-aluno-turma.component';
import {DropdownModule} from 'primeng/dropdown';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AulaComponent,
    AdminComponent,
    AdminCadastroComponent,
    AdminListaTurmasComponent,
    TurmaComponent,
    CadastraAlunoTurmaComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    InputTextModule,
    CardModule,
    ButtonModule,
    HttpClientModule,
    OrderListModule,
    DropdownModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
