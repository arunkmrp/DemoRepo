package com.kodnest.rhythmicrealm.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String playlistName;

	private String description;

	@ManyToMany
	List<Song> songs;

	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Playlist(Long id, String playlistName, String description, List<Song> songs) {
		super();
		this.id = id;
		this.playlistName = playlistName;
		this.description = description;
		this.songs = songs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", playlistName=" + playlistName + ", description=" + description + ", songs="
				+ songs + "]";
	}

}
