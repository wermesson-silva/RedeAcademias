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
		
		if(c.conta != null) {
			menu(c, c.conta.id);
		} else {
			listar(null);			
		}
	}
	
	public static void listar(String termo) {
		List<Cliente> clientes = null;
		
		if(termo == null) {
			clientes = Cliente.findAll();			
		} else {
			clientes =  Cliente.find("lower(nome) like ?1 or cpf like ?1 or lower(academia.nome) like ?1", "%" + termo.toLowerCase() + "%").fetch();
		}
		render(clientes, termo);
	}
	
	public static void remover(Long id) {
		Cliente c = Cliente.findById(id);
		c.delete();
		flash.success("Cliente removido com sucesso!");
		listar(null);
	}
	
	public static void editar(Long id) {
		
		List <Academia> academias = Academia.findAll();
		List <Personal> personais = Personal.findAll();
		
		Cliente cliente = Cliente.findById(id);
		
		renderTemplate("Clientes/form.html", cliente, academias, personais);
	}
	
	public static void menu(Cliente clienteMenu, Long idConta) {
		render(clienteMenu, idConta);
	}
	
}
