package main;

import java.awt.Image;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class App {
	
	//numero de processos, tamanho de fatia de tempo.
	private static int nProcessos, tamFatiaTempo;
	
	//cada processo, tempo de chegada, tempo de execucaoo e prioridade (1 ate 9).
	private static Queue<Processo> processos = new LinkedList<>();
	
	//Obejto auxiliador na montagem de Strings
	private static StringBuilder str;
	
	private static Escalonador escalonador;
	
	public static void main(String[] args) {
		load("Files/trab-so1-teste1.txt");
		escalonador = new Escalonador(processos);
		
	}	
	/*
	 * numero de processos, tamanho de fatia de tempo, e para cada processo, tempo de chegada, tempo de execucao e prioridade (1 ate 9).
	 * 
5
3
3 10 2
4 12 1
9 15 2
11 15 1
12 8 5
	 */
	public static void load(String arquivo) {

		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
			nProcessos = Integer.parseInt(sc.next());
			tamFatiaTempo = Integer.parseInt(sc.next());
			//System.out.println(nProcessos + " " + tamFatiaTempo);
			int contProcessos = 0;
			while(contProcessos < nProcessos){
				Processo p = new Processo(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), Integer.parseInt(sc.next()), contProcessos+1);
				//System.out.println(p.toString());
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
		//MaiorArea m = new MaiorArea(largura, altura, quantidade, minas);
		//m.printa();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Tempo decorrido: " + elapsedTime);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

}
