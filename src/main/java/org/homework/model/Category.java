package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable,BaseEntity<Long> {

  private static final long serialVersionUID = 7835463788887777L;
  private Long id;
  private String name;
}
