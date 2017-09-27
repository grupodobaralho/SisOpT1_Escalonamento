package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OldEscalonador {

	Queue<Processo> processos;

	// fila de eventos
	Queue<Processo> bloqueados;

	// fila de ready por prioridade
	ArrayList<ArrayList<Processo>> listaPrioridades;

	Processo pEmExecucao;

	StringBuilder resultado;

	int tempo = 0;
	
	int contProcess;

	public OldEscalonador(Queue<Processo> processos) {

		this.processos = processos;
		contProcess = processos.size();
		this.listaPrioridades = new ArrayList<>();
		while (listaPrioridades.size() < 9) {
			listaPrioridades.add(new ArrayList<Processo>());
		}
		this.resultado = new StringBuilder();
		
		int quantasVezesExecutou = 0;
		atualizaProcessos();
		while (contProcess!=0) {			

			quantasVezesExecutou++;
			//System.out.println("quantasVezesExecutou " + quantasVezesExecutou);
			sjf();
			
		}

		bloqueados = new LinkedList<>();
		//System.out.println(bloqueados);
		System.out.println(resultado.toString());
	}

	public void atualizaProcessos() {
		// contador que espera o tempo do processo e envia-o para o escalonador
		int tam = processos.size();
		for (int i = 0; i < tam; i++) {
			Processo p = processos.poll();
			p.settChegada(p.gettChegada() - 1);
			if (p.gettChegada() == 0) {
				listaPrioridades.get(p.getPrioridade()).add(p);
			} else
				processos.add(p);
		}
	}

	// retorna o indice de maior prioridade não-vazio ou -1 se todos vazios
	public int getIndicePrioridade() {
		for (int i = 0; i < listaPrioridades.size(); i++) {
			if (!listaPrioridades.get(i).isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	public void sjf() {
		int indice = getIndicePrioridade();

		if (pEmExecucao != null) {
			if (pEmExecucao.gettExecucao() == 0) {
				if (trocaDeContexto()) {	
						
						pEmExecucao = sjfAux(indice);
						resultado.append(pEmExecucao.getNum());
						contProcess--;	
				}
			} else {
				pEmExecucao.settExecucao(pEmExecucao.gettExecucao() - 1);
				resultado.append(pEmExecucao.getNum()); 
				System.out.println(pEmExecucao + " "+pEmExecucao.getNum());
			}
		} else if (indice != -1) {
			pEmExecucao = sjfAux(indice);
			contProcess--;
			resultado.append(pEmExecucao.getNum()); 
			// System.out.println(processos.size());			
		}
	}

	public Processo sjfAux(int indice) {
		int mTempo = 999999999;
		int indexMenor = 0;
		Processo pMenorTempo = null;
		for (int i = 0; i < listaPrioridades.get(indice).size(); i++) {			
			pMenorTempo = listaPrioridades.get(indice).get(i);
			if (mTempo > pMenorTempo.gettExecucao()) {
				indexMenor = i;
				mTempo = pMenorTempo.gettExecucao();
				
			}
		}		
		System.out.println(listaPrioridades.get(indice).get(indexMenor));
		listaPrioridades.get(indice).remove(indexMenor);
		return pMenorTempo;
	}

	// Faz troca de Contexto
	public boolean trocaDeContexto() {
		int indice = getIndicePrioridade();
		if (indice != -1) {
			resultado.append("T");
			resultado.append("C");
			for (int j = 0; !processos.isEmpty() || j < 2; j++) {
				atualizaProcessos();
			}
			return true;
		}
		return false;
	}
}
