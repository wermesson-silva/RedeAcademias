package controllers;

import models.Login;
import play.mvc.Controller;

public class Logins extends Controller{
	
	public static void form() {
		render();
	}
	
	public static void formLogar() {
		render();
	}
	
	public static void salvar(Login l) {
		
		if(l.senha.equals(l.confirmaSenha)) {
			l.save();
			formLogar();
		} else {
			flash.error("As senhas devem ser iguais!");
			form();
		}
		
	}
	
	public static void logar(String username, String senha) {
		String usuarioLogado = Login.autenticar(username, senha);
		
		Login loginUsuario = Login.find("login = ?1 and senha = ?2", username, senha).first();
		
		if (usuarioLogado == null) {
			//USUARIO NAO ENCONTRADO NO BANCO
			flash.error("Credenciais inválidas");
			formLogar();
		} else {
			//SOMENTE USUARIO QUE FORAM ENCONTRADOS NO BANCO
			session.put("usuarioLogado", usuarioLogado);
			Login.abrirPagina(loginUsuario.tipo, loginUsuario.id);
		}
	}
	
	public static void sair() {
		session.clear();
		flash.success("Você saiu do sistema");
		formLogar();
	}

}
