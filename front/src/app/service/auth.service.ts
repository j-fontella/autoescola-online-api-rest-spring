import { Injectable } from '@angular/core';
import {Logintoken} from "../models/logintoken";
import {Usuario} from "../models/usuario";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loginResponse : Usuario | undefined | any

  constructor(private route : Router) { }

  public clearLogin(){
    this.loginResponse = undefined;
  }

  public isAutenticado(){
    if(this.loginResponse?.prk){
      return true;
    }
    alert("Realize o login")
    return this.route.navigate(['']);
  }
}
