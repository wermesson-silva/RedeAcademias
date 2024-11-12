package controllers;

import java.util.ArrayList;
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
	
	public static void formPersonal(Long id) {
		Academia academia = Academia.findById(id);
		List<Personal> personais = Personal.findAll();
		
		render(academia, personais);
		
	}
	
	public static void adicionarPersonal(Long idPersonal, Long idAcademia) {
		Personal p = Personal.findById(idPersonal);
		Academia a = Academia.findById(idAcademia);
		
		if(a.personais == null) {
			a.personais = new ArrayList<>();
		}
		
		if(a.personais.contains(p)) {
			flash.error("Esse personal já está vinculado à Academia");
		} else {
			a.personais.add(p);
			a.save();
			flash.success("Personal adicionado com sucesso!");
		}	
		
		formPersonal(idAcademia);
	}
	
	public static void removerPersonal(Long idAcademia, Long idPersonal) {
		Academia a = Academia.findById(idAcademia);
		Personal p = Personal.findById(idPersonal);
		
		if(a.personais == null) {
			flash.success("Essa academia não possui nenhum personal");
		} else {
			a.personais.remove(p);
			a.save();
			flash.success("Personal removido com sucesso!");
		}	
		
		formPersonal(idAcademia);
		
		
	}
	
}
