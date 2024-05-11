package com.example.parcauto2.dao;

import com.example.parcauto2.Entity.TechnicalVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TechnicalVisitRepository extends CrudRepository<TechnicalVisit, Long> {
}
