package main;

public class Processo {

	private int id;

	private int tChegada;
	private int tDeExecucao;
	private int tFinal;
	public int gettFinal() {
		return tFinal;
	}

	public void settFinal(int tFinal) {
		this.tFinal = tFinal;
	}

	private int prioridade;

	private int tEmExecucao;
	
	//tEmExecucao - tChegada
	private int tResposta;
	
	//tCompletou - tChegada
	private int tTurnAround;
	
	//tTurnAround - tDeExecucao
	private int tEspera;

	public Processo(int tChegada, int tDeExecucao, int prioridade, int id) throws Exception {
		if (prioridade >= 1 && prioridade <= 9) {
			this.tChegada = tChegada;
			this.tDeExecucao = tDeExecucao;
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
		this.tDeExecucao = p.gettDeExecucao();
		this.prioridade = p.getPrioridade();
		this.id = p.getId();
		this.tEmExecucao = p.gettEmExecucao();
//		this.tResposta = p.gettResposta();
//		this.tEspera = p.gettEspera();
//		this.tTurnAround = p.gettTurnAround();
	}

	@Override
	public String toString() {
		return "Processo [id=" + id + ", tChegada=" + tChegada + "tDeExecucao=" + tDeExecucao + "tEmExecucao=" + tEmExecucao + "]";
		/*return "Processo [id=" + id + ", tChegada=" + tChegada + ", tDeExecucao=" + tDeExecucao + ", prioridade=" + prioridade
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

	public int gettDeExecucao() {
		return tDeExecucao;
	}

	public void settDeExecucao(int tDeExecucao) {
		this.tDeExecucao = tDeExecucao;
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
		return tTurnAround - tDeExecucao;
	}

	public int calculaTurnAround(int tCompletou) {
		this.tTurnAround = tCompletou - tResposta;
		return tTurnAround;
	}
	
	public int getTurnAround() {
		return tTurnAround;
	}

}
