package com.Ahmed.SoltanSalman.comman_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUpload {
    private String imageFile; // Base64 string
    private Title title;
}