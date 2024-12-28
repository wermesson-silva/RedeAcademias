package controllers;

import models.Login;
import models.Status;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void verificarAutenticacaoADM() {
	    String action = request.actionMethod; // Obtém o nome da ação chamada
	    if (action.equals("listar") || action.equals("remover") || action.equals("editar")) {
	        Status status = Status.ADMINISTRADOR;

	        if (session.get("Status") != null) {
	            if (session.get("Status").equals("CLIENTE")) {
	                status = Status.CLIENTE;
	            } else if (session.get("Status").equals("PERSONAL")) {
	                status = Status.PERSONAL;
	            }

	            if (status != Status.ADMINISTRADOR) {
	                flash.error("Função restrita apenas para administrador");
	                Login.abrirPagina(status, Long.parseLong(session.get("idConta")));
	            }
	        }
	    }
	}

	@Before
	static void verificarAutenticacao() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Por favor realize login");
			Logins.formLogar();
		}
		
	}
	
	
	public static Status retornaStatus() {
		Status status = Status.ADMINISTRADOR;
		
        if (session.get("Status").equals("CLIENTE")) {
            status = Status.CLIENTE;
        } else if (session.get("Status").equals("PERSONAL")) {
            status = Status.PERSONAL;
        }
        
        return status;
	}
	
	
}
