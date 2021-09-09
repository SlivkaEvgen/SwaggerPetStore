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
public class User implements Serializable {

  private static final long serialVersionUID = 239475831256347L;
  private Long id;
  private Integer userStatus;
  private String userName;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phone;
}
