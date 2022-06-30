package de.bibel.application.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gelesen")
public class Gelesen {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(nullable = false)
  private String bibelabschnitt;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_lieblingsvers", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> lieblingsverse;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_lieblingsvers_text", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> versText;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_label", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> labels;

  @Column(nullable = false)
  private String leser;

  @Column(nullable = false)
  private String kommentar;

  @Column(name = "timestamp", nullable = false)
  private LocalDateTime timestamp;

}