package com.kodnest.rhythmicrealm.service;

import java.util.List;

import com.kodnest.rhythmicrealm.entity.Song;

public interface SongService {
	void addSong(Song song);

	List<Song> getAllSongs();
}
