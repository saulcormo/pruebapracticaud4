package org.iesvdm.reservamesa;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ReservaMesa reservaMesa = new ReservaMesa();

        reservaMesa.setTamanioMesa(4);

        int[] mesas = new int[10];
        reservaMesa.setMesas(mesas);


        // rellenar el array (aleatorios 0-4)
        reservaMesa.rellenarMesas(mesas);

        // pintar las mesas
        reservaMesa.imprimir();
        System.out.println("Cantidad comensales en el restaurante: " + reservaMesa.comensalesTotales());

        boolean salir = false;
        // bucle para introducir personas
        do {
            // preguntar por la cantidad de personas
            System.out.println("\n¿Cuantos son? (-1 para salir)");
            int cantidad = sc.nextInt();

            if (cantidad == -1) {
                salir = true;
            } else if (cantidad > reservaMesa.getTamanioMesa()) {
                System.out.println("No puede haber grupos mayores de " + reservaMesa.getTamanioMesa() + " personas");
            } else {
                // introducir personas en el array
                int numeroMesaVacia = reservaMesa.buscarPrimeraMesaVacia();

                if (numeroMesaVacia >= 0) {
                    mesas[numeroMesaVacia] = cantidad;
                    System.out.println("Sientense en la mesa: " + (numeroMesaVacia + 1));
                } else // no hay mesa vacia // COMPARTIR
                {
                    numeroMesaVacia = reservaMesa.buscarMesaParaCompartir(cantidad);

                    if (numeroMesaVacia >= 0) {
                        System.out.println("Tiene que compartir, sientense en la mesa " + (numeroMesaVacia + 1));
                        mesas[numeroMesaVacia] = mesas[numeroMesaVacia] + cantidad;
                    } else {
                        System.out.println("No hay hueco para ustedes, lo siento");
                    }
                }

                // pintar las mesas
                reservaMesa.imprimir();
                System.out.println("Cantidad comensales en el restaurante: " + reservaMesa.comensalesTotales());
            }

        } while (!salir);
    }

}
