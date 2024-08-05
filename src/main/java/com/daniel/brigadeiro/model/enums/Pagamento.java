package com.daniel.brigadeiro.model.enums;

public enum Pagamento {

	DINHEIRO(0,"DINHEIRO"),
	DEBITO(1,"DÉBITO"),
	CREDITO(2,"CRÉDITO"),
	PIX(3,"PIX");
	
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

	private Pagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static Pagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Pagamento x : Pagamento.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
		
		   throw new IllegalAccessError("Pagamento Invalido");
		
	}

}
