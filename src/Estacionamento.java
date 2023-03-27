import java.util.ArrayList;

import javax.annotation.processing.FilerException;

import com.ifpb.escreverCSV.OperadorArquivoCSV;

public class Estacionamento {
	private String[] placas;
	
	private String urlPlacas="placas.csv"; // vaga;placa
	private String urlHistorico="historico.csv";//data;vaga;placa;entrada
	private OperadorArquivoCSV operadorArquivo = new OperadorArquivoCSV();

	public Estacionamento(int vagasLivres) throws Exception, FilerException {
		if (vagasLivres <=0) throw new Exception("VALOR DE VAGAS INSERIDO E INVALIDO");
		this.placas = new String[vagasLivres];
		operadorArquivo.criarArquivo(urlHistorico, "data","vaga","placa" ,"entrada");
		operadorArquivo.criarArquivo(urlPlacas, "vaga","placa");

		
	};
 
	public void entrar(String placa, int vaga)throws Exception, FilerException {
		
		if (!checarVagaExiste(vaga)) throw new Exception( "VAGA INVALIDA");
		if (!checarVagaEstaVazia(vaga)) throw new Exception("VAGA JA PREENCHIDA");
		

		
		this.placas[vaga-1] = placa;
		operadorArquivo.escreverArquivo(urlPlacas, Integer.toString(vaga), placa);
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga), placa,"entrada");
		
	};

	public void sair(int vaga) throws Exception, FilerException{
		
		if(!checarVagaExiste(vaga)) throw new Exception("VAGA INVALIDA");
		if (checarVagaEstaVazia(vaga)) throw new Exception("VAGA JA ESTA VAZIA");
		
		String placa= this.placas[vaga-1];
		this.placas[vaga-1]=null;
		

		// // vaga;placa
		//data;vaga;placa;saiu
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga),placa,"saida");
		//!! !! !! falta remover do urlPlacas a placa que saiu

	};
	
	public int consultarPlaca(String placa){
		
		for (int i=0; i<this.placas.length; i++){
			if(this.placas[i] == placa) {
				return i + 1; //retorna a posição legível ao usuArio
			}			
		}
		return -1; //caso não ache a placa
	};
	
	public void transferir(int vaga1, int vaga2) throws Exception{
		//A vaga1 deve existir não estA vazia.
		//A vaga2 deve existir e estA vazia.
		if (!checarVagaExiste(vaga1) || checarVagaEstaVazia(vaga1))throw new Exception("A VAGA "+ vaga1 + " ESTA INVALIDA PARA ESSA OPERAÇÃO");
		if (!checarVagaExiste(vaga2) || !checarVagaEstaVazia(vaga2))throw new Exception("A VAGA "+ vaga2 + " ESTA INVALIDA PARA ESSA OPERAÇÃO");
		
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
	
	private boolean checarVagaEstaVazia(int posicao) {
		return this.placas[posicao] == null;
	};
	
	private boolean checarVagaExiste(int posicao) {
		return posicao <= this.placas.length && posicao > 0;
	};

	
}
