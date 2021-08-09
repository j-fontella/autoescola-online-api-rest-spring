import { Component, Input, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public operacao = true;
  private textoBotao = "Entrar";
  private textoLink = "Não tem uma conta? Registre-se";
  
  constructor(
    public userService : UserService,
    private route: Router
  ){}
  
  ngOnInit(): void {
    this.getAllUsers();
  }

 

  public alterarTextoRegistro() : void{
    this.operacao = !this.operacao;
    this.atualizarTextos();
    let labelRegistro = document.querySelector("#labelRegistro");
    if(labelRegistro){
      labelRegistro.textContent = this.textoLink;
    }
    let botaoRegistro = document.querySelector("#botaoLogin span");
    if(botaoRegistro){
      botaoRegistro.textContent = this.textoBotao;
    }
  
  }

  private atualizarTextos(){
    this.textoBotao = this.operacao ? "Entrar" : "Registrar";
    this.textoLink = this.operacao ? "Não tem uma conta? Registre-se" : "Já tem uma conta? Fazer Login";
  }

  private getAllUsers(){
      this.userService.getUserByPrk(2).subscribe(data => {
        console.log(data)
      })
  }

  public acaoBotao(){
    this.operacao ? this.logarUsuario() : this.registrarUsuario();
  }

  public logarUsuario(){
    let usuarioInput = document.querySelector("#usuario") as HTMLInputElement;
    let senhaInput = document.querySelector("#senha") as HTMLInputElement;

    if(usuarioInput && senhaInput && usuarioInput.value && senhaInput.value){
      let usuario = usuarioInput.value
      let senha = senhaInput.value
      this.userService.logarUsuario(usuario,senha).subscribe(data =>{
        let data_comp : any = data;
        let rota = data_comp.statusAdmin ? "admin" : "aulas";
        this.route.navigate([rota], { state: { "prk": data_comp.prk, "nome" : usuario } })

      }, error =>{
        alert(error.error)
      })

    }else{
      alert("Para efetuar o login você deve inserir email e senha válidos.");
      return;
    }
  }

  public registrarUsuario(){
    let usuarioInput = document.querySelector("#usuario") as HTMLInputElement;
    let senhaInput = document.querySelector("#senha") as HTMLInputElement;

    if(usuarioInput && senhaInput && usuarioInput.value && senhaInput.value){
      let usuario = usuarioInput.value
      let senha = senhaInput.value
      this.userService.cadastrarUsuario(usuario,senha).subscribe(data =>{
        console.log(data)
      },error =>{
        alert(error.error)
      })

    }else{
      alert("Para efetuar um registro de conta você deve inserir email e senha válidos.");
      return;
    }
  }

  




}
