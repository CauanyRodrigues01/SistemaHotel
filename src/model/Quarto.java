package model;

public class Quarto {
	
	private static int contador = 1;
    private int numQuarto;
    private String tipo;
    private int capacidade;
    private double precoDiaria;
    private StatusQuarto status;

    public Quarto(String tipo, int capacidade, double precoDiaria) {
        this.numQuarto = contador++;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
        this.status = StatusQuarto.DISPONIVEL;
    }
    
    public boolean isDisponivel() {
        return status == StatusQuarto.DISPONIVEL;
    }
    
    // Getters e Setters
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

    public StatusQuarto getStatus() {
        return status;
    }

    public void setStatus(StatusQuarto status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quarto [numQuarto=" + numQuarto + ", tipo=" + tipo + ", capacidade=" + capacidade + 
               ", precoDiaria=" + precoDiaria + ", status=" + status + "]";
    }
}
