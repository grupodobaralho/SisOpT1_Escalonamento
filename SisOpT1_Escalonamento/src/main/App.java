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

	private static int tGlobal = 1;
	private static int tFinalizacao = 0;

	public static void main(String[] args) {
		processos = new ArrayList<>();
		pProntos = new ArrayList<>(processos);
		load("Files/trab-so1-teste1.txt");
		str = new StringBuilder();
		stf();
		System.out.println(str.toString());
		// escalonador = new Escalonador(processos);
	}

	public static void stf() {
		boolean terminou = false;
	
		while (!terminou) {
			apronta();
			Collections.sort(pProntos, Comparator.comparing(Processo::gettFinal).thenComparing(Processo::getPrioridade)
					.thenComparing(Processo::gettChegada));

			// Se pProntos está vazio e não existe processo em Exec. poe "-"
			if (pProntos.isEmpty() && emExecucao == null) {
				str.append("-");
			} else if (pProntos.isEmpty()) { // Se só pProntos está vazio,
												// atualiza emExec e printa ele
				if (emExecucao.gettEmExecucao() == emExecucao.gettFinal()) { // se
																				// acabou,
																				// tira
					str.append(emExecucao.getId());
					emExecucao = null;
				} else { // se nao acabou, atualiza o tempo de Execucao e printa
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
				}
			} else if (!pProntos.isEmpty() && emExecucao == null){
				emExecucao = new Processo(pProntos.get(0));
				pProntos.remove(0);
				str.append(emExecucao.getId());
			} else { // Se pProntos não está vazio e
												// emExec nao for nulo, atualiza
												// tudo e faz TC
				if (emExecucao.gettEmExecucao() == emExecucao.gettFinal()) { //Se finalizou faz TC
					str.append(emExecucao.getId());
					trocaContexto();
					emExecucao = null;
				} else { //Se nao finalizou, printa e atualiza
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao() + 1);
				}
			} 

			// Ordenada a lista de processos em estado Pronto para priorizar por
			// menor tempo de
			tGlobal++;
			somaTempoEmEspera();
			if(processos.isEmpty() && pProntos.isEmpty() && emExecucao==null)
				terminou = true;
		}

	}

	public static void somaTempoEmEspera() {
		pProntos.forEach(e -> {
			e.settEmExecucao(e.gettEspera() + 1);
		});
	}

	public static void apronta() {
		// System.out.println("ProcessosFila = "+ processos);
		for (int i = 0; i < processos.size(); i++) {
			if (processos.get(i).gettChegada() == tGlobal) {
				pProntos.add(processos.get(i));
				processos.remove(processos.get(i));
				i--;
			}
		}
		// System.out.println("ProcessosProntos = "+ pProntos);
	}

	public static void trocaContexto() {
		int cont = 0;
		//while (cont != 2) {
			str.append("TC");
			somaTempoEmEspera();
			apronta();
			Collections.sort(pProntos, Comparator.comparing(Processo::gettFinal).thenComparing(Processo::getPrioridade)
					.thenComparing(Processo::gettChegada));
			cont++;
		//}

	}

	/*
	 * numero de processos, tamanho de fatia de tempo, e para cada processo,
	 * tempo de chegada, tempo de execucao e prioridade (1 ate 9).
	 * 
	 * 5 3 3 10 2 4 12 1 9 15 2 11 15 1 12 8 5
	 */
	public static void load(String arquivo) {
		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			nProcessos = Integer.parseInt(sc.next());
			tamFatiaTempo = Integer.parseInt(sc.next());
			// System.out.println(nProcessos + " " + tamFatiaTempo);
			int contProcessos = 0;
			while (contProcessos < nProcessos) {
				Processo p = new Processo(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()),
						Integer.parseInt(sc.next()), contProcessos + 1);
				// System.out.println(p.toString());
				tFinalizacao+=p.gettFinal();
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

	public static void fazAlgEscalonamento(String arquivo) {
		long startTime = System.currentTimeMillis();
		load(arquivo);
		System.out.println("Calculando Maior Area sem minas...");
		// MaiorArea m = new MaiorArea(largura, altura, quantidade, minas);
		// m.printa();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Tempo decorrido: " + elapsedTime);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}