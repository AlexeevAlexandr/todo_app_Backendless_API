
package com.sample.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoEntity {
  private String objectId;
  private String description;
  private String owner;
  private boolean completed;
  private String deadlineDate;

  @Override
  public String toString() {
    return "{" +
            "\"objectId\" : \"" + objectId + "\", " +
            "\"description\" : \"" + description + "\", " +
            "\"owner\" : \"" + owner + "\"," +
            "\"completed\" : \"" + completed + "\"," +
            "\"deadlineDate\" : \"" + deadlineDate + "\"" +
            "}";
  }
}
                           