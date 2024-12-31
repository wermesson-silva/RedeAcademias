package controllers;

import java.util.Date;

import models.Cliente;
import models.Personal;
import play.mvc.Controller;

public class Validacao extends Controller{
	
	
	public static boolean validarCliente(Cliente c) {
		
		Cliente nomeDuplicado = Cliente.find("nome = ?1", c.nome).first();
		Cliente cpfDuplicado = Cliente.find("cpf = ?1", c.cpf).first();
		Cliente contatoDuplicado = Cliente.find("contato = ?1", c.contato).first();
		
		if(nomeDuplicado != null && c.id == null) {
			flash.error("Já existe um cliente cadastrado com esse nome!");
			return false;
			
		} else if(cpfDuplicado != null && c.id == null){
			flash.error("Já existe um cliente cadastrado com esse cpf!");
			return false;
			
		} else if(contatoDuplicado != null && c.id == null) {
			flash.error("Já existe um cliente cadastrado com esse contato!");
			return false;
			
		} else if(validarCpf(c.cpf) && validarContato(c.contato) && validarDataNascimento(c.dataNascimento, (long) 16) && validarTurno(c.turno)) {
			return true;
			
		} else {
			return false;
		}
		
	}
	
	public static boolean validarPersonal(Personal p) {
		Personal nomeDuplicado = Personal.find("nome = ?1", p.nome).first();
		Personal cpfDuplicado = Personal.find("cpf = ?1", p.cpf).first();
		Personal contatoDuplicado = Personal.find("contato = ?1", p.contato).first();
		
		if(nomeDuplicado != null && p.id == null) {
			flash.error("Já existe um cliente cadastrado com esse nome!");
			return false;
			
		} else if(cpfDuplicado != null && p.id == null){
			flash.error("Já existe um cliente cadastrado com esse cpf!");
			return false;
			
		} else if(contatoDuplicado != null && p.id == null) {
			flash.error("Já existe um cliente cadastrado com esse contato!");
			return false;
			
		} else if(validarCpf(p.cpf) && validarContato(p.contato) && validarDataNascimento(p.dataNascimento, (long) 18)) {
			return true;
			
		} else {
			return false;
		}
	}
	
	
	public static boolean validarCpf(String cpf) {
		
		if(cpf.length() < 14 || cpf == null) {
			flash.error("cpf inválido");
			return false;
		} else {			
			return true;
		}
		
	}
	
	public static boolean validarContato(String contato) {
		
		if(contato.length() < 14 || contato == null) {
			flash.error("contato inválido");
			return false;
		} else {			
			return true;
		}
		
	}
	public static boolean validarTurno(String turno) {
		
		if(turno.equals("nenhum")) {
			flash.error("por favor selecione um turno");
			return false;
		} else {			
			return true;
		}
		
	}
	
	public static boolean validarDataNascimento(Date data, Long idadeMinima) {
		Long idade = retornaIdade(data);
		
		if(idade < idadeMinima) {
			flash.error("Data inválida, o usuário deve ter " + idadeMinima + " anos ou mais");
			return false;
		} else {
			return true;
		}
	}
	
	
	public static Long retornaIdade(Date data) {
		
		Long idade = null;
		Date d2 = new Date();
		
		if (data == null) {
			return 0l;
		}
		
		long differenceInTime = d2.getTime() - data.getTime();
		idade = (differenceInTime / (1000l * 60 * 60 * 24 * 365));
		
		return idade;
	}
}
