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
	
	public void registrarHoras() {
	    String cpfFuncionario = gerenciamentoHotel.lerCpf();
	    
	    buscarFuncionarioPorCpf(cpfFuncionario).ifPresentOrElse(funcionario -> {
	        System.out.print("Informe a quantidade de horas a registrar: ");
	        int horas = gerenciamentoHotel.lerInt();
	        
	        if (gerenciamentoHotel.validarHoras(horas)) {
	            funcionario.registrarHoras(horas);
	            System.out.println("Horas registradas com sucesso!");
	        } else {
	            System.out.println("Registro de horas falhou devido a entradas inválidas.");
	        }
	    }, () -> System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado."));
	}
	
	public void calcularSalario() {
	    String cpfFuncionario = gerenciamentoHotel.lerCpf();
	    
	    buscarFuncionarioPorCpf(cpfFuncionario).ifPresentOrElse(funcionario -> {
	        double salario = funcionario.calcularSalario();
	        System.out.println("O salário calculado para " + funcionario.getNome() + " é: R$ " + salario);
	    }, () -> System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado."));
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

		System.out.print("Informe o nome do novo funcionário: ");
		String nomeNovoFuncionario = sc.nextLine();

		String cpfNovoFuncionario = gerenciamentoHotel.lerCpf();

		System.out.print("Informe o cargo: ");
		String cargoNovoFuncionario = sc.nextLine();

		double salarioPorHoraNovo = gerenciamentoHotel.lerSalarioPorHoraValido();

		System.out.print("Informe o turno de trabalho: ");
		String turnoNovoFuncionario = sc.nextLine();

		Funcionario novoFuncionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, cargoNovoFuncionario,
				salarioPorHoraNovo, turnoNovoFuncionario);

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

	        double salarioPorHoraNovo = gerenciamentoHotel.lerSalarioPorHoraValido();
	        funcionario.setSalarioPorHora(salarioPorHoraNovo);

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
		return Map.of(6, "Registrar Horas", 7, "Calcular Salário");
	}

	@Override
	public void executarOpcaoEspecifica(int opcao, Scanner sc) {
		switch (opcao) {
		case 6 -> registrarHoras();
		case 7 -> calcularSalario();
		default -> System.out.println("Opção específica inválida");
		}
	}
}
