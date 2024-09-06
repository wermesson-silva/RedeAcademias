package models;

import java.util.Date;

import javax.persistence.Entity;

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
	public String nomeAcademia;
	public String nomePersonal;
	
}
