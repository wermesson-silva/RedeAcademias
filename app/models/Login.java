package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import controllers.Clientes;
import play.db.jpa.Model;

@Entity
public class Login extends Model{
	
	public String login;
	public String senha;
	
	@Transient
	public String confirmaSenha;
	
	@Enumerated(EnumType.STRING)
	public Status tipo;
	
	public static String autenticar(String login, String senha) {
		Login l = Login.find("login = ?1 and senha = ?2", login, senha).first();
		if (l == null) {
			return null;
		} else {
			return l.login;
		}
	}
	
	public static void abrirPagina(Status tipo, Long idConta) {
		if(tipo == Status.CLIENTE) {
			Cliente c = Cliente.find("conta.id = ?1", idConta).first();
			
			if(c == null ) {
				Clientes.menu(null, idConta);				
			} else {
				Clientes.menu(c.id, idConta);
			}
		} else if(tipo == Status.PERSONAL) {
			
		}
	}
}
