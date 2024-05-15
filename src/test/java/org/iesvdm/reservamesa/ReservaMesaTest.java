package org.iesvdm.reservamesa;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReservaMesaTest {

    @Test
    void failTest() {
        fail("TEST SIN IMPLEMENTAR");
    }

    @Test
    void Rellenarmesa() {
        //when
        int[] mesas = new int[10];
        ReservaMesa reservaMesa = new ReservaMesa();

        //do
        reservaMesa.setTamanioMesa(4);
        reservaMesa.rellenarMesas(mesas);

        //THEN
        for (int i = 0; i < mesas.length; i++) {
            assertThat(reservaMesa.getMesas().length).isEqualTo(10);
        }
    }

    @Test
    void imprimir() {

        //when
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(0);
        reserva.setMesas(new int[10]);

        //do
        reserva.imprimir();

        //THEN
    }

    @Test
    void buscarPrimeraMesaVacia() {
        //do
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(4);
        int[] mesas = new int[10];
        reserva.setMesas(mesas);

        //WHEN
        int busca= reserva.buscarPrimeraMesaVacia();

        //THEN
        assertThat(busca).isEqualTo(0);
    }




    @Test
    void buscarMesaParaCompartir() {

        //DO
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(4);
        reserva.setMesas(new int[10]);

        //WHEN

        int busca = reserva.buscarMesaParaCompartir(3);

        //THEN
        assertThat(busca).isEqualTo(0);

    }

    @Test
    void buscarMesaCompartirMasCercaDe() {
        //when
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(4);
        int[] mesas = new int[10];
        reserva.setMesas(mesas);
        //do
        int buscar = reserva.buscarMesaCompartirMasCercaDe(0, 3);
        //THEN
        assertThat(buscar).isEqualTo(0);

    }

    @Test
    void bucarMesaCompartirMasAlejadaDe(){

        //when
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(2);
        int[] mesas = new int[12];
        reserva.setMesas(mesas);

        //do
         int buscar = reserva.buscarMesaCompartirMasAlejadaDe(2, 1);

        //Then
        assertThat(buscar).isEqualTo(-1);
    }

@Test
void buscarCompartirNMesasConsecutivas(){
        //when
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(2);
        int[] mesas = new int[12];
        reserva.setMesas(mesas);

        //do

        //Then

}

    @Test
    void comensalesTotales() {
        //when
        ReservaMesa reserva = new ReservaMesa();
        reserva.setTamanioMesa(2);
        int[] mesas = new int[4];
        reserva.setMesas(mesas);
        //do
        int comensales = reserva.comensalesTotales();
        //THEN
        assertThat(comensales).isEqualTo(0);
    }
}