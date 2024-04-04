package com.kodnest.rhythmicrealm.dto;

public class AddSongRequest {
	String songTitle;
	String artist;
	String genre;
	int releaseYear;
	String songLink;
	public AddSongRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddSongRequest(String songTitle, String artist, String genre, int releaseYear, String songLink) {
		super();
		this.songTitle = songTitle;
		this.artist = artist;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.songLink = songLink;
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
	@Override
	public String toString() {
		return "AddSongRequest [songTitle=" + songTitle + ", artist=" + artist + ", genre=" + genre + ", releaseYear="
				+ releaseYear + ", songLink=" + songLink + "]";
	}
	

}
