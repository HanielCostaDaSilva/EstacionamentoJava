package operadorCSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OperadorArquivoCSV {
	
	/**
	 * método responsável por criar um novo arquivo CSV, em uma determinada {@code url},e insere um título para cada coluna.
	 * Caso o arquivo CSV já tenha sido criado, ele checa a quantidade de colunas preenchidas, se nenhuma tiver sido preenchida, ele inserirá o {@code colunaTitulo} na primeira linha.    
	 * @param url
	 * @param colunaTitulo
	 */
	public static void criarArquivo(String url, String ...colunaTitulo) throws Exception{
		String filePath="";
		try{

			filePath = new File("").getCanonicalPath()+"/data/";
		}
		catch(IOException exception){
			throw new Exception("Não foi possível criar um novo arquivo");
		}

		File data = new File(filePath.concat(url));
		
		FileWriter escrevercolunaTitulo= new FileWriter(data,true);
		
		if(!data.exists()) {
			//Caso o arquivo que tenha sido criado não exista.
			try {
				data.createNewFile();
				System.out.println("asd");
				escrevercolunaTitulo.write(String.join( ";", colunaTitulo));
				
			}
			
			catch(Exception error) {
				System.out.println("Erro ao tentar criar o arquivo: "+url);
			}
		}
	
		if(lerArquivo(url).size()==0){ //checa se o arquvio foi criado, e está vazio
			escrevercolunaTitulo.write(String.join( ";", colunaTitulo)); //Se estiver vazio, ele irá inserir o título das linhas. 
		};
		try{
			escrevercolunaTitulo.close();
		}
		catch(Exception error) {
			System.out.println("Não foi possível fechar o arquivo: "+url);
		}
		
		
	};
	
	public static void escreverArquivo (String url , String...dados) {
	
		try{
			List<String> dadosExistentes=lerArquivo(url);
			String linhaDados= String.join(";",dados);
			
			dadosExistentes.add(linhaDados);
			
			//adicionar no csv
			String todasLinhas= OrganizarArquivo(dadosExistentes);
			FileWriter arquivo = new FileWriter(new File("").getCanonicalPath()+"/data/".concat(url), false);
			arquivo.write(todasLinhas);
			arquivo.close();
			
		
		} catch(Exception e){
			System.out.println("Erro ao tebtar escrever no arquivo "+ url);
		}
	}
	
	public static List<String> lerArquivo(String url){
		
		List<String> resultado = new ArrayList<String>();
		
		try {
			Path path= Paths.get(new File("").getCanonicalPath()+"/data/"+url);
			resultado= Files.readAllLines(path);
		}
		catch(Exception error ){
			System.out.println("Deu erro ao tentar ler o arquivo: "+url);
		};
		return resultado;
	}                                      
	
	public static String OrganizarArquivo(List<String> dadosExistentes){
		
		String linha="";
		
		for(String dado: dadosExistentes) {	
			linha+=(dado+'\n');
			
		}
		return linha;
	}
	

	public static void atualizaArquivo(String url, String[] listaDados, String...colunatitulo) throws IOException {
		File arquivoCSV = new File(new File("").getCanonicalPath()+"/data/".concat(url));
		//isto talvez tenha que ser mudado, criar uma variavel privada para o File das colunatitulo. não ser recriado várias vezes
		FileWriter update = new FileWriter(arquivoCSV, false);

		update.write(String.join(";", colunatitulo)+"\n");
		
		for(int i = 0; i < listaDados.length; i++){
			update.append(i+1+";"+listaDados[i]+"\n");
		}
		update.close();
	}


}