package controllers;

import models.Login;
import models.Status;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	

	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Por favor realize login");
			Logins.formLogar();
		}
		
	}
	
	@Before
	static void verificarAutenticacaoADM() {
		String action = request.actionMethod;
		if (action.equals("listar") || action.equals("remover") || action.equals("editar")) {
			Status status = retornaStatus();
			
			if (status != Status.ADMINISTRADOR && session.get("Status") != null) {
				flash.error("Função restrita apenas para administrador");
				Login.abrirPagina(status, Long.parseLong(session.get("idConta")));
			}
			
		}
	}
	
	public static Status retornaStatus() {
		
		Status status = null;
		
		if(session.get("Status") != null) {
			status = Status.ADMINISTRADOR;
			
			if (session.get("Status").equals("CLIENTE")) {
				status = Status.CLIENTE;
			} else if (session.get("Status").equals("PERSONAL")) {
				status = Status.PERSONAL;
			}
		}
		
		return status;
		
	}
	
	
}
