package com.guilherme.json_diff.dto;


import com.google.common.collect.MapDifference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DifferenceResultDTO {

    private boolean equal;
    private boolean equalSize;
    private MapDifference<String, Object> result;
}
