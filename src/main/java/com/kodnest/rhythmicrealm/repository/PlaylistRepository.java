package com.kodnest.rhythmicrealm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.rhythmicrealm.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
