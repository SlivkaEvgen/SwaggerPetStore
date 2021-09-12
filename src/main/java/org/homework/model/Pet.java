package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet implements Serializable,Model<Long> {

  private static final long serialVersionUID = 5888882349777L;
  private Long id;
  private Category Category;
  private String name;
  private List<String> photoUrls;
  private List<Tag> tags;
  private Status status;
}
