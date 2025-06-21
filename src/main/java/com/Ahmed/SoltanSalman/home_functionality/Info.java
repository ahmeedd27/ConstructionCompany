package com.Ahmed.SoltanSalman.home_functionality;

import com.Ahmed.SoltanSalman.comman_helpers.Description;
import com.Ahmed.SoltanSalman.comman_helpers.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private Title title;
    private Description desc;
}
