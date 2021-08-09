import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';
import { tap } from 'rxjs/operators'
import {AuthService} from "./auth.service";
@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = "http://localhost:8080/usuario/";

  httpOptions = {
    headers : new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  constructor(private httpClient : HttpClient, private authService : AuthService) { }

  public getUserByPrk(prk:number) : Observable<Usuario>{
    return this.httpClient.get<Usuario>(this.apiUrl + prk)
  }

  public cadastrarUsuario(usuario:String, senha:String){
    let uri = this.apiUrl + "registrar/"
    return this.httpClient.post(uri, {"usuario" : usuario, "senha" : senha})
  }

  public logarUsuario(usuario:String, senha:String){
    let uri = this.apiUrl + "logar/"
    return this.httpClient.post(uri, {"usuario" : usuario, "senha" : senha}).pipe(
      tap((loginResponse) => (this.authService.loginResponse = loginResponse))
    );
  }
}
