package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CPF;

import play.db.jpa.Model;

@Entity
public class Personal extends Model {

	public String nome; 
	public String sobrenome;
	public String contato; 
	
	@Temporal(TemporalType.DATE)
	public Date dataNascimento;
	
	public String cpf; 
	public float salario; 
	
	@Override
	public String toString() {
		return nome + " " + sobrenome ;  
	}

}
