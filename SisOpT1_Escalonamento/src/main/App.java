package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
	
	//número de processos, tamanho de fatia de tempo.
	private static int nProcessos, tamFatiaTempo;
	//cada processo, tempo de chegada, tempo de execução e prioridade (1 até 9).
	private static List<Processo> processos = new ArrayList<>();
	//Obejto auxiliador na montagem de Strings
	private static StringBuilder str;
	
	public static void main(String[] args) {
		load("Files/trab-so1-teste1.txt");
	}
	
	
	/*
	 * número de processos, tamanho de fatia de tempo, e para cada processo, tempo de chegada, tempo de execução e prioridade (1 até 9).
	 * 
5
3
3 10 2
4 12 1
9 15 2
11 15 1
12 8 5
	 */
	/**
	 * Recebe um caminho para um arquivo .txt e utiliza a classe
	 * Scanner chamada do Sistema Operacional de leitura de arquivos
	 * e armazena as informacoes nas variaveis globais
	 * @param arquivo
	 */
	public static void load(String arquivo) {

		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			nProcessos = Integer.parseInt(sc.next());
			tamFatiaTempo = Integer.parseInt(sc.next());
			System.out.println(nProcessos + " " + tamFatiaTempo);
			int contProcessos = 0;
			while(contProcessos < nProcessos){
				Processo p = new Processo(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
				System.out.println(p.toString());
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

	/**
	 * Metodo que a partir de um caminho para um arquivo .txt
	 * chama load() para ler os dados, calcula a maior area livre de 
	 * minas e imprime os resultados na tela
	 * @param arquivo
	 */
	public static void fazAlgEscalonamento(String arquivo) {
		long startTime = System.currentTimeMillis();
		load(arquivo);
		System.out.println("Calculando Maior Area sem minas...");
		//MaiorArea m = new MaiorArea(largura, altura, quantidade, minas);
		//m.printa();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Tempo decorrido: " + elapsedTime);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}
