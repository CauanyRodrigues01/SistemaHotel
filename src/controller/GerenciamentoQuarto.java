package controller;

import model.Quarto;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class GerenciamentoQuarto implements Gerenciamento {
    private final ArrayList<Quarto> quartos;
    private final Scanner sc;

    public GerenciamentoQuarto(Scanner scanner) {
        this.quartos = new ArrayList<>();
        this.sc = scanner;
    }

    public void reservarQuarto() {
        System.out.print("Digite o número do quarto: ");
        int numQuarto = readInt();

        Optional<Quarto> optionalQuarto = buscarQuartoPorNumero(numQuarto);

        if (optionalQuarto.isPresent() && optionalQuarto.get().isDisponivel()) {
            optionalQuarto.get().setStatus("indisponível");
            System.out.println("Quarto " + numQuarto + " reservado com sucesso!");
        } else if (optionalQuarto.isPresent()) {
            System.out.println("Quarto não disponível para reserva.");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    public void liberarQuarto() {
        System.out.print("Digite o número do quarto: ");
        int numQuarto = readInt();

        Optional<Quarto> optionalQuarto = buscarQuartoPorNumero(numQuarto);

        if (optionalQuarto.isPresent() && !optionalQuarto.get().isDisponivel()) {
            optionalQuarto.get().setStatus("disponível");
            System.out.println("Quarto " + numQuarto + " liberado com sucesso!");
        } else if (optionalQuarto.isPresent()) {
            System.out.println("Quarto já está disponível.");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    public void listarQuartosDisponiveis() {
        boolean encontrado = false;

        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                System.out.println(quarto);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Não há quartos disponíveis no momento.");
        }
    }

    public Optional<Quarto> buscarQuartoPorNumero(int numQuarto) {
        return quartos.stream().filter(h -> h.getNumQuarto() == numQuarto).findFirst();
    }
    

    private int readInt() {
        int value = sc.nextInt();
        sc.nextLine(); // Consumir nova linha
        return value;
    }
    
    @Override
    public void buscar() {
        System.out.print("Digite o número do quarto para buscar: ");
        int numQuarto = sc.nextInt(); // Usando o mesmo método para ler a entrada
        
        Optional<Quarto> quartoBuscado = quartos.stream().filter(h -> h.getNumQuarto() == (numQuarto)).findFirst();
        
        System.out.println(quartoBuscado);
        
    }

    @Override
    public void adicionar() {
        System.out.print("Número do quarto: ");
        int numQuarto = readInt();

        System.out.print("Tipo do quarto (solteiro, casal, suíte): ");
        String tipo = sc.nextLine();

        System.out.print("Capacidade do quarto: ");
        int capacidade = readInt();

        System.out.print("Preço da diária: ");
        double precoDiaria = sc.nextDouble();
        sc.nextLine(); // Consumir nova linha

        System.out.print("Status do quarto (disponível/indisponível): ");
        String status = sc.nextLine();

        Quarto novoQuarto = new Quarto(numQuarto, tipo, capacidade, precoDiaria, status);
        quartos.add(novoQuarto);

        System.out.println("Quarto adicionado com sucesso!");
    }

    @Override
    public void editar() {
        System.out.print("Informe o número do quarto que deseja editar: ");
        int numQuarto = readInt();

        Optional<Quarto> optionalQuarto = buscarQuartoPorNumero(numQuarto);

        if (optionalQuarto.isPresent()) {
            Quarto quarto = optionalQuarto.get();
            System.out.print("Novo tipo de quarto: ");
            quarto.setTipo(sc.nextLine());

            System.out.print("Nova capacidade: ");
            quarto.setCapacidade(readInt());

            System.out.print("Novo preço da diária: ");
            quarto.setPrecoDiaria(sc.nextDouble());
            sc.nextLine(); // Consumir nova linha

            System.out.print("Novo status (disponível/indisponível): ");
            quarto.setStatus(sc.nextLine());

            System.out.println("Quarto atualizado com sucesso!");
        } else {
            System.out.println("Quarto não encontrado.");
        }
    }

    @Override
    public void excluir() {
        System.out.print("Informe o número do quarto que deseja excluir: ");
        int numQuarto = readInt();

        Optional<Quarto> optionalQuarto = buscarQuartoPorNumero(numQuarto);

        if (optionalQuarto.isPresent()) {
            quartos.remove(optionalQuarto.get());
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
    
    @Override
    public Map<Integer, String> getOpcoesEspecificas() {
        return Map.of(
            8, "Reservar Quarto",
            7, "Liberar Quarto",
            6, "Listar Quartos Disponíveis"
        );
    }

    @Override
    public void executarOpcaoEspecifica(int opcao, Scanner sc) {
        switch (opcao) {
            case 8 -> reservarQuarto();
            case 7 -> liberarQuarto();
            case 6 -> listarQuartosDisponiveis();
            default -> System.out.println("Opção específica inválida");
        }
    }
}
