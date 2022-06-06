package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Makeup;

public interface MakeupRepository extends JpaRepository<Makeup, Long> {

}
