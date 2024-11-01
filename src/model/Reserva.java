package model;

import java.time.LocalDate;
import java.util.List;


public class Reserva {
	private int numeroHospede;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private String tipoQuarto;
	private Hospede hospede;
	private Integer idReserva;
	private String status;
	
	public Reserva(int numeroHospede, LocalDate dataEntrada, LocalDate dataSaida, String tipoQuarto, Hospede hospede, Integer idReserva) {
		super();
		this.numeroHospede = numeroHospede;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.tipoQuarto = tipoQuarto;
		this.hospede = hospede;
		this.idReserva = idReserva;
		this.status = "disponivel";
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


	public Integer getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
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



	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	@Override
	public String toString() {
		return "Reserva [numeroHospede=" + numeroHospede + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", tipoQuarto=" + tipoQuarto + ", hospede=" + hospede + ", idReserva=" + idReserva + ", status="
				+ status + "]";
	}

	public Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	}




	

	
	

}
