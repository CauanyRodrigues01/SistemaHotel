package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import model.Hospede;

public class GerenciamentoHospede implements Gerenciamento {
	
    private final List<Hospede> hospedes;
    private final Scanner sc;
    private final GerenciamentoHotel gerenciamentoHotel;

    public GerenciamentoHospede(Scanner scanner, GerenciamentoHotel gerenciamentoHotel) {
        this.hospedes = new ArrayList<>();
        this.sc = scanner;
        this.gerenciamentoHotel = gerenciamentoHotel;
    }
    
	// Método auxiliar para buscar hóspede pelo CPF e retorná-lo
    protected Optional<Hospede> buscarHospedePorCpf(String cpfBuscar) {
        return hospedes.stream()
                       .filter(h -> h.getCpf().equals(cpfBuscar))
                       .findFirst();
    }
    
    @Override
    public void buscar() {
        String cpfBuscar = gerenciamentoHotel.lerCpf();
        
        buscarHospedePorCpf(cpfBuscar).ifPresentOrElse(
            hospede -> System.out.println("Hóspede encontrado: " + hospede),
            () -> System.out.println("Hóspede não encontrado.")
        );
    }

    @Override
    public void adicionar() {
        
        System.out.print("Nome do hóspede: ");
        String nome = sc.nextLine();
        
        String cpf = gerenciamentoHotel.lerCpf();

        LocalDate dataNascimento = gerenciamentoHotel.lerDataNascimento();

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
        String cpf = gerenciamentoHotel.lerCpf();

        buscarHospedePorCpf(cpf).ifPresentOrElse(hospede -> {
            System.out.print("Novo nome (atual: " + hospede.getNome() + "): ");
            hospede.setNome(sc.nextLine());

            System.out.println("Data de nascimento atual: " + hospede.getDataNascimento());
            
            hospede.setDataNascimento(gerenciamentoHotel.lerDataNascimento());

            System.out.print("Novo endereço (atual: " + hospede.getEndereco() + "): ");
            hospede.setEndereco(sc.nextLine());

            System.out.print("Novo contato (atual: " + hospede.getContato() + "): ");
            hospede.setContato(sc.nextLine());

            System.out.println("Hóspede editado com sucesso!");
        }, () -> System.out.println("Hóspede com CPF " + cpf + " não encontrado."));
    }

    @Override
    public void excluir() {
        String cpf = gerenciamentoHotel.lerCpf();

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
