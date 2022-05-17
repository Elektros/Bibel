package de.bibel.application.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
  private String text;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_lieblingsvers", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> lieblingsvers;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_lieblingsvers_text", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> lieblingsversText;

  @Column
  @ElementCollection
  @CollectionTable(name = "gelesen_label", joinColumns = @JoinColumn(name = "gelesen_id"))
  private List<String> label;

  @Column(nullable = false)
  private String leser;

  @Column
  private String kommentar;
}