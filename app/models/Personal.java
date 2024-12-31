package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.br.CPF;

import controllers.Validacao;
import play.db.jpa.Model;

@Entity
public class Personal extends Model {

	public String nome; 
	public String sobrenome;
	public String contato; 
	
	@Temporal(TemporalType.DATE)
	public Date dataNascimento;
	
	@Transient
	public Long idade;
	
	public String cpf; 
	public String salario; 
	
	@OneToOne
	public Login conta;
	
	@Override
	public String toString() {
		return nome + " " + sobrenome ;  
	}
	
	public Long getIdade() {
		return this.idade = Validacao.retornaIdade(dataNascimento);
	}

}
