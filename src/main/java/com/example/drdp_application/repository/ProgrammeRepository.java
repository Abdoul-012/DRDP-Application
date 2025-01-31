package com.example.drdp_application.repository;

import com.example.drdp_application.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme,Integer> {
   public  List<Programme> findAllById(Integer id);
}
