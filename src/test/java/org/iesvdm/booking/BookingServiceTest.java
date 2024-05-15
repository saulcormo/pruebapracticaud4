package org.iesvdm.booking;

import static java.awt.AWTEventMulticaster.add;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingServiceTest {

    @Mock
    private PaymentService paymentService;
    @Mock
    private  RoomService roomService;
    @Spy
    private  BookingDAO bookingDAO = new BookingDAO(new HashMap<>());
    @Mock
    private  MailSender mailSender;
    @InjectMocks
    private BookingService bookingService;
    @Captor
    private ArgumentCaptor<BookingRequest> bookingRequestCaptor;
    @Captor
    private ArgumentCaptor<String> roomIdCaptor;
    @Captor
    private ArgumentCaptor<String> bookingIdCaptor;
    @Captor
    private ArgumentCaptor<Double> priceCaptor;
    @Spy
    private BookingRequest bookingRequest1 = new BookingRequest("1"
            , LocalDate.of(2024,6, 10)
            , LocalDate.of(2024, 6, 16)
            ,4
            ,false
            );

    @Spy
    private BookingRequest bookingRequest2 = new BookingRequest("2"
            , LocalDate.of(2024,8, 3)
            , LocalDate.of(2024, 9, 9)
            ,3
            ,true
    );

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);


    }

    /**
     * Crea un stub para roomService.getAvailableRoom
     * que devuelva una lista de 3 habitaciones disponibles
     * con un total de plazas libres de 10 para la invocación
     * de getAvailablePlaceCount.
     */
    @Test
    void getAvailablePlaceCountTest() {

        when(roomService.getAvailableRooms()).thenReturn(new ArrayList<>()
        {
            {
                add(new Room("101", 10));
                add(new Room("102", 10));
                add(new Room("103", 10));

            }
        });

        bookingService.getAvailablePlaceCount();
        assertThat(bookingService.getAvailablePlaceCount()).isEqualTo(30);
    }

    /**
     * Verifica que para bookingRequest1 se invocan
     * los métodos getDateFrom, getDateTo y getGuestCount
     * y que el precio en dólares (bookingService.calculatePrice) es el que corresponde para
     * el número de noches de la reserva de bookingRequest1.
     */
     @Test
    void calculatePriceTest() {
         bookingService.calculatePrice(bookingRequest1);
         verify(bookingRequest1, times(1)).getDateFrom();
         verify(bookingRequest1, times(1)).getDateTo();
         verify(bookingRequest1, times(1)).getGuestCount();
        assertThat(bookingService.calculatePrice(bookingRequest1)).isEqualTo(1200.0);


     }


    /**
     * Crea un stub para roomService.findAvailableRoomId
     * que cuando se pase la bookingRequest2 devuelva el roomId
     * 101.
     * Verfica si la bookingRequest2 es de prepago (isPrepaid)
     * , se invoca paymentService.pay pasándosele los argumentos
     * bookingRequest2 y el precio esperado. Para ello, captura el bookingRequest
     * el precio en paymentService.pay con los captors necesarios.
     *
     */
    @Test
    void makeBookingTest1() {
        when(roomService.findAvailableRoomId(bookingRequest2)).thenReturn("101");
        if(bookingRequest2.isPrepaid()) {
            bookingRequestCaptor = ArgumentCaptor.forClass(BookingRequest.class);
            priceCaptor = ArgumentCaptor.forClass(Double.class);
            verify(paymentService).pay(bookingRequestCaptor.capture(), priceCaptor.capture());
            assertThat(bookingRequestCaptor.getValue()).isEqualTo(bookingRequest2);
            assertThat(priceCaptor.getValue()).isEqualTo(bookingService.calculatePrice(bookingRequest2));
        }
    }

    /**
     * Igual que antes, crea un stub para roomService.findAvailableRoomId
     * que cuando se pase la bookingRequest2 devuelva el roomId
     * 101.
     * Ahora verifica que se llaman los métodos roomService.bookRoom
     * con el argumento (a capturar por captor) 101.
     * Verifica, también, que se invoca a mailSender.sendBookingConfirmation
     * con el bookingId esperado (es decir, el que devuelve makeBooking).
     * En este caso la verificación tiene que cumplir el orden de invocación
     * bookRoom 1º, sendBookingConfirmation 2º
     */
    @Test
    void makeBookingTest2() {

        when(roomService.findAvailableRoomId(bookingRequest2)).thenReturn("101");
        bookingService.makeBooking(bookingRequest2);
        roomIdCaptor = ArgumentCaptor.forClass(String.class);
        bookingIdCaptor = ArgumentCaptor.forClass(String.class);
        verify(roomService).bookRoom(roomIdCaptor.capture());
        verify(mailSender).sendBookingConfirmation(bookingIdCaptor.capture());
        inOrder(roomService, mailSender) ;{
            verify(roomService).bookRoom(roomIdCaptor.capture());
            verify(mailSender).sendBookingConfirmation(bookingIdCaptor.capture());
        }

    }
}
