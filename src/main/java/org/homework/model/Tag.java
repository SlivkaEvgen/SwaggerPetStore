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
public class Tag implements Serializable,Model<Long> {

  private static final long serialVersionUID = 9382619309373L;
  private Long id;
  private String name;
}
