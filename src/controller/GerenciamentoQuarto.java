package controller;

import model.Quarto;
import model.StatusQuarto;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class GerenciamentoQuarto implements Gerenciamento {
    private final ArrayList<Quarto> quartos;
    private final Scanner sc;
    private final GerenciamentoHotel gerenciamentoHotel;

    public GerenciamentoQuarto(Scanner scanner, GerenciamentoHotel gerenciamentoHotel) {
        this.quartos = new ArrayList<>();
        this.sc = scanner;
        this.gerenciamentoHotel = gerenciamentoHotel;
    }

    public boolean reservarQuarto(int numQuarto) {
        return alterarStatusQuarto(numQuarto, StatusQuarto.DISPONIVEL, StatusQuarto.RESERVADO,
                                    "Quarto reservado com sucesso!", "Erro: quarto indisponível para reserva.");
    }

    public boolean liberarQuarto(int numQuarto) {
        return alterarStatusQuarto(numQuarto, StatusQuarto.OCUPADO, StatusQuarto.DISPONIVEL,
                                    "Quarto liberado com sucesso!", "Erro: quarto não está ocupado.");
    }

    public boolean ocuparQuarto(int numQuarto) {
        return alterarStatusQuarto(numQuarto, StatusQuarto.RESERVADO, StatusQuarto.OCUPADO,
                                    "Quarto ocupado com sucesso!", "Erro: apenas quartos reservados podem ser ocupados.");
    }

    private boolean alterarStatusQuarto(int numQuarto, StatusQuarto statusAtual, StatusQuarto novoStatus, String mensagemSucesso, String mensagemErro) {
        Optional<Quarto> optionalQuarto = buscarQuartoPorNumero(numQuarto);

        return optionalQuarto.map(quarto -> {
            if (quarto.getStatus() == statusAtual) {
                quarto.setStatus(novoStatus);
                System.out.println(mensagemSucesso);
                return true;
            } else {
                System.out.println(mensagemErro);
                return false;
            }
        }).orElseGet(() -> {
            System.out.println("Quarto não encontrado.");
            return false;
        });
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
    
    // Método auxiliar para buscar quarto pelo número e retorná-lo
    protected Optional<Quarto> buscarQuartoPorNumero(int numQuarto) {
        return quartos.stream()
                      .filter(q -> q.getNumQuarto() == numQuarto)
                      .findFirst();
    }
    
    @Override
    public void buscar() {
        int numQuarto = gerenciamentoHotel.lerNumQuarto();
        sc.nextLine(); // Consumir a nova linha

        buscarQuartoPorNumero(numQuarto).ifPresentOrElse(
            quarto -> System.out.println("Quarto encontrado: " + quarto),
            () -> System.out.println("Quarto não encontrado.")
        );
    }
    
    @Override
    public void adicionar() {
        System.out.print("Tipo do quarto (solteiro, casal, suíte): ");
        String tipo = sc.nextLine();

        System.out.print("Capacidade do quarto: ");
        int capacidade = gerenciamentoHotel.lerInt();

        double precoDiaria = gerenciamentoHotel.lerPrecoDiariaValido();

        Quarto novoQuarto = new Quarto(tipo, capacidade, precoDiaria);
        quartos.add(novoQuarto);

        System.out.println("Quarto adicionado com sucesso!");
    }

    @Override
    public void editar() {
        int numQuarto = gerenciamentoHotel.lerNumQuarto();

        buscarQuartoPorNumero(numQuarto).ifPresentOrElse(quarto -> {
            System.out.print("Novo tipo do quarto: ");
            quarto.setTipo(sc.nextLine());

            System.out.print("Nova capacidade: ");
            quarto.setCapacidade(gerenciamentoHotel.lerInt());
            
            System.out.print("Nova preco ");
            quarto.setPrecoDiaria(gerenciamentoHotel.lerPrecoDiariaValido());

            System.out.println("Quarto atualizado com sucesso!");
        }, () -> System.out.println("Quarto não encontrado."));
    }

    @Override
    public void excluir() {
        System.out.print("Informe o número do quarto que deseja excluir: ");
        int numQuarto = gerenciamentoHotel.lerInt();

        buscarQuartoPorNumero(numQuarto).ifPresentOrElse(quarto -> {
            quartos.remove(quarto);
            System.out.println("Quarto excluído com sucesso!");
        }, () -> System.out.println("Quarto não encontrado."));
    }

    @Override
    public void listar() {
        if (quartos.isEmpty()) {
            System.out.println("Não há quartos cadastrados.");
        } else {
        	System.out.println("Listagem de Quartos do Hotel:");
            quartos.forEach(System.out::println);
        }
    }
    
    @Override
    public Map<Integer, String> getOpcoesEspecificas() {
        return Map.of(
        	6, "Listar Quartos Disponíveis"
        );
    }

    @Override
    public void executarOpcaoEspecifica(int opcao, Scanner sc) {
        switch (opcao) {
        	case 6 -> listarQuartosDisponiveis();
            
            default -> System.out.println("Opção específica inválida");
        }
    }
}
