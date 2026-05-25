package com.chaibrews.blog.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  String name;

  @ManyToMany
  @JoinTable(
              name = "post_tags",
              joinColumns = @JoinColumn(name = "post_id"),
              inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Post> posts = new HashSet<>();

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Tag tag = (Tag) o;
    return Objects.equals(id, tag.id) && Objects.equals(name, tag.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
