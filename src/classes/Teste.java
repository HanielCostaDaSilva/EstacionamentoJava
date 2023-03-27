package classes;

import java.util.ArrayList;

import javax.annotation.processing.FilerException;

public class Teste {
	public static void main(String[] args) throws FilerException, Exception {
		Estacionamento estac = new Estacionamento(10);
		String[] aux2 = estac.listarGeral();
		ArrayList<Integer> aux = estac.listarLivres();
		for (int i=0; i<aux.size();i++) {
			System.out.println(aux.get(i));
		}
		for (int i=0; i<aux2.length;i++) {
			System.out.println(aux2[i]);
		}
		estac.updatePlacas();
		
	}
}
