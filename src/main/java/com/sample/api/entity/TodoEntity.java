
package com.sample.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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
                           