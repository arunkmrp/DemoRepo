package com.kodnest.rhythmicrealm.service;

import java.util.List;

import com.kodnest.rhythmicrealm.dto.PlaylistRequest;
import com.kodnest.rhythmicrealm.entity.Playlist;

public interface PlaylistService {

	List<Playlist> getAllPlaylists();

	

	Playlist findPlaylistById(Long playlistId);



	Playlist createPlaylist(PlaylistRequest playlistRequest);



	void deletePlaylist(Long id);



	void updatePlaylist(Long id, PlaylistRequest playlistRequest);



	void addSongToPlaylist(Long playlistId, Long songId);



	void removeSongFromPlaylist(Long playlistId, Long songId);

}
