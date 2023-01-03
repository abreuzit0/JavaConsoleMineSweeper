package br.com.abrzit0.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.abrzit0.cm.excecao.ExplosaoException;

public class CampoTeste {
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo(3,2);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo(3,4);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2,3);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4,3);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2,2);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1);
		
		boolean resultado = campo.adicionarVizinho(vizinho);
		
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcacao() {
		assertFalse(campo.isMarcado());
		
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
		
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);
		
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo22 = new Campo(2,2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void testeLinhaColuna() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		
		assertTrue(campo11.getLinha() == 1 && campo12.getColuna() == 2);
	}
	
	@Test
	void testeReiniciarJogo() {
		campo.reiniciar();

		assertTrue(campo.isFechado());
		assertFalse(campo.isMarcado() || campo.isMinado());
	}
	
	@Test
	void testeMinasNaVizinhanca1() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		Campo campo22 = new Campo(2,2);

		campo12.minar();
		campo22.minar();
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		
		assertTrue(campo.minasNaVizinhanca() == 1);
	}
	
	@Test
	void testeMinasNaVizinhanca2() {
		Campo campo11 = new Campo(1,1);
		Campo campo12 = new Campo(1,2);
		Campo campo22 = new Campo(2,2);
		
		campo11.minar();
		campo12.minar();
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		assertTrue(campo22.minasNaVizinhanca() == 2);
	}
	
	@Test
	void testeStringMarcado() {
		campo.alternarMarcacao();
		
		assertTrue(campo.toString().equalsIgnoreCase("x"));
	}
	
	@Test
	void testeStringAbertoEMinado() {
		campo.abrir();
		campo.minar();
		
		assertTrue(campo.toString().equalsIgnoreCase("*"));
	}
	
	@Test
	void testeStringAbertoEMinasNaVizinhanca() {
		campo.abrir();
		Campo campo22 = new Campo(2,2);
		campo.adicionarVizinho(campo22);
		campo22.minar();
		
		assertTrue(campo.toString().equals("1"));
	}
	
	@Test
	void testeStringAberto() {
		campo.abrir();
		
		assertTrue(campo.toString().equals(" "));
	}
	
	@Test
	void testeStringCampoPadrao() {
		
		assertTrue(campo.toString().equalsIgnoreCase("?"));
	}
}
