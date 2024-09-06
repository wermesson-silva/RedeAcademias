package controllers;

import java.util.List;

import models.Cliente;
import play.mvc.Controller;

public class Clientes extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void inicio() {
		render();
	}
	
	public static void salvar(Cliente c) {
		
		String mensagem = "Cliente cadastrado com sucesso!";
		
		if(c.id != null) {
			mensagem = "Dados do cliente editados com sucesso!";
		}
		
		c.save();
		flash.success(mensagem);
		listar(null);
	}
	
	public static void listar(String termo) {
		List<Cliente> clientes = null;
		
		if(termo == null) {
			clientes = Cliente.findAll();			
		} else {
			clientes =  Cliente.find("lower(nome) like ?1 or cpf like ?1 or lower(nomeAcademia) like ?1", "%" + termo.toLowerCase() + "%").fetch();
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
		
		Cliente cliente = Cliente.findById(id);
		
		String data = cliente.dataNascimento + "";
		String vetor[] = data.split(" ");
		String novaData = vetor[0];
		
		renderTemplate("Clientes/form.html", cliente, novaData);
	}
	
}
