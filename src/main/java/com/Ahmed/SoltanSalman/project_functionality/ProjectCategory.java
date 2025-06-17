package com.Ahmed.SoltanSalman.project_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Categories")
public class ProjectCategory {
    private String _id;
   private String ar;
   private String en;

    public ProjectCategory(String ar, String en) {
        this.ar = ar;
        this.en = en;
    }
}
