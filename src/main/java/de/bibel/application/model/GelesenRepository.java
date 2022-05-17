package de.bibel.application.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GelesenRepository extends JpaRepository<Gelesen, UUID> {

  @Query(
      "SELECT gelesen FROM Gelesen gelesen"
          + " WHERE (:bibelabschnitt is null or gelesen.text like concat('%',:bibelabschnitt,'%'))"
          + " AND (:kommentarAusschnitt is null or gelesen.kommentar like concat('%',:kommentarAusschnitt,'%'))"
          + " AND (:leser is null or gelesen.leser = :leser)"
          + " AND (:label is null or :label member of gelesen.label)"
          + " AND (:lieblingsvers is null or :lieblingsvers member of gelesen.lieblingsvers)"
          + " AND (:lieblingsversText is null or :lieblingsversText member of gelesen.lieblingsversText)"
  )
  List<Gelesen> findGelesensByLabelInAndLieblingsversIn(String bibelabschnitt, String kommentarAusschnitt, String leser, String label, String lieblingsvers, String lieblingsversText);
}

