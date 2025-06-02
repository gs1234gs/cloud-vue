package com.tianchang.otherpojo.picture.columnar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Series {
    private String name;
    private String type;
    private List<Integer> data;
}
