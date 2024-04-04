package com.kodnest.rhythmicrealm.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.rhythmicrealm.dto.PlaylistRequest;
import com.kodnest.rhythmicrealm.entity.Playlist;
import com.kodnest.rhythmicrealm.entity.Song;
import com.kodnest.rhythmicrealm.repository.PlaylistRepository;
import com.kodnest.rhythmicrealm.repository.SongRepository;
import com.kodnest.rhythmicrealm.service.PlaylistService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlaylistImpl implements PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	SongRepository songRepository;

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	 public Playlist createPlaylist(PlaylistRequest playlistRequest) {
	        // Here you would map the PlaylistRequest to a Playlist entity and save it to the database using the repository
	        // For demonstration purposes, let's assume PlaylistRequest has fields playlistName, description, and songs
	        Playlist playlist = new Playlist();
	        playlist.setPlaylistName(playlistRequest.getPlaylistName());
	        playlist.setDescription(playlistRequest.getDescription());
	        // Map songs from playlistRequest to playlist, assuming playlist has a collection of songs
	        playlist.setSongs(playlistRequest.getSongs());
	        
	        // Save the playlist to the database
	        return playlistRepository.save(playlist);
	    }

	@Override
	public Playlist findPlaylistById(Long playlistId) {
		return playlistRepository.findById(playlistId).orElse(null);
	}

	@Override
	public void deletePlaylist(Long id) {
		playlistRepository.deleteById(id);
		
	}

	@Override
	public void updatePlaylist(Long id, PlaylistRequest playlistRequest) {
	    Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
	    if (optionalPlaylist.isPresent()) {
	        Playlist playlist = optionalPlaylist.get();
	        playlist.setPlaylistName(playlistRequest.getPlaylistName());
	        playlist.setDescription(playlistRequest.getDescription());
	        // Update other fields as needed
	        
	        playlistRepository.save(playlist);
	    } else {
	        throw new EntityNotFoundException("Playlist not found with id: " + id);
	    }
	}

	@Override
	public void addSongToPlaylist(Long playlistId, Long songId) {
		 Playlist playlist = playlistRepository.findById(playlistId)
	                .orElseThrow(() -> new RuntimeException("Playlist not found"));

	        Song song = songRepository.findById(songId)
	                .orElseThrow(() -> new RuntimeException("Song not found"));

	        if (!playlist.getSongs().contains(song)) {
	            playlist.getSongs().add(song);
	            playlistRepository.save(playlist);
	        }
		
	}

	@Override
	public void removeSongFromPlaylist(Long playlistId, Long songId) {
		 Playlist playlist = playlistRepository.findById(playlistId)
	                .orElseThrow(() -> new RuntimeException("Playlist not found"));

	        Song song = songRepository.findById(songId)
	                .orElseThrow(() -> new RuntimeException("Song not found"));

	        playlist.getSongs().remove(song);
	        playlistRepository.save(playlist);
	    }

		
	}


