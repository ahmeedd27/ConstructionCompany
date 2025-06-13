package com.Ahmed.SoltanSalman.home_helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Numbers {
    private String title;
    private List<NumbersInfo> info;
}
