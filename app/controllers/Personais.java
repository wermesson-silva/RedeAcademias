package controllers;

import java.util.List;

import models.Academia;
import models.Cliente;
import models.Personal;
import play.mvc.Controller;

public class Personais extends Controller {
	
	public static void form() {

		render();
	}
	
	public static void salvar(Personal p) {
		
		String mensagem = "Personal cadastrado com sucesso!";
		
		if(p.id != null) {
			mensagem = "Dados do personal editados com sucesso!";
		}
		
		p.save();
		flash.success(mensagem);
		listar(null);
	}
	
	public static void listar(String termo) {
		List<Personal> personais = null;
		
		if(termo == null) {
			personais = Personal.findAll();			
		} else {
			personais =  Personal.find("lower(nome) like ?1 or cpf like ?1 or lower(academia.nome) like ?1", "%" + termo.toLowerCase() + "%").fetch();
		}
		render(personais, termo);
	}
	
	public static void remover(Long id) {
		Personal p = Personal.findById(id);
		p.delete();
		flash.success("Personal removido com sucesso!");
		listar(null);
	}
	
	public static void editar(Long id) {
		
		Cliente personal = Personal.findById(id);
	
		renderTemplate("Personais/form.html", personal);
	}
	
}
