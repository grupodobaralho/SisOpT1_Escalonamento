package main;

import java.awt.Image;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class App {

	// numero de processos, tamanho de fatia de tempo.
	private static int nProcessos, tamFatiaTempo;

	// cada processo, tempo de chegada, tempo de execucaoo e prioridade (1 ate
	// 9).
	private static List<Processo> processos;

	// Obejto auxiliador na montagem de Strings
	private static StringBuilder str;

	// private static Escalonador escalonador;

	private static List<Processo> pProntos;
	private static Processo emExecucao = null;
	private static List<Processo> pTerminados;

	private static int tGlobal = 1;

	public static void main(String[] args) {
		processos = new ArrayList<>();
		pProntos = new ArrayList<>(processos);
		pTerminados = new ArrayList<>();
		load("Files/trab-so1-teste4.txt");
		str = new StringBuilder();
		//sjf();
		rr();
		System.out.println(str.toString());

		double TTM = 0.0;
		double TRM = 0.0;
		double TRE = 0.0;

		for (Processo p : pTerminados) {
			TTM += p.gettFinal() - p.gettChegada();
			TRM += p.gettEspera();
		}		
		
		//Turnaround
		System.out.printf("\nTTM %.2f\n", TTM / pTerminados.size());
		//Resposta
		System.out.printf("\nTRM %.2f\n", TRM / pTerminados.size());
		//Espera
		TRE = TRM;
		System.out.printf("\nTRE %.2f\n", TRE);
		System.out.printf("\n%d\n", pTerminados.size());
		
		/*
		double TTM = 0.0;
		double TRM = 0.0;
		double TEM = 0.0;
		*/
		
	}

	public static void sjf() {
		boolean terminou = false;
		apronta();
		while (!terminou) {
			Collections.sort(pProntos, Comparator.comparing(Processo::gettDeExecucao)
					.thenComparing(Processo::getPrioridade).thenComparing(Processo::gettChegada));

			// Se pProntos est� vazio e n�o existe processo em Exec. poe "-"
			if (pProntos.isEmpty() && emExecucao == null) {
				str.append("-");
				// Se s� pProntos est� vazio, atualiza emExec e printa ele
			} else if (pProntos.isEmpty()) {
				// se acabou, tira
				if (emExecucao.gettEmExecucao() == emExecucao.gettDeExecucao()) {
					pTerminados.add(emExecucao);
					tGlobal--;
					pTerminados.get(pTerminados.size() - 1).settFinal(tGlobal);
					emExecucao = null;
				} else { // se nao acabou, atualiza o tempo de Execucao e printa
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
				}
			} else if (!pProntos.isEmpty() && emExecucao == null) {
				emExecucao = new Processo(pProntos.get(0));
				emExecucao.settEspera(tGlobal - emExecucao.gettChegada());
				pProntos.remove(0);
				str.append(emExecucao.getId());
				// Se pProntos n�o est� vazio e emExec nao for nulo
			} else {
				// Se finalizou faz TC
				if (emExecucao.gettEmExecucao() == emExecucao.gettDeExecucao()) {
					trocaContexto();
					Collections.sort(pProntos, Comparator.comparing(Processo::gettDeExecucao)
							.thenComparing(Processo::getPrioridade).thenComparing(Processo::gettChegada));
					pTerminados.add(emExecucao);
					pTerminados.get(pTerminados.size() - 1).settFinal(tGlobal);					
					tGlobal++;
					apronta();
					emExecucao = null;
					// Se nao finalizou, printa e atualiza
				} else {
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
				}
			}

			// Ordenada a lista de processos em estado Pronto para priorizar por
			// menor tempo de
			tGlobal++;
			somaTempoEmEspera();
			apronta();
			if (processos.isEmpty() && pProntos.isEmpty() && emExecucao == null)
				terminou = true;
		}

	}

	public static void rr() {
		boolean terminou = false;
		apronta();
		Collections.sort(pProntos, Comparator.comparing(Processo::getPrioridade)
				.thenComparing(Processo::gettVolta)
				.thenComparing(Processo::gettChegada));
		while (!terminou) {
			Collections.sort(pProntos, Comparator.comparing(Processo::getPrioridade)
					.thenComparing(Processo::gettVolta)
					.thenComparing(Processo::gettChegada));
			// Se pProntos esWt� vazio e n�o existe processo em Exec. poe "-"
			if (pProntos.isEmpty() && emExecucao == null) {
				str.append("-");	
			} 
			// Se s� pProntos est� vazio, atualiza emExec e printa ele
			else if (pProntos.isEmpty()) {
				// se acabou o processo, tira
				if (emExecucao.gettEmExecucao() == emExecucao.gettDeExecucao()) {
					pTerminados.add(emExecucao);
					tGlobal--;
					pTerminados.get(pTerminados.size() - 1).settFinal(tGlobal);
					emExecucao = null;
				} else { // se nao acabou, atualiza o tempo de Execucao e printa
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
					emExecucao.settEmExecucaoFatia(emExecucao.gettEmExecucaoFatia() + 1);
				}				
			} 
			//Se s� emExecucao est� vazio, pega o primeiro da fila ordenada
 			else if (!pProntos.isEmpty() && emExecucao == null) {
				emExecucao = new Processo(pProntos.get(0));
				emExecucao.settEspera(tGlobal - emExecucao.gettChegada());
				pProntos.remove(0);
				str.append(emExecucao.getId());	
			} 
			// Se pProntos n�o est� vazio e emExec tamb�m
			else {
				// Se o processo finalizou, faz TC e atualiza
				if (emExecucao.gettEmExecucao() == emExecucao.gettDeExecucao()) {
					trocaContexto();
					pTerminados.add(emExecucao);
					pTerminados.get(pTerminados.size() - 1).settFinal(tGlobal);
					tGlobal++;
					emExecucao = null;
				}
				// se acabou a fatia de tempo, troca
				else if(emExecucao.gettEmExecucaoFatia() == tamFatiaTempo) {
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao()+1);
					emExecucao.settEmExecucaoFatia(1);
					emExecucao.settVolta(tGlobal);	
					trocaContexto();
					tGlobal++;
					pProntos.add(emExecucao);
					emExecucao = null;
				} 
				// Se nao finalizou e ainda tem tempo, printa e atualiza
				else { 
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
					emExecucao.settEmExecucaoFatia(emExecucao.gettEmExecucaoFatia() + 1);
				}
			}

			// Ordenada a lista de processos em estado Pronto para priorizar por
			// menor tempo de
			somaTempoEmEspera();
			apronta();
			tGlobal++;
			if (processos.isEmpty() && pProntos.isEmpty() && emExecucao == null)
				terminou = true;
		}

	}

	public static void somaTempoEmEspera() {
		pProntos.forEach(e -> {
			e.settEspera(e.gettEspera() + 1);
		});
	}

	public static void apronta() {
		for (int i = 0; i < processos.size(); i++) {
			//System.out.println("pID E tCh: "+processos.get(i).getId()+"  "+ processos.get(i).gettChegada() + "   Tglobal: "+ tGlobal);
			if (processos.get(i).gettChegada() == tGlobal) {
				pProntos.add(processos.get(i));
				processos.remove(processos.get(i));
				i--;
			}
		}
	}

	public static void trocaContexto() {
		int cont = 0;
		while (cont != 2) {
			if (cont == 0)
				str.append("T");
			else
				str.append("C");
			somaTempoEmEspera();
			apronta();
			cont++;
		}

	}

	/*
	 * numero de processos, tamanho de fatia de tempo, e para cada processo, tempo
	 * de chegada, tempo de execucao e prioridade (1 ate 9).
	 * 
	 * 5 3 3 10 2 4 12 1 9 15 2 11 15 1 12 8 5
	 */
	public static void load(String arquivo) {
		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			nProcessos = Integer.parseInt(sc.next());
			tamFatiaTempo = Integer.parseInt(sc.next());
			int contProcessos = 0;
			while (contProcessos < nProcessos) {
				Processo p = new Processo(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()),
						Integer.parseInt(sc.next()), contProcessos + 1);
				processos.add(p);
				contProcessos++;
			}
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}

}