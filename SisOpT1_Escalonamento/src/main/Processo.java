package main;

public class Processo {

	private int num;
	private int tChegada;
	private int tExecucao;
	private int prioridade;

	public Processo(int tChegada, int tExecucao, int prioridade, int num) throws Exception {
		if (prioridade >= 1 && prioridade <= 9) {
			this.tChegada = tChegada;
			this.tExecucao = tExecucao;
			this.prioridade = prioridade;
			this.num = num;
		} else {
			System.out.println("Foi esse numero: " + prioridade);
			throw new Exception("Numero invalido");
		}
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
		return "Processo [num=" + num + ", tChegada=" + tChegada + ", tExecucao=" + tExecucao + ", prioridade="
				+ prioridade + "]";
	}

}
