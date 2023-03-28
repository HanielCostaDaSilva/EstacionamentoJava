package classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OperadorArquivoCSV {
	
	public void criarArquivo(String url, String ...Titulo) throws IOException {
		
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
	
	public void escreverArquivo (String url , String...dados) {
		
		
		try{			
			List<String> dadosExistentes=lerArquivo(url);
			String linhaDados= String.join(";",dados);
			
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
	
	public List<String> lerArquivo(String url){
		
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
	
	public String lerArquivo(List<String> dadosExistentes){
		
		String linha="";
		
		for(String dado: dadosExistentes) {	
			linha+=(dado+'\n');
			
		}
		return linha;
	}


	public void atualizaArquivo(String url, String[] placas) throws IOException {
		File arquivoCSV = new File(new File("").getCanonicalPath()+"/data/".concat(url));
		arquivoCSV.createNewFile();
		FileWriter update = new FileWriter(arquivoCSV, false);
		update.write("vaga,placa"+"\n");
		for(int i = 0; i < placas.length; i++){
			if (i ==  placas.length-1){
				update.write(i+1+","+placas[i]);
			}else{
				update.write(i+1+","+placas[i]+"\n");
			}
		}
		update.close();
	}


}