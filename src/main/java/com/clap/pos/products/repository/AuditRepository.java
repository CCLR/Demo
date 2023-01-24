package com.clap.pos.products.repository;

import com.clap.pos.products.entities.Auditory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<Auditory, Long> {
}
