package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ParfumsHomme;
@Repository
public interface ParfumsHommeRepository extends JpaRepository<ParfumsHomme, Long> {

}
