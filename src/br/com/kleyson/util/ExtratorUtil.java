package br.com.kleyson.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.kleyson.model.Componente;
import br.com.kleyson.model.Medida;

public class ExtratorUtil {

	public static String getNomeAlimento(Document doc) throws IOException {		
		return doc.getElementsByTag("h2").get(1).text().substring(20).toUpperCase();
	}

	public static HashMap<Medida,Double> getMedidasAlimento(Document doc, List<Medida> medidas) throws IOException {		
		HashMap<Medida, Double> itens = new HashMap<Medida,Double>();
		Elements nodes = doc.getElementsByTag("table").get(0).getElementsByTag("th");
		for (Element node : nodes) {
			String text = null;
			Double valor = null;
			Medida medidaEncontrada = null;
			if (!node.text().contains("Componente") && !node.text().contains("Unidade") && !node.text().contains("(100.00 g)")) {
				int ultimoParenteses = node.text().lastIndexOf("(");
				if (node.text().contains("(")){
					text = node.text().substring(0, ultimoParenteses-1);
					for (Medida medida : medidas) {
						if (medida.getDescricao().equals(text)){
							medidaEncontrada = medida;
						}
					}
					valor = Double.valueOf(node.text().substring(ultimoParenteses+1, node.text().length()-3));
					itens.put(medidaEncontrada, valor);
				}
			}
		}
		return itens;
	}

	public static HashMap<Componente,Double> getComponentesAlimento(Document doc, List<Componente> componentes) throws IOException {
		HashMap<Componente, Double> itens = new HashMap<Componente,Double>();
		Elements nodes = doc.getElementsByTag("table").get(0).getElementsByTag("tr");	
		for (Element node : nodes) {
			if (node.getElementsByTag("td").size() > 0 && node.getElementsByTag("td").get(0).hasClass("nutrienteDesc")){					
				String descricao = node.getElementsByClass("nutrienteDesc").get(0).text();
				String valor = node.getElementsByClass("nutrienteValor").get(0).text();
				
				if (descricao != null && valor != null){
					for (Componente componente : componentes) {
						if (componente.getDescricao().equals(descricao)){
							itens.put(componente, Double.valueOf(valor));
						}
					}
					
				}
			}
		}
		return itens;
	}	
}