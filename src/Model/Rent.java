package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Rent {

	private int ID;
	private User user;
	private Car car;
	private LocalDateTime dateTime;
	private int hours;
	private double total;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");
	private int status;
	
	//Status : Running -> 0
	//         Returned -> 1
	
	public Rent() {
		dateTime = LocalDateTime.now();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public LocalDateTime getLocalDateTime(){
		return dateTime;
		
	}
	
	public String getDateTime() {
		return formatter.format(dateTime);
	}

	public void setDateTime(String dateTimeString) {
		this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}
	
	public String getStatusToString() {
		long passedhours = ChronoUnit.HOURS.between(dateTime , LocalDateTime.now());
		String status = "";
		if(getStatus()!=1 && passedhours<getHours()) {
			status = "Estimated";
		}
		else if (getStatus()!=1 && passedhours>getHours()) {
			status = "Delayed";
		}
		else if (getStatus()==1) {
			status = "Returned";
		}
		return status;   
	}

	public long getDelayedHours() {
		long passedhours = ChronoUnit.HOURS.between(dateTime , LocalDateTime.now());
		long delayedHours = passedhours - hours;
		return delayedHours;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
