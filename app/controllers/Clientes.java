package controllers;

import java.util.List;

import models.Academia;
import models.Cliente;
import models.Personal;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Clientes extends Controller {
	
	public static void form(Long idConta) {
		
		List <Academia> academias = Academia.findAll();
		List <Personal> personais = Personal.findAll();

		render(academias, personais, idConta);
	}
	
	public static void salvar(Cliente c) {
		
		String mensagem = "Cliente cadastrado com sucesso!";
		
		if(c.id != null) {
			mensagem = "Dados do cliente editados com sucesso!";
		}
		
		c.save();
		flash.success(mensagem);
		
		if(session.get("Status").equals("CLIENTE")) {
			menu(c.id, c.conta.id);
		} else {
			listar(null);			
		}
	}
	
	public static void listar(String termo) {
		List<Cliente> clientes = null;
		
		if(termo == null) {
			clientes = Cliente.findAll();			
		} else {
			clientes = Cliente.find("lower(nome) like ?1 or lower(cpf) like ?1", "%" + termo.toLowerCase() + "%").fetch();
		}
		render(clientes, termo);
	}
	
	public static void remover(Long id) {
		Cliente c = Cliente.findById(id);
		c.delete();
		flash.success("Cliente removido com sucesso!");
		
		if(session.get("Status").equals("CLIENTE")) {
			menu(c.id, c.conta.id);
		} else {
			listar(null);			
		}
		
	}
	
	public static void editar(Long id) {
		
		List <Academia> academias = Academia.findAll();
		List <Personal> personais = Personal.findAll();
		
		Cliente cliente = Cliente.findById(id);
		
		renderTemplate("Clientes/form.html", cliente, academias, personais);
	}
	
	public static void menu(Long clienteMenuId, Long idConta) {
		
		List<Academia> academiasMenu = Academia.findAll();
		List<Personal> personaisMenu = Personal.findAll();
		
		if(clienteMenuId == null) {
			render(null, idConta, academiasMenu, personaisMenu);			
		} else {
			Cliente clienteMenu = Cliente.findById(clienteMenuId);
			render(clienteMenu, idConta, academiasMenu, personaisMenu);
		}
		
	}
	
	public static void adicionarAcademia(Long idAcademia, Long idCliente) {
		Cliente cliente = Cliente.findById(idCliente);
		Academia academia = Academia.findById(idAcademia);
		cliente.academia = academia;
		cliente.save();
		flash.success("Cadastro na academia " + cliente.academia + " realizado!");
		menu(cliente.id, cliente.conta.id);
	}
	
	public static void removerAcademia(Long idCliente) {
		Cliente cliente = Cliente.findById(idCliente);
		flash.success("Você saiu da academia: " + cliente.academia);
		cliente.academia = null;
		cliente.save();
		menu(cliente.id, cliente.conta.id);
	}
	
	public static void adicionarPersonal(Long idPersonal, Long idCliente) {
		Cliente cliente = Cliente.findById(idCliente);
		Personal personal = Personal.findById(idPersonal);
		cliente.personal = personal;
		cliente.acompanhamentoPersonal = "Sim";
		cliente.save();
		flash.success("Cadastro com o personal " + cliente.personal + " realizado!");
		menu(cliente.id, cliente.conta.id);
	}
	
	public static void removerPersonal(Long idCliente) {
		Cliente cliente = Cliente.findById(idCliente);
		flash.success("Você cancelou o acompanhamento com o personal: " + cliente.personal);
		cliente.personal = null;
		cliente.acompanhamentoPersonal = "Não";
		cliente.save();
		menu(cliente.id, cliente.conta.id);
	}
	
}
