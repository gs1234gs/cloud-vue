package com.tianchang.otherpojo.picture.columnar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class YAxis {
    private String type;
    List<String> data;
}
