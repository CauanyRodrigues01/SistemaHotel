package model;

import java.time.LocalDate;


public class Reserva {
	private int numeroHospede;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private Quarto quarto;
	private Hospede hospede;
	private Integer idReserva;
	private String status;

	
	public Reserva(int numeroHospede, LocalDate dataEntrada, LocalDate dataSaida, Quarto quarto, Hospede hospede,
			Integer idReserva) {
		super();
		this.numeroHospede = numeroHospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.quarto = quarto;
		this.hospede = hospede;
		this.idReserva = idReserva;
		this.status = "disponível";
		
	}




	public int duracaoEstadia()  {
		 int diaEntrada = dataEntrada.getDayOfYear();
	     int diaSaida = dataSaida.getDayOfYear();
	     return diaSaida - diaEntrada;
	}
	
	public double calcularValorReserva(double precoDiaria) {
        int duracaoDiasReserva = duracaoEstadia();
        return duracaoDiasReserva * precoDiaria;    
    }

	public int getNumeroHospede() {
		return numeroHospede;
	}

	public void setNumeroHospede(int numeroHospede) {
		this.numeroHospede = numeroHospede;
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

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "Reserva [numeroHospede=" + numeroHospede + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", quarto=" + quarto + ", hospede=" + hospede + ", idReserva=" + idReserva + ", status=" + status
				+ "]";
	}





	
	

}
