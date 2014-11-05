package br.com.kleyson.model;

import java.util.Map;

public class Alimento {

	private Long id;
	private String descricao;
	private Map<Medida,Long> medidas;
	private Map<Componente,Long> componentes;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Map<Medida,Long> getMedidas() {
		return medidas;
	}
	public void setMedidas(Map<Medida, Long> medidas) {
		this.medidas = medidas;
	}
	public Map<Componente, Long> getComponentes() {
		return componentes;
	}
	public void setComponentes(Map<Componente, Long> componentes) {
		this.componentes = componentes;
	}
	
}
