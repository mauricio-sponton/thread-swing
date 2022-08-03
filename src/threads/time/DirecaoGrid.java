package threads.time;

public enum DirecaoGrid {

	X("X"), Y("Y"), VAZIO("VAZIO");
	
	private String descricao;
	
	private DirecaoGrid(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
