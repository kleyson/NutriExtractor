package br.com.kleyson.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import br.com.kleyson.model.Componente;
import br.com.kleyson.model.Medida;
import br.com.kleyson.util.ExtratorUtil;

public class Extrator {

	public static void main(String[] args) throws IOException {
		
		System.out.println("---- Medidas dos Componentes ----");
		int id = 1;
		ArrayList<Componente> componentes = ExtratorDeComponentes.getComponentes();
		for (Componente componente : componentes) {
			System.out.println("insert into componentes values ("+ id + ", '" + componente.getDescricao().toUpperCase() + "')"); 
			id++;
		}
		System.out.println("-----------------");

		System.out.println("---- Medidas ----");		
		id = 1;
		ArrayList<Medida> medidas = ExtratorDeMedidas.getMedidas();
		for (Medida medida : medidas) {
			System.out.println("insert into medidas values ("+ id + ", '" + medida.getDescricao().toUpperCase() + "')");
			id++;
		}
		System.out.println("-----------------");
		
		String[] alimentos = {"01001", "01002", "01003", "01004"};	
		String defaultPath = "/sibe/workspace_dbwrench/NutriExtractor/files/alimentos/";
		int idAlimento = 1;
		for (String alimento : alimentos) {
			File input = new File(defaultPath + alimento);
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			
			// Gerar Alimentos;
			System.out.println("insert into medidas values ("+ id + ", '" + ExtratorUtil.getNomeAlimento(doc).toUpperCase() + "')");

			
			HashMap<Medida,Double> medidasAlimento = ExtratorUtil.getMedidasAlimento(doc, medidas);
			
			System.out.println("---- Medidas dos Alimentos ----");
			id = 1;
			Set<Medida> chaves = medidasAlimento.keySet();  
	        for (Medida chave : chaves){  
	            if(chave != null)  
	            	System.out.println("insert into alimentos_medidas values ("+ id + ", " + idAlimento + ", " + chave.getId() + ","+ medidasAlimento.get(chave) +")");
	            id++;
	        }
			System.out.println("-----------------");
			
			HashMap<Componente,Double> componentesAlimento = ExtratorUtil.getComponentesAlimento(doc, componentes);
			System.out.println("---- Componentes dos Alimentos ----");
			id = 1;
			Set<Componente> keys = componentesAlimento.keySet();  
	        for (Componente chave : keys){  
	            if(chave != null)  
	            	System.out.println("insert into alimentos_componentes values ("+ id + ", " + idAlimento + ", " + chave.getId() + ","+ componentesAlimento.get(chave) +")");
	            id++;
	        }  
			System.out.println("-----------------");
			idAlimento++;
		}
	}
}
