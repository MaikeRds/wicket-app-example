package com.maike.contato;

public enum EstadoCivil {
	
	SOTEIRO("Soteiro"), CASADO("Casado"), DIVORCIADO("Divorciado"), VIUVO("Viúvo");
	
	private final String label;
	
	private EstadoCivil(String label) {
		this.label = label;
	}
	
	
	public String getLabel() {
		return this.label;
	}
	

}
