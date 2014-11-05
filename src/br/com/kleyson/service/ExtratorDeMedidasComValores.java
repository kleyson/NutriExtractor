package br.com.kleyson.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtratorDeMedidasComValores {

	public static void main(String[] args) throws IOException {
		
		String[] alimentos = {"01001"};	
		String defaultPath = "/sibe/workspace_dbwrench/NutriExtractor/files/alimentos/";
		Map<String,String> itens = new HashMap<String,String>();
		
		for (String alimento : alimentos) {
			File input = new File(defaultPath+alimento);
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			Elements nodes = doc.getElementsByTag("table").get(0).getElementsByTag("th");	
			String text;
			String valor;
			for (Element node : nodes) {
				if (!node.text().contains("Componente") && !node.text().contains("Unidade") && !node.text().contains("(100.00 g)")) {
					int ultimoParenteses = node.text().lastIndexOf("(");
					if (node.text().contains("(")){
						text = node.text().substring(0, ultimoParenteses-1);
						valor = node.text().substring(ultimoParenteses+1, node.text().length()-3);
						itens.put(text, valor);
					}else{
						text = node.text();
					}

				}
			}
		}
		Set<String> chaves = itens.keySet();  
        for (String chave : chaves){  
            if(chave != null)  
                System.out.println(chave + " -  "  + itens.get(chave));  
        }  
	}

}