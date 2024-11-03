package main;

import java.util.Scanner;

import controller.GerenciamentoHotel;

public class Main {
    public static void main(String[] args) {
    	
        // Inicializa o Scanner
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Seja Bem-Vindo ao Sistema do Hotel!");

            GerenciamentoHotel gerenciamentoHotel = new GerenciamentoHotel(sc);
            
            gerenciamentoHotel.exibirMenuPrincipal();
        } // O Scanner será fechado automaticamente aqui
    }
}
