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

public class ExtratorDeComponentesComValores {

	public static void main(String[] args) throws IOException {
		
		String[] alimentos = {"01001"};	
		String defaultPath = "/home/kleyson/Dev/Projects/java/workspace/NutriExtractor/files/alimentos/";
		Map<String,String> itens = new HashMap<String,String>();
		
		for (String alimento : alimentos) {
			File input = new File(defaultPath+alimento);
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			Elements nodes = doc.getElementsByTag("table").get(0).getElementsByTag("tr");	
			for (Element node : nodes) {
				if (node.getElementsByTag("td").size() > 0 && node.getElementsByTag("td").get(0).hasClass("nutrienteDesc")){					
					String descricao = node.getElementsByClass("nutrienteDesc").get(0).text();
					String unidade = node.getElementsByClass("nutrienteUnidade").get(0).text();
					String valor = node.getElementsByClass("nutrienteValor").get(0).text();
					
					if (descricao != null && unidade != null && valor != null){
						itens.put(descricao + " | " + unidade, valor);
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