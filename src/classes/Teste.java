package classes;

import javax.annotation.processing.FilerException;

public class Teste {
	public static void main(String[] args) throws FilerException, Exception {
		Estacionamento estac = new Estacionamento(10);
		String[] aux2 = estac.listarGeral();
	

		
		//Lista a quantidades de vagas livres


		//Exibe o arquivo placas.csv
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}

		estac.updatePlacas();
		aux2 = estac.listarGeral();


		//lista a quantidade de vagas livrwa ouTRA VEZX
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}
		//vagas livres
		
		estac.sair(1);
		aux2 = estac.listarGeral();
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}
	}
}
