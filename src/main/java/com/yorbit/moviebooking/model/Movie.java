package com.yorbit.moviebooking.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie implements Serializable, Comparable<Movie>{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movie_id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="releaseDate")
	private LocalDate releaseDate;
	
	@Column(name="showCycle")
	private String showCycle;
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "screen_id")
	private Screen screen;

	public Movie() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getShowCycle() {
		return showCycle;
	}

	public void setShowCycle(String showCycle) {
		this.showCycle = showCycle;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((screen == null) ? 0 : screen.hashCode());
		result = prime * result + ((showCycle == null) ? 0 : showCycle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (screen == null) {
			if (other.screen != null)
				return false;
		} else if (!screen.equals(other.screen))
			return false;
		if (showCycle == null) {
			if (other.showCycle != null)
				return false;
		} else if (!showCycle.equals(other.showCycle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", showCycle=" + showCycle
				+ ", screen=" + screen + "]";
	}

	@Override
	public int compareTo(Movie movie) {
		if(id==movie.id) {
			return 0;
		}
		else if(id>movie.id) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
