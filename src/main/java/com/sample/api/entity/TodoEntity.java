
package com.sample.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TodoEntity {
  private String objectId;
  @NonNull
  private String description;
  @NonNull
  private String owner;
  @NonNull
  private boolean completed;
  @NonNull
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
                           