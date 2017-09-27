package main;

public class Processo {

	private int id;

	private int tChegada;
	private int tFinal;
	private int prioridade;

	private int tEmExecucao;
	
	//tEmExecucao - tChegada
	private int tResposta;
	
	//tCompletou - tChegada
	private int tTurnAround;
	
	//tTurnAround - tFinal
	private int tEspera;

	public Processo(int tChegada, int tFinal, int prioridade, int id) throws Exception {
		if (prioridade >= 1 && prioridade <= 9) {
			this.tChegada = tChegada;
			this.tFinal = tFinal;
			this.prioridade = prioridade;
			this.id = id;
			this.tEmExecucao = 0;
			this.tResposta = 0;
			this.tEspera = 0;
			this.tTurnAround = 0;

		} else {
			System.out.println("Foi esse idero: " + prioridade);
			throw new Exception("idero invalido");
		}
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", tChegada=" + tChegada + ", tFinal=" + tFinal + ", prioridade=" + prioridade
				+ ", tEmExecucao=" + tEmExecucao + ", tResposta=" + tResposta + ", tEspera=" + tEspera
				+ ", tTurnAround=" + tTurnAround + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int gettChegada() {
		return tChegada;
	}

	public void settChegada(int tChegada) {
		this.tChegada = tChegada;
	}

	public int gettFinal() {
		return tFinal;
	}

	public void settFinal(int tFinal) {
		this.tFinal = tFinal;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int gettEmExecucao() {
		return tEmExecucao;
	}

	public void settEmExecucao(int tEmExecucao) {
		this.tEmExecucao = tEmExecucao;
	}

	public int gettResposta() {
		return tResposta;
	}

	public void settResposta(int tResposta) {
		this.tResposta = tResposta;
	}

	public int gettEspera() {
		return tEspera;
	}

	public void settEspera(int tEspera) {
		this.tEspera = tEspera;
	}

	public int gettTurnAround() {
		return tTurnAround;
	}

	public void settTurnAround(int tTurnAround) {
		this.tTurnAround = tTurnAround;
	}
	
		

}
