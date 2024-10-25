package controllers;

import java.util.List;

import models.Academia;
import models.Cliente;
import models.Personal;
import play.mvc.Controller;

public class Academias extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void salvar(Academia a) {
		
		String mensagem = "Academia cadastrada com sucesso!";
		
		if(a.id != null) {
			mensagem = "Dados da Academia editados com sucesso!";
		}
		
		a.save();
		flash.success(mensagem);
		listar(null);
	}
	
	public static void listar(String termo) {
		List<Academia> academias = null;
		
		if(termo == null) {
			academias = Academia.findAll();			
		} else {
			academias =  Academia.find("lower(nome) like ?1 or lower(endereco) like ?1 or contato like ?1", "%" + termo.toLowerCase() + "%").fetch();
		}
		render(academias, termo);
	}
	
	public static void remover(Long id) {
		Academia a = Academia.findById(id);
		a.delete();
		flash.success("Academia removida com sucesso!");
		listar(null);
	}
	
	public static void editar(Long id) {
		
		Academia academia = Academia.findById(id);
		
		renderTemplate("Academias/form.html", academia);
	}
	
}
