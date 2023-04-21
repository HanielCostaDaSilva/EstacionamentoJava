package classes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.processing.FilerException;

import operadorCSV.OperadorArquivoCSV;

public class Estacionamento {
	private String[] placas;

	private String urlPlacas = "placas.csv"; // vaga;placa
	private String urlHistorico = "historico.csv";// data;vaga;placa;entrada
	private List<String> dadosExistentes = new ArrayList<String>();

	public Estacionamento(int vagasLivres) throws Exception, FilerException {
		if (vagasLivres <= 0) // checa se a quantidade de vagas é maior que 0
			throw new Exception("VALOR DE VAGAS INSERIDO E INVALIDO");

		this.placas = new String[vagasLivres]; // placas é instanciada com a quantidade de vagas pedidas pelo usuário

		OperadorArquivoCSV.criarArquivo(urlHistorico, "data", "vaga", "placa", "situação"); // criamos o arquivo que conterá o histórico
		OperadorArquivoCSV.criarArquivo(urlPlacas, "vaga", "placa"); // é criado o arquivo que armazena todsas as placas presentes no estacionamentos.
		this.lerDados(); // verificamos se já há algum veículo dentro do arquivo, para inserí-lo na lista  de placas.
	};

	/**
	 * checa se uma vaga está vazia ou não no estacionamento, caso estejÁ retorna
	 * {@code True}.
	 * A posição precisa ser um {@code int }{@value >=1}, pois será decrementado
	 * para poder ser consultado.
	 * 
	 * @return a boolean.
	 */

	private boolean checarVagaEstaVazia(int posicao) {
		return this.placas[posicao - 1] == null;
	};

	/**
	 * checa se a posição inserida é acessível a lista, caso seja, retorna
	 * {@code True}.
	 * A posição precisa ser um {@code int }>={@value } e menor que
	 * {@value placas.lenght}
	 * 
	 * @return a boolean.
	 */

	private boolean checarVagaExiste(int posicao) {
		return posicao <= this.placas.length && posicao > 0;
	};

	private String getMomentoAtual() {
		DateFormat formatarDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dataHoraAtual = new Date();
		return formatarDataHora.format(dataHoraAtual);
	}

	public void entrar(String placa, int vaga) throws Exception, FilerException {

		if (!checarVagaExiste(vaga)) // checa se a vaga inserida pelo usuário de fato existe na lista
			throw new Exception("VAGA" + vaga + " INVALIDA");
		if (!checarVagaEstaVazia(vaga)) // checa se não existe nenhum carro na vaga.
			throw new Exception("VAGA" + vaga + " JÁ PREENCHIDA");

		this.placas[vaga - 1] = placa;
		OperadorArquivoCSV.escreverArquivo(urlHistorico, this.getMomentoAtual(), Integer.toString(vaga), placa,
				"entrada");
		this.gravarDados();

	};

	public void sair(int vaga) throws Exception, FilerException {

		if (!checarVagaExiste(vaga)) // checa se a vaga inserida pelo usuário de fato existe na lista
			throw new Exception("VAGA" + vaga + " INVALIDA");
		if (checarVagaEstaVazia(vaga)) // checa se existe algum carro na vaga, caso não haja, retorna um erro.
			throw new Exception("VAGA" + vaga + " JÁ ESTA VAZIA");

		String placa = this.placas[vaga - 1];
		this.placas[vaga - 1] = null;

		// // vaga;placa
		// data;vaga;placa;saiu
		OperadorArquivoCSV.escreverArquivo(urlHistorico, this.getMomentoAtual(), Integer.toString(vaga), placa,
				"saida");
		this.gravarDados();

		// !! !! !! falta remover do urlPlacas a placa que saiu

	};

	public void transferir(int vaga1, int vaga2) throws Exception {
		// A vaga1 deve existir não estA vazia.
		// A vaga2 deve existir e estA vazia.

		if (!checarVagaExiste(vaga1))
			throw new Exception("Erro! A vaga " + vaga1 + " não está presente no estacionamento.");

		else if (checarVagaEstaVazia(vaga1))
			throw new Exception("Erro! A vaga " + vaga1 + " não possui veículo!");

		if (!checarVagaExiste(vaga2))
			throw new Exception("Erro! A vaga " + vaga2 + " não está presente no estacionamento.");

		else if (!checarVagaEstaVazia(vaga2))
			throw new Exception("Erro! A vaga " + vaga2 + " já possui um veículo!");

		String placaTransferida;
		this.placas[vaga2 - 1] = placaTransferida = this.placas[vaga1 - 1];
		this.placas[vaga1 - 1] = null;

		// Referente ao histórico
		OperadorArquivoCSV.escreverArquivo(this.urlHistorico, this.getMomentoAtual(), Integer.toString(vaga2),
				placaTransferida, "transferência");
		this.gravarDados();

	}

	public int consultarPlaca(String placa) {

		for (int i = 0; i < this.placas.length; i++) {
			if(this.placas[i]!=null){
				if (this.placas[i].toLowerCase().equals(placa.toLowerCase())) {
					return i + 1; // retorna a posição legível ao usuArio
				}
			}
			
		}
		return -1; // caso não ache a placa
	};

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

	public void updatePlacas() {
		this.dadosExistentes = OperadorArquivoCSV.lerArquivo(urlPlacas);
		this.dadosExistentes = OperadorArquivoCSV.lerArquivo(urlPlacas);

		for (int i = 1; i < dadosExistentes.size(); i++) {
			try {
				this.placas[i - 1] = dadosExistentes.get(i).split(";")[1];
			} catch (Exception e) {
				this.placas[i - 1] = null;
			}
		}

	};

	public void lerDados() {
		List<String> linhasCSV = OperadorArquivoCSV.lerArquivo(urlPlacas);

		/**
		 * checa se o arquivo placas está vazio.
		 */
		if (linhasCSV.size() == 0) {
			return;
		}
		;

		linhasCSV.remove(0); // Removemos a linha que contêm o título, a fim de pulá-lo na contagem

		for (int i = 0; i < placas.length; i++) {

			if (i + 1 >= linhasCSV.size()) {
				break;
			}
			String linha = linhasCSV.get(i);

			String[] colunas = linha.split(";");

			if (!(colunas[1].equals("null"))) { // se não houver placa, é porque a vaga está desocupada
				this.placas[Integer.parseInt(colunas[0]) - 1] = colunas[1]; // Só será gravado as vagas que possuem veículos.
			}
			;
		}
	};

	public void gravarDados() {
		// Grava dados da lista de Placas no arquivo placas.csv
		try {
			OperadorArquivoCSV.atualizaArquivo(this.urlPlacas, this.placas, "vaga", "placa");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}