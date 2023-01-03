package br.com.abrzit0.cm;

import br.com.abrzit0.cm.modelo.Tabuleiro;
import br.com.abrzit0.cm.visao.TabConsole;

public class App {
	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6 ,6);
		new TabConsole(tabuleiro);
	}
}
