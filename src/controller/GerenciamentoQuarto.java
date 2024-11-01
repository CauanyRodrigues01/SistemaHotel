package controller;

import model.Quarto;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class GerenciamentoQuarto implements Gerenciamento {
    private ArrayList<Quarto> quartos;

    public GerenciamentoQuarto() {
        this.quartos = new ArrayList<>();
    }

    @Override
    public void adicionar() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Número do quarto: ");
        int numQuarto = scanner.nextInt();
        scanner.nextLine(); // Consuming newline
        
        System.out.print("Tipo do quarto (solteiro, casal, suíte): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Capacidade do quarto: ");
        int capacidade = scanner.nextInt();
        
        System.out.print("Preço da diária: ");
        double precoDiaria = scanner.nextDouble();
        scanner.nextLine(); // Consuming newline
        
        System.out.print("Status do quarto (disponível/indisponível): ");
        String status = scanner.nextLine();

        Quarto novoQuarto = new Quarto(numQuarto, tipo, capacidade, precoDiaria, status);
        quartos.add(novoQuarto);
        
        System.out.println("Quarto adicionado com sucesso!");
    }

    @Override
    public void editar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número do quarto que deseja editar: ");
        int numQuarto = scanner.nextInt();
        scanner.nextLine(); // Consuming newline

        Quarto quarto = buscarQuarto(numQuarto);
        
        if (quarto != null) {
            System.out.print("Novo tipo de quarto: ");
            quarto.setTipo(scanner.nextLine());
            
            System.out.print("Nova capacidade: ");
            quarto.setCapacidade(scanner.nextInt());
            
            System.out.print("Novo preço da diária: ");
            quarto.setPrecoDiaria(scanner.nextDouble());
            scanner.nextLine(); // Consuming newline
            
            System.out.print("Novo status (disponível/indisponível): ");
            quarto.setStatus(scanner.nextLine());
            
            System.out.println("Quarto atualizado com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    @Override
    public void excluir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número do quarto que deseja excluir: ");
        int numQuarto = scanner.nextInt();

        Quarto quarto = buscarQuarto(numQuarto);
        
        if (quarto != null) {
            quartos.remove(quarto);
            System.out.println("Quarto excluído com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    @Override
    public void listar() {
        if (quartos.isEmpty()) {
            System.out.println("Não há quartos cadastrados.");
        } else {
            for (Quarto quarto : quartos) {
                System.out.println(quarto);
            }
        }
    }

    public void reservarQuarto(int numQuarto) {
        Quarto quarto = buscarQuarto(numQuarto);
        
        if (quarto != null && quarto.getStatus().equalsIgnoreCase("disponível")) {
            quarto.setStatus("indisponível");
            System.out.println("Quarto " + numQuarto + " reservado com sucesso!");
        } else if (quarto != null) {
            System.out.println("Quarto não disponível para reserva.");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    public void liberarQuarto(int numQuarto) {
        Quarto quarto = buscarQuarto(numQuarto);
        
        if (quarto != null && quarto.getStatus().equalsIgnoreCase("indisponível")) {
            quarto.setStatus("disponível");
            System.out.println("Quarto " + numQuarto + " liberado com sucesso!");
        } else if (quarto != null) {
            System.out.println("Quarto já está disponível.");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    public void listarQuartosDisponiveis() {
        boolean encontrado = false;
        
        for (Quarto quarto : quartos) {
            if (quarto.getStatus().equalsIgnoreCase("disponível")) {
                System.out.println(quarto);
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            System.out.println("Não há quartos disponíveis no momento.");
        }
    }

   private Quarto buscarQuarto(int numQuarto) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumQuarto() == numQuarto) {
             return quarto;
        }
    }
    return null;
 }
    
    
    
 
    // Getters, setters e outros métodos necessários
}