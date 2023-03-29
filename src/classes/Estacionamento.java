package classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.FilerException;

import classes.operadorCSV.OperadorArquivoCSV;

public class Estacionamento {
	private String[] placas;

	private String urlPlacas = "placas.csv"; // vaga;placa
	private String urlHistorico = "historico.csv";// data;vaga;placa;entrada
	private OperadorArquivoCSV operadorArquivo = new OperadorArquivoCSV();
	private List<String> dadosExistentes = new ArrayList<String>();

	public Estacionamento(int vagasLivres) throws Exception, FilerException {
		if (vagasLivres <= 0) //checa se a quantidade de vagas é maior que 0
			throw new Exception("VALOR DE VAGAS INSERIDO E INVALIDO");
		
		this.placas = new String[vagasLivres]; //placas é instanciada com a quantidade de vagas pedidas pelo usuário
		
		operadorArquivo.criarArquivo(urlHistorico, "data", "vaga", "placa", "entrada"); //criamos o arquivo que conterá o histórico
		operadorArquivo.criarArquivo(urlPlacas, "vaga", "placa"); // é criado o arquivo que armazena todsas as placas presentes no estacionamentos.
		lerDados(); //verificamos se já há algum veículo dentro do arquivo, para inserí-lo na lista de placas. 
	};

	public void entrar(String placa, int vaga) throws Exception, FilerException {

		if (!checarVagaExiste(vaga)) //checa se a vaga inserida pelo usuário de fato existe na lista 
			throw new Exception("VAGA INVALIDA");
		if (!checarVagaEstaVazia(vaga)) //checa se não existe nenhum carro na vaga.
			throw new Exception("VAGA JA PREENCHIDA");

		this.placas[vaga - 1] = placa;
		operadorArquivo.escreverArquivo(urlPlacas, Integer.toString(vaga), placa);
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga), placa, "entrada");

	};

	public void sair(int vaga) throws Exception, FilerException {

		if (!checarVagaExiste(vaga)) //checa se a vaga inserida pelo usuário de fato existe na lista 
			throw new Exception("VAGA INVALIDA");
		if (checarVagaEstaVazia(vaga)) //checa se existe algum carro na vaga, caso não haja, retorna um erro.
			throw new Exception("VAGA JA ESTA VAZIA");

		String placa = this.placas[vaga - 1];
		this.placas[vaga - 1] = null;

		// // vaga;placa
		// data;vaga;placa;saiu
		operadorArquivo.escreverArquivo(urlHistorico, Integer.toString(vaga), placa, "saida");
		operadorArquivo.atualizaArquivo(urlPlacas, placas);

		// !! !! !! falta remover do urlPlacas a placa que saiu

	};

	public int consultarPlaca(String placa) {

		for (int i = 0; i < this.placas.length; i++) {
			if (this.placas[i] == placa) {
				return i + 1; // retorna a posição legível ao usuArio
			}
		}
		return -1; // caso não ache a placa
	};

	public void transferir(int vaga1, int vaga2) throws Exception {
		// A vaga1 deve existir não estA vazia.
		// A vaga2 deve existir e estA vazia.
		if (!checarVagaExiste(vaga1) || checarVagaEstaVazia(vaga1))
			throw new Exception("A VAGA " + vaga1 + " ESTA INVALIDA PARA ESSA OPERAÇÃO");
		if (!checarVagaExiste(vaga2) || !checarVagaEstaVazia(vaga2))
			throw new Exception("A VAGA " + vaga2 + " ESTA INVALIDA PARA ESSA OPERAÇÃO");

		this.placas[vaga2] = this.placas[vaga1];
		this.placas[vaga1] = null;
	}

	public String[] listarGeral() {

		String[] s = new String[this.placas.length];

		for (int i = 0; i < this.placas.length; i++) {

			String situacao;
			if (this.placas[i] == null)
				situacao = "Vaga " + (i + 1) + "— livre"; // caso encontre uma vaga livre
			else
				situacao = "Vaga " + (i + 1) + "— " + this.placas[i];

			s[i] = situacao;
		}
		return s;
	};

	public ArrayList<Integer> listarLivres() {
		ArrayList<Integer> vagasLivres = new ArrayList<>();

		for (int i = 0; i < this.placas.length; i++) {

			if (this.placas[i] == null)
				vagasLivres.add(i + 1);
		}
		return vagasLivres;

	}

	private boolean checarVagaEstaVazia(int posicao) {
		return this.placas[posicao - 1] == null;
	};

	private boolean checarVagaExiste(int posicao) {
		return posicao <= this.placas.length && posicao > 0;
	};

	public void updatePlacas() {
		this.dadosExistentes = operadorArquivo.lerArquivo(urlPlacas);

		for (int i = 1; i < dadosExistentes.size(); i++) {
			try {
				this.placas[i - 1] = dadosExistentes.get(i).split(";")[1];
			} catch (Exception e) {
				this.placas[i - 1] = null;
			}
		}

	}

	public void lerDados() {
		List<String> linhasCSV = operadorArquivo.lerArquivo(urlPlacas);

		linhasCSV.remove(0); // Removemos a linha que contêm o título, a fim de pulá-lo na contagem

		for (String linha : linhasCSV) {

			String[] colunas = linha.split(";"); // colunas recebe [vaga , null or "placa" ]

			if (colunas[1].equalsIgnoreCase("null") == false) { // se não houver placa, é porque a vaga está desocupada
				this.placas[Integer.parseInt(colunas[0]) - 1] = colunas[1]; // Só será gravado as vagas que possuem veículos.
			}
		}
	}
}