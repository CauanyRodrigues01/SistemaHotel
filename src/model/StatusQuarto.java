package model;

public enum StatusQuarto {
    DISPONIVEL,
    RESERVADO, // O cliente reservou mas ainda não está usando
	OCUPADO; // Chegou o dia da reserva e o cliente veio ao Hotel e está lá dentro ocupando. Passa a ser ocupado quando se faz o check-in
}
