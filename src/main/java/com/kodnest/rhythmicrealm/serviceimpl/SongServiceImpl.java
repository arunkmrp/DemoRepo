package com.kodnest.rhythmicrealm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.rhythmicrealm.entity.Song;
import com.kodnest.rhythmicrealm.repository.SongRepository;
import com.kodnest.rhythmicrealm.service.SongService;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongRepository songRepository;

	@Override
	public void addSong(Song songs) {
		songRepository.save(songs);
	}

	@Override
	public List<Song> getAllSongs() {
		return songRepository.findAll();
	}

	

}
