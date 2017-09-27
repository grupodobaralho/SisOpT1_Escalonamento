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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class App {

	// numero de processos, tamanho de fatia de tempo.
	private static int nProcessos, tamFatiaTempo;

	// cada processo, tempo de chegada, tempo de execucaoo e prioridade (1 ate 9).
	private static List<Processo> processos = new LinkedList<>();

	// Obejto auxiliador na montagem de Strings
	private static StringBuilder str;

	// private static Escalonador escalonador;

	private static List<Processo> pProntos;
	private static Processo emExecucao = null;

	private static int tGlobal = 1;

	public static void main(String[] args) {
		load("Files/trab-so1-teste1.txt");
		pProntos = new LinkedList<>(processos);
		stf();
		// escalonador = new Escalonador(processos);
	}

	public static void stf() {
		boolean terminou = false;

		while (!processos.isEmpty()) {

			apronta();

			Collections.sort(pProntos,
					Comparator.comparing(Processo::gettFinal)
					.thenComparing(Processo::getPrioridade)
					.thenComparing(Processo::gettChegada));

			if (pProntos.isEmpty() && emExecucao == null) {
				str.append("-");
			} else if (pProntos.isEmpty()) {
				if (emExecucao.gettEmExecucao() == emExecucao.gettFinal()) {
					emExecucao = null;
				}
			} else if (emExecucao != null) {
				if (emExecucao.gettEmExecucao() == emExecucao.gettFinal()) {
					emExecucao = processos.get(0);
					pProntos.remove(0);
				} else {
					str.append(emExecucao.getId());
					emExecucao.settEmExecucao(emExecucao.gettEmExecucao()+1);
				}
				emExecucao = pProntos.get(0);
			}

			// Ordenada a lista de processos em estado Pronto para priorizar por
			// menor tempo de
			System.out.println(pProntos);
			tGlobal++;
			terminou = true;
		}

	}

	public static void somaTempoEmEspera() {
		pProntos.forEach(e -> {
			e.settEmExecucao(e.gettEspera() + 1);
		});
	}

	public static void apronta() {
		for (Processo p : processos) {
			if (p.gettChegada() == tGlobal) {
				pProntos.add(p);
				processos.contains(p);
			}
		}
	}

	public static void trocaContexto() {
		int cont = 0;
		while (cont != 2) {
			str.append("TC");
			somaTempoEmEspera();
			apronta();
			str.append("TC");
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
			// System.out.println(nProcessos + " " + tamFatiaTempo);
			int contProcessos = 0;
			while (contProcessos < nProcessos) {
				Processo p = new Processo(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()),
						Integer.parseInt(sc.next()), contProcessos + 1);
				// System.out.println(p.toString());
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