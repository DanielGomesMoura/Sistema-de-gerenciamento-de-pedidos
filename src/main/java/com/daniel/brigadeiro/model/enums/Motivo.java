package com.daniel.brigadeiro.model.enums;

public enum Motivo {

	ESTOQUE_INICIAL(0,"ESTOQUE INICIAL"),
	VENCIMENTO(1,"VENCIMENTO"),
	INVENTARIO(2,"INVENTÁRIO");
	
	private Integer codigo;
	private String descricao;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public static Motivo toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Motivo x : Motivo.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
		
		   throw new IllegalAccessError("Motivo invalido");
	}
	
	private Motivo(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
}
