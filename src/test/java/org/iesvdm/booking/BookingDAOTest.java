package org.iesvdm.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingDAOTest {

    private BookingDAO bookingDAO;
    private Map<String, BookingRequest> bookings;

    @BeforeEach
    public void setup() {
        bookings = new HashMap<>();
        bookingDAO = new BookingDAO(bookings);
    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas (bookings) con la que
     * construyes el objeto BookingDAO bajo testeo.
     * Comprueba que cuando invocas bookingDAO.getAllBookingRequest
     * obtienes las 2 peticiones.
     */
    @Test
    void  getAllBookingRequestsTest() {
    BookingRequest bookingRequest1 = new BookingRequest("1", LocalDate.of(2024, 6, 10),
            LocalDate.of(2024, 6, 16), 4, false);
     BookingRequest bookingRequest2 = new BookingRequest("2", LocalDate.of(2024, 8, 3),
             LocalDate.of(2024, 9, 9), 3, true);
        bookingDAO.save(bookingRequest1);
        bookingDAO.save(bookingRequest2);
        Collection<BookingRequest> bookingRequests = bookingDAO.getAllBookingRequests();
        assertEquals(2, bookingRequests.size());

    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * Comprueba que cuando invocas bookingDAO.getAllUUIDs
     * obtienes las UUIDs de las 2 peticiones guardadas.
     */
    @Test
    void getAllUUIDsTest() {
        BookingRequest bookingRequest1 = new BookingRequest("1", LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 16), 4, false);
        BookingRequest bookingRequest2 = new BookingRequest("2", LocalDate.of(2024, 8, 3),
                LocalDate.of(2024, 9, 9), 3, true);
        bookingDAO.save(bookingRequest1);
        bookingDAO.save(bookingRequest2);
        Set<String> uuids = bookingDAO.getAllUUIDs();
        assertEquals(2, uuids.size());
    }


    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * Comprueba que cuando invocas bookingDAO.get con el UUID
     * obtienes las respectivas 2 peticiones guardadas.
     */
    @Test
    void getTest() {
        BookingRequest bookingRequest1 = new BookingRequest("1", LocalDate.of(2024, 6, 10),
                LocalDate.of(2024, 6, 16), 4, false);
        BookingRequest bookingRequest2 = new BookingRequest("2", LocalDate.of(2024, 8, 3),
                LocalDate.of(2024, 9, 9), 3, true);

        bookingDAO.save(bookingRequest1);
        bookingDAO.save(bookingRequest2);
        assertEquals(bookingRequest1, bookingDAO.get("1"));
        assertEquals(bookingRequest2, bookingDAO.get("2"));
    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * A continuación, borra la primera y comprueba
     * que se mantiene 1 reserva, la segunda guardada.
     */
    @Test
    void deleteTest() {

    }

    /**
     * Guarda 2 veces la misma petición de reserva (BookingRequest)
     * y demuestra que en la colección de bookings están repetidas
     * pero con UUID diferente
     *
     */
    @Test
    void saveTwiceSameBookingRequestTest() {

    }

}
