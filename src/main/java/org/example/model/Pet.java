package org.example.model;

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
public class Pet implements Serializable {

  private static final long serialVersionUID = 5888882349777L;

  private float id;
  Category Category;
  private String name;
  List<Object> photoUrls;
  List<Object> tags;
  private String status;
}
