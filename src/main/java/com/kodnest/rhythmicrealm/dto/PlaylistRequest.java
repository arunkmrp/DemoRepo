package com.kodnest.rhythmicrealm.dto;

import java.util.List;

import com.kodnest.rhythmicrealm.entity.Song;

public class PlaylistRequest {
	private String playlistName;
    private String description;
    private List<Song> songs;
	public PlaylistRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaylistRequest(String playlistName, String description, List<Song> songs) {
		super();
		this.playlistName = playlistName;
		this.description = description;
		this.songs = songs;
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
		return "PlaylistRequest [playlistName=" + playlistName + ", description=" + description + ", songs=" + songs
				+ "]";
	}
	
    
    

}
