package models;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.br.CPF;

import play.db.jpa.Model;

@Entity
public class Personal extends Model {

	public String nome; 
	public String sobrenome;
	public String contato; 
	public String dataNascimento;
	public String cpf; 
	public float salario; 
	
	@Override
	public String toString() {
		return nome + " " + sobrenome ;  
	}

}
