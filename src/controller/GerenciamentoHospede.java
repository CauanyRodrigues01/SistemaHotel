package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;

public class GerenciamentoHospede implements Gerenciamento {
	
    private final List<Hospede> hospedes;
    private final Scanner sc;

    public GerenciamentoHospede(Scanner scanner) {
        this.hospedes = new ArrayList<>();
        this.sc = scanner;
    }
    
    // Método auxiliar para buscar hóspede pelo CPF e retorná-lo
    protected Optional<Hospede> buscarHospedePorCpf(String cpfBuscar) {
        return hospedes.stream()
                       .filter(h -> h.getCpf().equals(cpfBuscar))
                       .findFirst();
    }
    
    @Override
    public void buscar() {
        System.out.print("Digite o CPF do hóspede para buscar: ");
        String cpfBuscar = sc.nextLine();
        
        buscarHospedePorCpf(cpfBuscar).ifPresentOrElse(
            hospede -> System.out.println("Hóspede encontrado: " + hospede),
            () -> System.out.println("Hóspede não encontrado.")
        );
    }

    @Override
    public void adicionar() {
        
        System.out.print("Nome do hóspede: ");
        String nome = sc.nextLine();

        System.out.print("CPF do hóspede: ");
        String cpf = sc.nextLine();

        //TODO validar data
        System.out.print("Data de nascimento do hóspede (dd/mm/aaaa): ");
        String dataNascimento = sc.nextLine();

        System.out.print("Endereço do hóspede: ");
        String endereco = sc.nextLine();

        System.out.print("Contato do hóspede: ");
        String contato = sc.nextLine();

        Hospede novoHospede = new Hospede(nome, cpf, dataNascimento, endereco, contato);
        
        hospedes.add(novoHospede);

        System.out.println("\nHóspede adicionado com sucesso!");
    }

    @Override
    public void editar() {
        System.out.print("CPF do hóspede a ser editado: ");
        String cpf = sc.nextLine();

        buscarHospedePorCpf(cpf).ifPresentOrElse(hospede -> {
            System.out.print("Novo nome (atual: " + hospede.getNome() + "): ");
            hospede.setNome(sc.nextLine());

            System.out.print("Nova data de nascimento (atual: " + hospede.getDataNascimento() + "): ");
            hospede.setDataNascimento(sc.nextLine());

            System.out.print("Novo endereço (atual: " + hospede.getEndereco() + "): ");
            hospede.setEndereco(sc.nextLine());

            System.out.print("Novo contato (atual: " + hospede.getContato() + "): ");
            hospede.setContato(sc.nextLine());

            System.out.println("Hóspede editado com sucesso!");
        }, () -> System.out.println("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    public void excluir() {
        System.out.print("CPF do hóspede a ser excluído: ");
        String cpf = sc.nextLine();

        buscarHospedePorCpf(cpf).ifPresentOrElse(hospede -> {
            hospedes.remove(hospede);
            System.out.println("Hóspede excluído com sucesso!");
        }, () -> System.out.println("Hóspede não encontrado."));
    }


    @Override
    public void listar() {
        if (hospedes.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado.");
        } else {
            System.out.println("Lista de hóspedes:");
            for (Hospede hospede : hospedes) {
                System.out.println(hospede);
            }
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
