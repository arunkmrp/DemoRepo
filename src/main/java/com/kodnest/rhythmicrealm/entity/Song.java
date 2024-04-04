package com.kodnest.rhythmicrealm.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String songTitle;

	@Column(nullable = false)
	String artist;

	@Column(nullable = false)
	String genre;

	@Column(nullable = false)
	int releaseYear;

	@Column(nullable = false)
	String songLink;

	@ManyToMany
	List<Playlist> playlists;

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(Long id, String songTitle, String artist, String genre, int releaseYear, String songLink,
			List<Playlist> playlists) {
		super();
		this.id = id;
		this.songTitle = songTitle;
		this.artist = artist;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.songLink = songLink;
		this.playlists = playlists;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", songTitle=" + songTitle + ", artist=" + artist + ", genre=" + genre
				+ ", releaseYear=" + releaseYear + ", songLink=" + songLink + ", playlists=" + playlists + "]";
	}

}
