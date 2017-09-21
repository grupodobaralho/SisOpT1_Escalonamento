package main;

public class Processo {
	
	private int tChegada;
	private int tExecucao;
	private int prioridade;
	
	public Processo(int tChegada, int tExecucao, int prioridade) throws Exception {
		this.tChegada = tChegada;
		this.tExecucao = tExecucao;
		if(prioridade >=1 && prioridade <= 9)
			this.prioridade = prioridade;
		else {
			System.out.println("Foi esse numero: " + prioridade);
			throw new Exception("Numero invalido");
		}
	}

	public int gettChegada() {
		return tChegada;
	}

	public void settChegada(int tChegada) {
		this.tChegada = tChegada;
	}

	public int gettExecucao() {
		return tExecucao;
	}

	public void settExecucao(int tExecucao) {
		this.tExecucao = tExecucao;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public String toString() {
		return "Processo [tChegada=" + tChegada + ", tExecucao=" + tExecucao + ", prioridade=" + prioridade + "]";
	}		
	

}
