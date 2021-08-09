import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Turma } from '../models/turma';
import { UsuarioSpl } from '../models/usuariospl';

@Injectable({
  providedIn: 'root'
})
export class TurmaService {

  apiUrl = "http://localhost:8080/turmas/";

  httpOptions = {
    headers : new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private httpClient : HttpClient) { }


  public cadastrarTurma(vagas:Number, disciplina:String){
    let uri = this.apiUrl + "registrar/"
    return this.httpClient.post(uri, {"vagas" : vagas, "disciplina" : disciplina})
  }

  public cadastrarAlunoTurma(frkUsuario : number, frkTurma : number){
    let uri = this.apiUrl + "cadastraraluno/"
    return this.httpClient.post(uri, {"prk" : frkUsuario, "frkTurma" : frkTurma})
  }

  public getTurmas() : Observable<Turma>{
    return this.httpClient.get<Turma>(this.apiUrl + "ativas/")
  }

  public getTurmasPorUsuario(prk:number) {
    let uri = this.apiUrl + "turmasporaluno/"
    return this.httpClient.post(uri, {"prk" : prk})
  }

  public removerAlunoTurma(frkTurma:number, frkUsuario:number){
    let uri = this.apiUrl + "removeraluno/"
    return this.httpClient.post(uri, {"prk" : frkUsuario, "frkTurma" : frkTurma})
  }

  public getAlunosTurma(frkTurma:Number){
    return this.httpClient.get<UsuarioSpl>(this.apiUrl + "id/"+frkTurma)
  }

  public getElegiveis(usuario:Number[]){
    let uri = this.apiUrl + "elegiveis/"
    return this.httpClient.post(uri, usuario)
  }


}
