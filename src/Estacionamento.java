import java.util.ArrayList;

import com.ifpb.escreverCSV.OperadorArquivoCSV;

public class Estacionamento {
	private String[] placas;

	public Estacionamento(int vagasLivres) {
		assert vagasLivres >=0:"VALOR DE VAGAS INSERIDO É INVÁLIDO";
		this.placas = new String[vagasLivres];
	};
 
	public void entrar(String placa, int vaga) {
		
		assert checarVagaExiste(vaga): "VAGA INVÁLIDA";
		assert checarVagaEstaVazia(vaga) : "VAGA JÁ PREENCHIDA";
		
		OperadorArquivoCSV operadorArquivo= new OperadorArquivoCSV();
		String urlPlacas="./dados/placas.csv"; // vaga;placa
		String urlHistorico="./dados/historico.csv";//data;vaga;placa;entrada
		
		this.placas[vaga-1] = placa;
		operadorArquivo.escreverArquivo(urlPlacas, Integer.toString(vaga), placa);
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga), placa,"entrada");
		
	};

	public void sair(int vaga) {
		assert checarVagaExiste(vaga): "VAGA INVÁLIDA";
		assert !checarVagaEstaVazia(vaga) : "VAGA JÁ ESTÁ VAZIA";
		
		String placa= this.placas[vaga-1];
		this.placas[vaga-1]=null;
		
		OperadorArquivoCSV operadorArquivo= new OperadorArquivoCSV();
		//String urlPlacas="./dados/placas.csv"; // vaga;placa
		String urlHistorico="./dados/historico.csv";//data;vaga;placa;saiu
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga),placa,"saida");
		//!! !! !! falta remover do urlPlacas a placa que saiu


	};
	
	public int consultarPlaca(String placa){
		
		for (int i=0; i<this.placas.length; i++){
			if(this.placas[i] == placa) {
				return i + 1; //retorna a posição legível ao usuário
			}
			
		}
		return -1; //caso não ache a placa
	};
	
	public void transferir(int vaga1, int vaga2) {
		//A vaga1 deve existir não está vazia.
		//A vaga2 deve existir e está vazia.
		
		assert checarVagaExiste(vaga1) && !checarVagaEstaVazia(vaga1) : "A VAGA "+ vaga1 + " ESTÁ INVÁLIDA PARA ESSA OPERAÇÃO";
		assert checarVagaExiste(vaga2) && checarVagaEstaVazia(vaga2) : "A VAGA "+ vaga2 + " ESTÁ INVÁLIDA PARA ESSA OPERAÇÃO";
		
		this.placas[vaga2]= this.placas[vaga1];
		this.placas[vaga1]= null;
	}
	
	public String[] listarGeral() {
		
		String[] s= new String[this.placas.length];
		
		for (int i=0; i< this.placas.length; i++){
			
			String situacao;
			if(this.placas[i]==null) situacao="Vaga "+(i+1) + "— livre"; //caso encontre uma vaga livre
			else situacao="Vaga "+ (i+1) + "— "+ this.placas[i];
			
			s[i]=situacao;
		}
		return s;
	};
	
	public ArrayList<Integer> listarLivres(){
		ArrayList<Integer> vagasLivres= new ArrayList<>();
		
		for (int i=0; i<this.placas.length; i++){
		
			if (this.placas[i]==null) vagasLivres.add(i+1);
		}
		return vagasLivres;
		
	}
	

	public boolean checarVagaEstaVazia(int posicao) {
		return this.placas[posicao] == null;
	};
	
	public boolean checarVagaExiste(int posicao) {
		return posicao <= this.placas.length && posicao > 0;
	};

	
}
