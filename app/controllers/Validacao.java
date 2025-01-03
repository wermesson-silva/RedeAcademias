package controllers;

import java.util.Date;
import java.util.List;

import models.Academia;
import models.Cliente;
import models.Login;
import models.Personal;
import play.mvc.Controller;

public class Validacao extends Controller{
	
	public static boolean validarAcademia(Academia a) {
		Academia nomeDuplicado = Academia.find("nome = ?1", a.nome).first();
		Academia cnpjDuplicado = Academia.find("CNPJ = ?1", a.CNPJ).first();
		Academia contatoDuplicado = Academia.find("contato = ?1", a.contato).first();
		
		if(nomeDuplicado != null && a.id == null) {
			flash.error("Já existe uma academia cadastrada com esse nome!");
			return false;
			
		} else if(cnpjDuplicado != null && a.id == null){
			flash.error("Já existe uma Academia cadastrada com esse cnpj!");
			return false;
			
		} else if(contatoDuplicado != null && a.id == null) {
			flash.error("Já existe uma academia cadastrada com esse contato!");
			return false;
			
		} else if(a.endereco == null) {
			flash.error("Endereço inválido!");
			return false;
			
		} else if(a.nome == null) {
			flash.error("Nome inválido!");
			return false;
			
		} else if(validarCnpj(a.CNPJ)) {
			return true;
			
		} else {
			return false;
		}
	}
	
	
	public static boolean validarCliente(Cliente c) {
		
		Cliente nomeDuplicado = Cliente.find("nome = ?1 and sobrenome = ?2", c.nome, c.sobrenome).first();
		Cliente cpfDuplicado = Cliente.find("cpf = ?1", c.cpf).first();
		Cliente contatoDuplicado = Cliente.find("contato = ?1", c.contato).first();
		
		if(nomeDuplicado != null && c.id == null) {
			flash.error("Já existe um cliente cadastrado com esse nome e sobrenome!");
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
		Personal nomeDuplicado = Personal.find("nome = ?1 and sobrenome = ?2", p.nome, p.sobrenome).first();
		Personal cpfDuplicado = Personal.find("cpf = ?1", p.cpf).first();
		Personal contatoDuplicado = Personal.find("contato = ?1", p.contato).first();
		
		if(nomeDuplicado != null && p.id == null) {
			flash.error("Já existe um persoanal cadastrado com esse nome e sobrenome!");
			return false;
			
		} else if(cpfDuplicado != null && p.id == null){
			flash.error("Já existe um personal cadastrado com esse cpf!");
			return false;
			
		} else if(contatoDuplicado != null && p.id == null) {
			flash.error("Já existe um personal cadastrado com esse contato!");
			return false;
			
		} else if(validarCpf(p.cpf) && validarContato(p.contato) && validarDataNascimento(p.dataNascimento, (long) 18)) {
			return true;
			
		} else {
			return false;
		}
	}
	
	public static boolean validarLogin(String login) {
		Login duplicado = Login.find("login = ?1", login).first();
		
		if(duplicado != null) {
			flash.error("Já existe um usuário no sistema cadastrado com esse Login!");
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean validarRemocaoAcademia(Academia a) {
		List<Cliente> clientes = Cliente.find("academia = ?1", a).fetch();
		
		if(clientes.size() != 0) {
			flash.error("Impossível remover, a academia possui Clientes cadastrados!");
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean validarRemocaoPersonal(Personal p) {
		List<Cliente> clientes = Cliente.find("personal = ?1", p).fetch();
		List<Academia> academias = Academia.find("SELECT a FROM Academia a JOIN a.personais p WHERE p = ?1", p).fetch();
		
		if(clientes.size() != 0 || academias.size() != 0) {
			flash.error("Impossível remover, o personal está associado a uma academia ou a um cliente!");
			return false;
		} else {
			return true;
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
	
	public static boolean validarCnpj(String cnpj) {
		
		if(cnpj.length() < 14 || cnpj == null) {
			flash.error("cnpj inválido");
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
