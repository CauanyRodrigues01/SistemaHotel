package controller;

import java.util.Map;
import java.util.Scanner;

public interface Gerenciamento {
	public void adicionar();
	public void editar();
	public void excluir();
	public void listar();
	
    Map<Integer, String> getOpcoesEspecificas();
    void executarOpcaoEspecifica(int opcao, Scanner sc);
}
