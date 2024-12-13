package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Login extends Model{
	public String login;
	public String senha;
	
	@Enumerated(EnumType.STRING)
	public Status tipo;
	
	public static Login autenticar(String login, String senha) {
		Login l = Login.find("login = ?1 and senha = ?2", login, senha).first();
		if (l == null) {
			return null;
		} else {
			return l;
		}
	}
}

