package model;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class CheckOut {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public CheckOut(Hospede hospede, Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

//    public double calculaValorEstadia() {
//        long diasEstadia = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
//        return diasEstadia * quarto.getPrecoDiaria();
//    }

//    public void realizarCheckOut() {
//        double valorTotal = calculaValorEstadia();
//        quarto.atualizarStatus("disponível");
//        System.out.println("Check-out realizado com sucesso para o hóspede " + hospede.getNome());
//        System.out.println("Valor total da estadia: " + valorTotal);
//    }

    @Override
    public String toString() {
        return "CheckOut [hospede=" + hospede + ", quarto=" + quarto + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + "]";
    }
}