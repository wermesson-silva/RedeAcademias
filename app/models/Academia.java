package models;

import javax.persistence.Entity;

import groovy.transform.ToString;
import play.db.jpa.Model;

@Entity
public class Academia extends Model{
	
	public String nome; 
	public String endereco; 
	public String CNPJ;
	public String Contato;
	
	@Override
	public String toString() {
		return nome + " (" + endereco + ")";  
	}

}
