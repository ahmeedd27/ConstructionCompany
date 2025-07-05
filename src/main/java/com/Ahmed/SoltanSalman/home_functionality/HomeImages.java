package com.Ahmed.SoltanSalman.home_functionality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeImages {
    @Field("id")
    private int id;
    private String imgUrl;
}
