package de.bibel.application.model;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GelesenRepository extends JpaRepository<Gelesen, UUID> {}

