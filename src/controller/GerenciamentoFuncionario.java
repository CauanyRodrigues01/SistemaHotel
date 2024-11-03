package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Funcionario;
import model.Hospede;

public class GerenciamentoFuncionario implements Gerenciamento {

    private List<Funcionario> funcionarios;

    public GerenciamentoFuncionario() {
        this.funcionarios = new ArrayList<>();
    }

    @Override
    public void adicionar() {

        Scanner sc = new Scanner(System.in);

        System.out.println("---- Você escolheu a opção para cadastrar um novo funcionário ---");

        System.out.println("Informe o nome do novo funcionário: ");
        String nomeNovoFuncionario = sc.nextLine();

        System.out.println("Informe o CPF: ");
        String cpfNovoFuncionario = sc.nextLine();

        System.out.println("Informe o cargo: ");
        String cargoNovoFuncionario = sc.nextLine();

        System.out.println("Informe o salário por hora: ");
        double salarioPorHoraNovoFuncionario = sc.nextDouble();
        sc.nextLine();

        System.out.println("Informe o turno de trabalho: ");
        String turnoNovoFuncionario = sc.nextLine();

        Funcionario novoFuncionario = new Funcionario(nomeNovoFuncionario, cpfNovoFuncionario, cargoNovoFuncionario,
                salarioPorHoraNovoFuncionario, turnoNovoFuncionario, null);

        funcionarios.add(novoFuncionario);

        sc.close();
    }

    @Override
    public void editar() {

        Scanner sc = new Scanner(System.in);
        System.out.println("---- Você escolheu a opção para editar os dados de um funcionário ---");
        System.out.println("Informe o CPF do funcionario que deseja editar as informações: ");
        boolean encontrado = false;
        String cpfFuncionario = sc.nextLine();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpfFuncionario)) {

                System.out.println("---- Os dados do funcionário serão editados ----");

                System.out.println("Informe o nome do funcionário: ");
                String nomeEditadoFuncionario = sc.nextLine();

                System.out.println("Informe o CPF: ");
                String cpfEditadoFuncionario = sc.nextLine();

                System.out.println("Informe o cargo:: ");
                String cargoEditadoFuncionario = sc.nextLine();

                System.out.println("Novo salário por hora: ");
                double salarioPorHoraEditadoFuncionario = sc.nextDouble();
                sc.nextLine();

                System.out.println("Informe o turno de trabalho: ");
                String turnoEditadoFuncionario = sc.nextLine();

                funcionario.setNome(nomeEditadoFuncionario);
                funcionario.setCpf(cpfEditadoFuncionario);
                funcionario.setCargo(cargoEditadoFuncionario);
                funcionario.setSalarioPorHora(salarioPorHoraEditadoFuncionario);
                funcionario.setTurno(turnoEditadoFuncionario);

                System.out.println("Dados atualizados com sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("CPF não encontrado.Certifique-se de que o funcionário está cadastrado.");
        }

        sc.close();
    }

    @Override
    public void excluir() {

        Scanner sc = new Scanner(System.in);
        System.out.println("---- Você escolheu a opção para excluir um funcionário ---");
        System.out.println("Informe o CPF do funcionario que deseja excluir: ");
        String cpfFuncionario = sc.nextLine();
        boolean encontrado = false;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpfFuncionario)) {
                System.out.println("Funcionário encontrado!");
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("CPF: " + funcionario.getCpf());
                System.out.println("Cargo: " + funcionario.getCargo());

                System.out.println("Tem certeza de que deseja excluir este funcionário? (S/N)");
                String confirmacao = sc.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    funcionarios.remove(funcionario);
                    System.out.println("Funcionário excluído com sucesso!");
                    encontrado = true;
                    break;
                } else {
                    System.out.println("Exclusão cancelada.");
                    encontrado = true;
                    break;
                }
            }
        }
        if (!encontrado) {
            System.out.println("CPF não encontrado.Certifique-se de que o funcionário está cadastrado.");
        }

        sc.close();
    }

    @Override
    public void listar() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não existem funcionários cadastrados.");
        } else {
            System.out.println("Lista com os funcionários cadastrados:");
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }
}
