package com.Ahmed.SoltanSalman.news_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "News")
public class New {
    private String _id;
    private String slug;
    private Header header;
    @Field("aricle")
    private String article;
    private boolean isFeatured;
}
