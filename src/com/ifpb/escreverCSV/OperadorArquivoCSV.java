package com.ifpb.escreverCSV;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OperadorArquivoCSV {
	
	public void criarArquivo(String url) {
		
		try {
			File arquivoCSV = new File(url);
			arquivoCSV.createNewFile();
		
		}
			
		catch(Exception error) {
			System.out.println("DEu erro mané");
		}
	};
	
	public void escreverArquivo (String url , String...dados) {
		
		criarArquivo(url);
		
		try{			
			List<String> dadosExistentes=lerArquivo(url);
			String linhaDados= String.join(";",dados);
			
			dadosExistentes.add(linhaDados);
			
			//adicionar no csv
			String todasLinhas= lerArquivo(dadosExistentes);
			FileWriter arquivo = new FileWriter(url);
			arquivo.write(todasLinhas);
			arquivo.close();
			
		
		} catch(Exception e){
			System.out.println("Erro ao gerar o arquivo "+ url);
		}
	}
	
	public List<String> lerArquivo(String url){
		
		List<String> resultado = new ArrayList<String>();
		
		try {
			Path path= Paths.get(url);
			resultado= Files.readAllLines(path);
		}
		catch(Exception error ){
			System.out.println("DEu erro mané");
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
}
