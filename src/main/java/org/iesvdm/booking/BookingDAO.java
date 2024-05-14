package org.iesvdm.booking;

import java.util.*;

public class BookingDAO {

	private Map<String, BookingRequest> bookings = new HashMap<>();

	public BookingDAO(Map<String, BookingRequest> bookings) {
		this.bookings = bookings;
	}

	public String save(BookingRequest bookingRequest) {
		String id = UUID.randomUUID().toString();
		bookings.put(id, bookingRequest);
		return id;
	}
	
	public BookingRequest get(String id) {
		return bookings.get(id);
	}
	
	public void delete(String bookingId) {
		bookings.remove(bookingId);
	}

	public int totalBookings() {
		return bookings.size();
	}

	public Collection<BookingRequest> getAllBookingRequests() {
		return bookings.values();
	}

	public Set<String> getAllUUIDs() {
		return bookings.keySet();
	}
}
