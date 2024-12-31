package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import controllers.Validacao;
import play.db.jpa.Model;

@Entity
public class Cliente extends Model{
	
	public String nome;
	public String sobrenome;
	public String cpf;
	public String contato;
	
	@Temporal(TemporalType.DATE)
	public Date dataNascimento;
	
	@Transient
	public Long idade;
	
	public String turno;
	public String acompanhamentoPersonal;
	
	@ManyToOne
	public Academia academia ;
	
	@ManyToOne
	public Personal personal;
	
	@OneToOne
	public Login conta;
	
	public Long getIdade() {
		return Validacao.retornaIdade(dataNascimento);
	}
	
}
