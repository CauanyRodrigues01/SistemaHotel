package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Funcionario;

public class GerenciamentoFuncionario implements Gerenciamento {

	private final List<Funcionario> funcionarios;
	private final Scanner sc;
	private final GerenciamentoHotel gerenciamentoHotel;

	public GerenciamentoFuncionario(Scanner scanner, GerenciamentoHotel gerenciamentoHotel) {
		this.funcionarios = new ArrayList<>();
		this.sc = scanner;
		this.gerenciamentoHotel = gerenciamentoHotel;
	}

	// Método auxiliar para buscar funcionario pelo CPF e retorná-lo
	protected Optional<Funcionario> buscarFuncionarioPorCpf(String cpfBuscar) {
	    return funcionarios.stream()
	                       .filter(h -> h.getCpf().equals(cpfBuscar))
	                       .findFirst();
	}

	@Override
	public void buscar() {
	    String cpfBuscar = gerenciamentoHotel.lerCpf();
	    
	    buscarFuncionarioPorCpf(cpfBuscar).ifPresentOrElse(
	        funcionario -> System.out.println("Funcionário encontrado: " + funcionario),
	        () -> System.out.println("Funcionário não encontrado.")
	    );
	}
	
	@Override
	public void adicionar() {

		System.out.println("Informe o nome do novo funcionário: ");
		String nomeNovoFuncionario = sc.nextLine();

		String cpfNovoFuncionario = gerenciamentoHotel.lerCpf();

		System.out.println("Informe o cargo: ");
		String cargoNovoFuncionario = sc.nextLine();

		System.out.println("Informe o salário por hora: ");
		double salarioPorHoraNovoFuncionario = sc.nextDouble(); //TODO validar entrada
		sc.nextLine();

		System.out.println("Informe o turno de trabalho: ");
		String turnoNovoFuncionario = sc.nextLine();

		Funcionario novoFuncionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, cargoNovoFuncionario,
				salarioPorHoraNovoFuncionario, turnoNovoFuncionario, null);//TODO validar entrada

		funcionarios.add(novoFuncionario);
		System.out.println("Funcionário cadastrado com sucesso!");
	}

	@Override
	public void editar() {
	    String cpfFuncionario = gerenciamentoHotel.lerCpf();

	    buscarFuncionarioPorCpf(cpfFuncionario).ifPresentOrElse(funcionario -> {
	        System.out.print("Novo nome do funcionário: ");
	        funcionario.setNome(sc.nextLine());

	        System.out.print("Novo CPF: ");
	        funcionario.setCpf(sc.nextLine());

	        System.out.print("Novo cargo: ");
	        funcionario.setCargo(sc.nextLine());

	        System.out.print("Novo salário por hora: ");
	        funcionario.setSalarioPorHora(sc.nextDouble());//TODO validar entrada
	        sc.nextLine(); // Consumir nova linha

	        System.out.print("Novo turno de trabalho: ");
	        funcionario.setTurno(sc.nextLine());

	        System.out.println("Dados do funcionário atualizados com sucesso!");
	    }, () -> System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado."));
	}

	@Override
	public void excluir() {
	    String cpfFuncionario = gerenciamentoHotel.lerCpf();
	    buscarFuncionarioPorCpf(cpfFuncionario).ifPresentOrElse(funcionario -> 
	    {
	        System.out.println("Funcionário encontrado: " + funcionario);
	        System.out.print("Tem certeza de que deseja excluir? (S/N): ");
	        String confirmacao = sc.nextLine();
	        if (confirmacao.trim().equalsIgnoreCase("S")) {
	            funcionarios.remove(funcionario);
	            System.out.println("Funcionário excluído com sucesso!");
	        } else {
	            System.out.println("Exclusão cancelada.");
	        }
	    }, () -> System.out.println("CPF não encontrado."));
	}

	@Override
	public void listar() {
	    if (funcionarios.isEmpty()) {
	        System.out.println("Não existem funcionários cadastrados.");
	    } else {
	    	System.out.println("Listagem de Funcionários do Hotel:");
	        funcionarios.forEach(System.out::println);
	    }
	}

	@Override
	public Map<Integer, String> getOpcoesEspecificas() {
		return Map.of();
	}

	@Override
	public void executarOpcaoEspecifica(int opcao, Scanner sc) {
		switch (opcao) {
		default -> System.out.println("Opção específica inválida");
		}
	}
}
