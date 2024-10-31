package model;

public class Quarto {
    private int numQuarto;
    private String tipo;
    private int capacidade;
    private double precoDiaria;
    private String status;

    public Quarto(int numQuarto, String tipo, int capacidade, double precoDiaria, String status) {
        this.numQuarto = numQuarto;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
        this.status = status;
    }
    
    //colocar funções específicas aqui depois do construtor!

    public int getNumQuarto() {
        return numQuarto;
    }

    public void setNumQuarto(int numQuarto) {
        this.numQuarto = numQuarto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quarto [numQuarto=" + numQuarto + ", tipo=" + tipo + ", capacidade=" + capacidade + 
               ", precoDiaria=" + precoDiaria + ", status=" + status + "]";
    }
}
