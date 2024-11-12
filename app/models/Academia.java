package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import groovy.transform.ToString;
import play.db.jpa.Model;

@Entity
public class Academia extends Model{
	
	public String nome; 
	public String endereco; 
	public String CNPJ;
	public String contato;
	
	@ManyToMany
	public List<Personal> personais;
	
	@Override
	public String toString() {
		return nome + " (" + endereco + ")";  
	}

}
