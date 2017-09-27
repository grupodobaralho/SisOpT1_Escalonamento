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
			this.tEmExecucao = 1;
			this.tResposta = 0;
			this.tEspera = 0;
			this.tTurnAround = 0;

		} else {
			System.out.println("Foi esse idero: " + prioridade);
			throw new Exception("idero invalido");
		}
	}
	
	public Processo(Processo p){
		this.tChegada = p.gettChegada();
		this.tFinal = p.gettFinal();
		this.prioridade = p.getPrioridade();
		this.id = p.getId();
		this.tEmExecucao = p.gettEmExecucao();
//		this.tResposta = p.gettResposta();
//		this.tEspera = p.gettEspera();
//		this.tTurnAround = p.gettTurnAround();
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", tChegada=" + tChegada + "tFinal=" + tFinal + "tEmExecucao=" + tEmExecucao + "]";
		/*return "Processo [id=" + id + ", tChegada=" + tChegada + ", tFinal=" + tFinal + ", prioridade=" + prioridade
				+ ", tEmExecucao=" + tEmExecucao + ", tResposta=" + tResposta + ", tEspera=" + tEspera
				+ ", tTurnAround=" + tTurnAround + "]";
				*/
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
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	public int gettEspera() {
		return tEspera;
	}

	public void settEspera(int tEspera) {
		this.tEspera = tEspera;
	}
	
	public int calculaTResposta() {
		return tEmExecucao - tChegada;
	}

	public int calculaTEspera() {
		return tTurnAround - tFinal;
	}

	public int calculaTurnAround(int tCompletou) {
		this.tTurnAround = tCompletou - tResposta;
		return tTurnAround;
	}
	
	public int getTurnAround() {
		return tTurnAround;
	}

}
