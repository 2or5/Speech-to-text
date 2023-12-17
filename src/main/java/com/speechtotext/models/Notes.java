package com.speechtotext.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notes")
public class Notes {
    @Id
    private String noteId;
    private String name;
    private String text;
    private Date date;
}
