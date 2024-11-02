package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Hospede;

public class GerenciamentoHospede implements Gerenciamento {
	
    private List<Hospede> hospedes;

    public GerenciamentoHospede() {
        this.hospedes = new ArrayList<>();
    }

    @Override
    public void adicionar() {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();

        //TODO validar data
        System.out.print("Data de nascimento do hóspede (dd/mm/aaaa): ");
        String dataNascimento = scanner.nextLine();

        System.out.print("Endereço do hóspede: ");
        String endereco = scanner.nextLine();

        System.out.print("Contato do hóspede: ");
        String contato = scanner.nextLine();

        Hospede novoHospede = new Hospede(nome, cpf, dataNascimento, endereco, contato);
        
        hospedes.add(novoHospede);

        System.out.println("\nHóspede adicionado com sucesso!");
    }

    @Override
    public void editar() {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("CPF do hóspede a ser editado: ");
        String cpf = scanner.nextLine();

        Optional<Hospede> hospedeExistente = hospedes.stream()
                .filter(h -> h.getCpf().equals(cpf))
                .findFirst();

        if (hospedeExistente.isPresent()) {
            Hospede hospede = hospedeExistente.get();
            
            System.out.print("Novo nome (atual: " + hospede.getNome() + "): ");
            hospede.setNome(scanner.nextLine());

            System.out.print("Nova data de nascimento (atual: " + hospede.getDataNascimento() + "): ");
            hospede.setDataNascimento(scanner.nextLine());

            System.out.print("Novo endereço (atual: " + hospede.getEndereco() + "): ");
            hospede.setEndereco(scanner.nextLine());

            System.out.print("Novo contato (atual: " + hospede.getContato() + "): ");
            hospede.setContato(scanner.nextLine());

            System.out.println("Hóspede editado com sucesso!");
        } else {
            System.out.println("Hóspede não encontrado.");
        }
    }

    @Override
    public void excluir() {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("CPF do hóspede a ser excluído: ");
        String cpf = scanner.nextLine();

        boolean removed = hospedes.removeIf(h -> h.getCpf().equals(cpf));
        
        if (removed) {
            System.out.println("Hóspede excluído com sucesso!");
        } else {
            System.out.println("Hóspede não encontrado.");
        }
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

    public Optional<Hospede> buscarPorCpf(String cpf) {
        return hospedes.stream().filter(h -> h.getCpf().equals(cpf)).findFirst();
    }
}
