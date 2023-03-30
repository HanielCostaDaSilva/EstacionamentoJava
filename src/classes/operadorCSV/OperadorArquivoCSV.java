package classes.operadorCSV;

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
	 * @param url
	 * @param Titulo
	 */
	public static void criarArquivo(String url, String ...Titulo) {
		
		String filePath = new File("").getCanonicalPath()+"/data/";
		File data = new File(filePath.concat(url));
		if(!data.exists()) {
			try {
				File arquivoCSV = new File(filePath.concat(url));
				arquivoCSV.createNewFile();
				FileWriter escreverTitulo= new FileWriter(arquivoCSV);

				escreverTitulo.write(String.join( ";", Titulo));

				escreverTitulo.close();
			
			}
				
			catch(Exception error) {
				System.out.println("DEu erro ao tentar criar o arquivo: "+url);
			}
		}
		
	};
	
	public static void escreverArquivo (String url , String...dados) {
		
		try{			
			List<String> dadosExistentes=lerArquivo(url);
			String linhaDados= String.join(";",dados)+"\n";
			
			dadosExistentes.add(linhaDados);
			
			//adicionar no csv
			String todasLinhas= lerArquivo(dadosExistentes);
			FileWriter arquivo = new FileWriter(new File("").getCanonicalPath()+"/data/".concat(url), true);
			arquivo.write(todasLinhas);
			arquivo.close();
			
		
		} catch(Exception e){
			System.out.println("Erro ao gerar o arquivo "+ url);
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
	
	public static String lerArquivo(List<String> dadosExistentes){
		
		String linha="";
		
		for(String dado: dadosExistentes) {	
			linha+=(dado+'\n');
			
		}
		return linha;
	}
	

	public static void atualizaArquivo(String url, String[] listaDados, String...titulo) throws IOException {
		File arquivoCSV = new File(new File("").getCanonicalPath()+"/data/".concat(url));
		//isto talvez tenha que ser mudado, criar uma variavel privada para o File das titulo. não ser recriado várias vezes
		FileWriter update = new FileWriter(arquivoCSV, false);

		update.write(String.join(";", titulo)+"\n");
		
		for(int i = 0; i < listaDados.length; i++){
			update.append(i+1+";"+listaDados[i]+"\n");
		}
		update.close();
	}


}