package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

  private static final long serialVersionUID = 62345888887777L;

  private Long id,petId,quantity;
  private String shipDate,status;
  private Boolean complete;
}
