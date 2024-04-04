package com.kodnest.rhythmicrealm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodnest.rhythmicrealm.dto.AddSongRequest;
import com.kodnest.rhythmicrealm.entity.Song;
import com.kodnest.rhythmicrealm.service.SongService;

@CrossOrigin("*")
@Controller
public class SongController {
	@Autowired
	SongService songService;

	@PostMapping("api/admin/addsong")
	public String addSong(@RequestBody AddSongRequest addSongRequest) {
		 Song song = new Song();
	        song.setSongTitle(addSongRequest.getSongTitle());
	        song.setArtist(addSongRequest.getArtist());
	        song.setGenre(addSongRequest.getGenre());
	        song.setReleaseYear(addSongRequest.getReleaseYear());
	        song.setSongLink(addSongRequest.getSongLink());
		songService.addSong(song);
		return "redirect:/admin/add-song";
	}

	
	 @GetMapping("/api/songs")
	    public ResponseEntity<List<Song>> getAllSongs() {
	        List<Song> songs = songService.getAllSongs();
	        return ResponseEntity.ok().body(songs);
	    }

}
