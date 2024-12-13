package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Administrador extends Model{
	public String nome; 
	public String sobrenome; 
	public String Cpf; 
	public String contato; 
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nome + " " + sobrenome;
	}
}
