package com.guilherme.json_diff.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class DiffException extends RuntimeException {

    private int code;
    private String msg;

    public DiffException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }
}
