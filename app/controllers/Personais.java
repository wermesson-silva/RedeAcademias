package controllers;

import java.util.List;

import models.Academia;
import models.Cliente;
import models.Personal;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Personais extends Controller {
	
	public static void form(Long idConta) {

		render(idConta);
	}
	
	public static void salvar(Personal p) {
		
		String mensagem = "Personal cadastrado com sucesso!";
		
		if(p.id != null) {
			mensagem = "Dados do personal editados com sucesso!";
		}
		
		p.save();
		flash.success(mensagem);
		
		if(session.get("Status").equals("PERSONAL")) {
			menu(p.id, p.conta.id);
		} else {
			listar(null);			
		}
	}
	
	public static void listar(String termo) {
		List<Personal> personais = null;
		
		if(termo == null) {
			personais = Personal.findAll();			
		} else {
			personais =  Personal.find("lower(nome) like ?1 or salario like ?1 or lower(academia.nome) like ?1", "%" + termo.toLowerCase() + "%").fetch();
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
		
		Personal personal = Personal.findById(id);
	
		renderTemplate("Personais/form.html", personal);
	}
	
	public static void menu(Long personalMenuId, Long idConta) {
		
		List<Academia> academiasMenu = Academia.findAll();
		List<Cliente> clientesMenu = Cliente.findAll();
		
		if(personalMenuId == null) {
			render(null, idConta, academiasMenu, clientesMenu);			
		} else {
			Personal personalMenu = Personal.findById(personalMenuId);
			render(personalMenu, idConta, academiasMenu, clientesMenu);
		}
		
	}
	
}
