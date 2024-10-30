package model;

public class CheckIn {
    private Hospede hospede;
    private Quarto quarto;

    public CheckIn(Hospede hospede, Quarto quarto) {
        this.hospede = hospede;
        this.quarto = quarto;
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

    public boolean verificarDadosDeEntrada() {
        return hospede != null && quarto != null;
    }

//    public void realizarCheckIn() {
//        if (verificarDadosDeEntrada()) {
//            quarto.atualizarStatus("ocupado");
//            System.out.println("Check-in realizado com sucesso para o hóspede " + hospede.getNome() +
//                               " no quarto " + quarto.getNumQuarto());
//        } else {
//            System.out.println("Dados inválidos para o check-in.");
//        }
//    }

    @Override
    public String toString() {
        return "CheckIn [hospede=" + hospede + ", quarto=" + quarto + "]";
    }
}
