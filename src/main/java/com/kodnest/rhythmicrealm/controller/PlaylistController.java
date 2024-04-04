package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.rhythmicrealm.dto.PlaylistRequest;
import com.kodnest.rhythmicrealm.dto.PlaylistResponse;
import com.kodnest.rhythmicrealm.entity.Playlist;
import com.kodnest.rhythmicrealm.entity.Song;
import com.kodnest.rhythmicrealm.service.PlaylistService;

import jakarta.persistence.EntityNotFoundException;

@CrossOrigin("*")
@RestController
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;

	
	 @PostMapping("/api/playlists")
	    public ResponseEntity<?> createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
	        try {
	            // Validate playlistRequest fields
	            if (playlistRequest.getPlaylistName() == null || playlistRequest.getPlaylistName().isEmpty()) {
	                return ResponseEntity.badRequest().body("Playlist name is required");
	            }
	            if (playlistRequest.getDescription() == null || playlistRequest.getDescription().isEmpty()) {
	                return ResponseEntity.badRequest().body("Playlist description is required");
	            }
	            if (playlistRequest.getSongs() == null || playlistRequest.getSongs().isEmpty()) {
	                return ResponseEntity.badRequest().body("At least one song must be selected for the playlist");
	            }

	          

	            // Call the service to create the playlist
	            Playlist createdPlaylist = playlistService.createPlaylist(playlistRequest);

	            // Return a response with the created playlist or any other necessary information
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlaylist);
	        } catch (Exception e) {
	            // Handle any unexpected errors
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create playlist: " + e.getMessage());
	        }
	    }


	
	@GetMapping("api/admin/view-playlists")
    public ResponseEntity<List<Playlist>> viewPlaylists() {

        List<Playlist> playlists = playlistService.getAllPlaylists();
        return ResponseEntity.ok().body(playlists);
    }

	@PostMapping("/create")
	public String createPlaylist(@ModelAttribute("playlist") Playlist playlist) {

		return "redirect:/admin/create-playlist";

	}

	
	@GetMapping("/api/playlists/{playlistId}/songs")
    public ResponseEntity<List<Song>> getSongsForPlaylist(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.findPlaylistById(playlistId);
        if (playlist != null) {
            List<Song> songs = playlist.getSongs();
            return ResponseEntity.ok().body(songs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	 @DeleteMapping("/api/playlists/{id}")
	    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
	        playlistService.deletePlaylist(id);
	        return ResponseEntity.ok().build();
	    }

	 
	 @PutMapping("/api/playlists/{id}")
	    public ResponseEntity<?> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequest playlistRequest) {
	        try {
	            playlistService.updatePlaylist(id, playlistRequest);
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating playlist");
	        }
	    }
	 
	 @GetMapping("/api/playlists/{id}")
	 public ResponseEntity<PlaylistResponse> getPlaylistDetails(@PathVariable Long id) {
	     try {
	         Playlist playlist = playlistService.findPlaylistById(id);
	         PlaylistResponse playlistResponse = new PlaylistResponse();
	         playlistResponse.setPlaylistName(playlist.getPlaylistName());
	         playlistResponse.setDescription(playlist.getDescription());
	         // Set other properties if needed
	         
	         return ResponseEntity.ok().body(playlistResponse);
	     } catch (EntityNotFoundException e) {
	         return ResponseEntity.notFound().build();
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	 }
	 
	 
	 @PostMapping("/api/playlists/{playlistId}/songs")
	 public ResponseEntity<?> addSongToPlaylist(@PathVariable Long playlistId, @RequestBody Long songId) {
	     try {
	    	 System.out.println(songId);
//	         Long songIdValue = Long.parseLong(songId);
	         playlistService.addSongToPlaylist(playlistId, songId);
	         return ResponseEntity.ok().build();
	     } catch (NumberFormatException e) {
	         // Handle case where songId is not a valid Long
	         return ResponseEntity.badRequest().body("Invalid songId format");
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add song to playlist");
	     }
	 }

	 
	 
	 @DeleteMapping("/api/playlists/{playlistId}/songs/{songId}")
	    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
	        try {
	            playlistService.removeSongFromPlaylist(playlistId, songId);
	            return ResponseEntity.ok().build();
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove song from playlist");
	        }
	    }


}
