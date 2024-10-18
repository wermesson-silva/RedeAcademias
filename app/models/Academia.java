package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Academia extends Model{
	
	public String nome; 
	public String endereco; 
	public String CNPJ;
	public String Contato;
	

}
