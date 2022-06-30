package de.bibel.application.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GelesenRepository extends JpaRepository<Gelesen, UUID> {

  @Query(
      "SELECT gelesen FROM Gelesen gelesen"
          + " WHERE (:bibelabschnitt is null or gelesen.bibelabschnitt like concat('%',:bibelabschnitt,'%'))"
          + " AND (:kommentarAusschnitt is null or gelesen.kommentar like concat('%',:kommentarAusschnitt,'%'))"
          + " AND (:leser is null or gelesen.leser = :leser)"
          + " AND (:label is null or :label member of gelesen.labels)"
          + " AND (:lieblingsvers is null or :lieblingsvers member of gelesen.lieblingsverse)"
          + " AND (:lieblingsversText is null or :lieblingsversText member of gelesen.versText)"
          + " AND (:startDateTime is null or gelesen.timestamp > :startDateTime)"
          + " AND (:endDateTime is null or gelesen.timestamp < :endDateTime)"
  )
  List<Gelesen> findGelesenByLabelInAndLieblingsversIn(String bibelabschnitt,
                                                       String kommentarAusschnitt,
                                                       String leser,
                                                       String label,
                                                       String lieblingsvers,
                                                       String lieblingsversText,
                                                       LocalDateTime startDateTime,
                                                       LocalDateTime endDateTime);
}

