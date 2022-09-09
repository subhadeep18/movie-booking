package com.yorbit.moviebooking.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cinema")
public class Cinema implements Serializable, Comparable<Cinema>{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private int id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "booking_time")
    private LocalTime bookingTime;
    
    @Column(name="seating_capacity")
    private Integer seatingCapacity;
    
    @Column(name = "isAvailable")
    private String isAvailable;
    
    @ManyToOne
    @JoinColumn(name="screen_id", nullable=false)
    private Screen screen;
    
    @OneToMany(mappedBy="cinema")
    @JsonIgnore
    private Set<Booking> bookings;

	public Cinema() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingTime == null) ? 0 : bookingTime.hashCode());
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + id;
		result = prime * result + ((isAvailable == null) ? 0 : isAvailable.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((screen == null) ? 0 : screen.hashCode());
		result = prime * result + ((seatingCapacity == null) ? 0 : seatingCapacity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cinema other = (Cinema) obj;
		if (bookingTime == null) {
			if (other.bookingTime != null)
				return false;
		} else if (!bookingTime.equals(other.bookingTime))
			return false;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		if (id != other.id)
			return false;
		if (isAvailable == null) {
			if (other.isAvailable != null)
				return false;
		} else if (!isAvailable.equals(other.isAvailable))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (screen == null) {
			if (other.screen != null)
				return false;
		} else if (!screen.equals(other.screen))
			return false;
		if (seatingCapacity == null) {
			if (other.seatingCapacity != null)
				return false;
		} else if (!seatingCapacity.equals(other.seatingCapacity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", bookingTime=" + bookingTime + ", seatingCapacity="
				+ seatingCapacity + ", isAvailable=" + isAvailable + ", screen=" + screen + ", bookings=" + bookings
				+ "]";
	}

	@Override
	public int compareTo(Cinema cinema) {
		if(id==cinema.id) {
			return 0;
		}
		else if(id>cinema.id) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
