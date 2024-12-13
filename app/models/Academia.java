package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne
	public Administrador adm; 
	
	@Override
	public String toString() {
		return nome + " (" + endereco + ")";  
	}

}
