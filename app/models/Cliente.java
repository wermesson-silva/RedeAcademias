package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Cliente extends Model{
	
	public String nome;
	public String sobrenome;
	public String cpf;
	public String contato;
	public Date dataNascimento;
	public String turno;
	public String acompanhamentoPersonal;
	
	@ManyToOne
	public Academia academia ;
	
	@ManyToOne
	public Personal personal;
	
}
