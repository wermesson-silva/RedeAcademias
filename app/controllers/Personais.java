package controllers;

import java.util.List;

import models.Academia;
import models.Cliente;
import models.Login;
import models.Personal;
import models.Status;
import play.mvc.Before;
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
			listar();			
		}
	}
	
	public static void listar() {
		List<Personal> personais =  Personal.findAll();
		render(personais);
	}
	
	public static void listarJson(String termo) {
		List<Personal> personais = null;
		
		if(termo == null) {
			personais = Personal.findAll();			
		} else {
			personais = Personal.find("lower(nome) like ?1 or salario like ?1", "%" + termo.toLowerCase() + "%").fetch();
			
			for(Personal posicao: personais) {
				posicao.idade = Validacao.retornaIdade(posicao.dataNascimento);
			}
		}
		renderJSON(personais);
	}
	
	public static void remover(Long id) {
		Personal p = Personal.findById(id);
		p.delete();
		flash.success("Personal removido com sucesso!");
		
		if(session.get("Status").equals("PERSONAL")) {
			menu(p.id, p.conta.id);
		} else {
			listar();			
		}
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
	
	public static void adicionarAcademia(Long idAcademia, Long idPersonal) {
		Academia academia = Academia.findById(idAcademia);
		Personal personal = Personal.findById(idPersonal);
		
		academia.personais.add(personal);
		academia.save();
		
		flash.success("Adicionado à academia com sucesso!");
		menu(personal.id, personal.conta.id);
		
	}
	
	public static void adicionarCliente(Long idCliente, Long idPersonal) {
		Cliente cliente = Cliente.findById(idCliente);
		Personal personal = Personal.findById(idPersonal);
		
		cliente.personal = personal;
		cliente.acompanhamentoPersonal = "Sim";
		cliente.save();
		
		flash.success("Cliente adicionado com sucesso!");
		menu(personal.id, personal.conta.id);
		
	}
	
	public static void removerAcademia(Long idAcademia, Long idPersonal) {
		Academia academia = Academia.findById(idAcademia);
		Personal personal = Personal.findById(idPersonal);
		
		academia.personais.remove(personal);
		academia.save();
		
		flash.success("Removido da academia com sucesso!");
		menu(personal.id, personal.conta.id);
	}
	
	public static void removerCliente(Long idCliente, Long idPersonal) {
		Cliente cliente = Cliente.findById(idCliente);
		Personal personal = Personal.findById(idPersonal);
		
		cliente.personal = null;
		cliente.save();
		
		flash.success("Cliente removido com sucesso!");
		menu(personal.id, personal.conta.id);
	}
	
}
