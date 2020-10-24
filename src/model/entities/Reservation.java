package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public Reservation() {
	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		updateDates(checkIn, checkOut);
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkIn;
	}

	public Date getCheckout() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) {			
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates must be future dates"); // lança uma exceção - precisa ser tratada no programa principal
		} else if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date"); // lança uma exceção - precisa ser tratada no programa principal
		} else {
			this.checkIn = checkIn;
			this.checkOut = checkOut;
		}
	}

	@Override
	public String toString() {
		return "Reservation: Room " 
				+ roomNumber 
				+ ", check-in: " 
				+ sdf.format(checkIn) 
				+ ", check-out: " 
				+ sdf.format(checkOut) 
				+ ", "
				+ duration()
				+ " nights.";
	}
}