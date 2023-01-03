package br.com.abrzit0.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.abrzit0.cm.excecao.ExplosaoException;
import br.com.abrzit0.cm.excecao.SairException;
import br.com.abrzit0.cm.modelo.Tabuleiro;

public class TabConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Deseja iniciar outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}

			}
		} catch (SairException e) {
			System.out.println("Ate breve!");																																												
		} finally {
			entrada.close();
		}
		
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
				.map(e -> Integer.parseInt(e.trim())).iterator();
				
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
				
			}
			System.out.println(tabuleiro);
			System.out.println("Voce ganhou!!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Voce perdeu!!");
		}
		
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print(texto + "\n\n");
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
