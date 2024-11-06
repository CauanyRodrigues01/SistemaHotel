package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {
    
    private LocalDate dataNascimento;
    private String endereco;
    private String contato;
    private List<Reserva> reservas; // Lista de reservas do hóspede, inicializada vazia

    public Hospede(String nome, String cpf, LocalDate dataNascimento, String endereco, String contato) {
        super(nome, cpf);
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.reservas = new ArrayList<>(); // Inicializando lista de reservas vazia no construtor
    }

    // Funções específicas para manipulação de reservas do hóspede
    public void adicionarReserva(Reserva novaReserva) {
        reservas.add(novaReserva);
    }

    public void listarHistoricoDeReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas no histórico.");
            return;
        }

        System.out.println("Histórico de Reservas:");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    // Getters e Setters
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "Hospede [nome=" + super.getNome() + ", cpf=" + super.getCpf() + 
               ", dataNascimento=" + dataNascimento + ", endereco=" + endereco + 
               ", contato=" + contato + "]";
    }
}
