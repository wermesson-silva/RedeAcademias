package jobs;

import models.Academia;
import models.Personal;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		
		if (Academia.count() == 0) {
			Academia a1 = new Academia();
			a1.nome = "PowerFit";
			a1.endereco = "João Câmera";
			a1.Contato = "84 98181-2212";
			a1.CNPJ = "21232323";
			a1.save();
			
			Academia a2 = new Academia();
			a2.nome = "EducFit";
			a2.endereco = "Ceara Mirin";
			a2.Contato = "84 94183-2562";
			a2.CNPJ = "21867865";
			a2.save();
			
			Academia a3 = new Academia();
			a3.nome = "Smart Fit";
			a3.endereco = "João Câmera";
			a3.Contato = "84 99583-7612";
			a3.CNPJ = "2123421";
			a3.save();
		}
		
		if(Personal.count() == 0) {
			Personal p1 = new Personal(); 
			p1.nome = "Cesar";
			p1.sobrenome  = "Gomes";
			p1.contato = "84 9 8642 1245";
			p1.cpf = "458.132.154-65";
			p1.dataNascimento = "21/02/1998";
			p1.salario = 2546.00f;
			p1.save();
		}
	}

}
