package com.guilherme.json_diff.parsers;

import com.guilherme.json_diff.dto.DifferenceResultDTO;

public interface Parser {

    DifferenceResultDTO getDifference(String file1, String file2);
}
