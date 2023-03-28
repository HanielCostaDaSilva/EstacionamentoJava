package classes;

import java.util.ArrayList;

import javax.annotation.processing.FilerException;

public class Teste {
	public static void main(String[] args) throws FilerException, Exception {
		Estacionamento estac = new Estacionamento(10);
		String[] aux2 = estac.listarGeral();
		ArrayList<Integer> aux = estac.listarLivres();

		
		//Lista a quantidades de vagas livres
		for (int i=0; i<aux.size();i++) {
			System.out.println(aux.get(i));
		}


		//Exibe o arquivo placas.csv
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}

		estac.updatePlacas();
		aux2 = estac.listarGeral();
		aux = estac.listarLivres();


		//lista a quantidade de vagas livrwa ouTRA VEZX
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}
		//vagas livres
		for (int i=0; i<aux.size();i++) {
			System.out.println(aux.get(i));
		}
		
	}
}
