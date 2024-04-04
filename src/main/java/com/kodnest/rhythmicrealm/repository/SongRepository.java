package com.kodnest.rhythmicrealm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.rhythmicrealm.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
