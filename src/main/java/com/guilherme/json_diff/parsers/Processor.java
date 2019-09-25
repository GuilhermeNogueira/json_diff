package com.guilherme.json_diff.parsers;

import com.guilherme.json_diff.dto.DifferenceResultDTO;

/**
 * Each file must have their own processor implemented.
 * Processor is responsible for processing differences between two fies with same type
 */
public interface Processor {

    /**
     * Get the difference between two files
     *
     * @param file1 left side of comparison
     * @param file2 right side of comparison
     */
    DifferenceResultDTO getDifference(String file1, String file2);
}
