package com.Ahmed.SoltanSalman.news_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "NewsCategories")
public class NewsCategory {
    private String _id;
    private String ar;
    private String en;

    public NewsCategory(String ar, String en) {
        this.ar = ar;
        this.en = en;
    }
}
