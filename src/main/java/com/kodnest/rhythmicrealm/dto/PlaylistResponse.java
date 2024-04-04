package com.kodnest.rhythmicrealm.dto;

public class PlaylistResponse {
	private String playlistName;
    private String description;
	public PlaylistResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaylistResponse(String playlistName, String description) {
		super();
		this.playlistName = playlistName;
		this.description = description;
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
	@Override
	public String toString() {
		return "PlaylistResponse [playlistName=" + playlistName + ", description=" + description + "]";
	}
    
    

}
