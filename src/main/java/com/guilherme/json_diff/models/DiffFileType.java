package com.guilherme.json_diff.models;

import com.guilherme.json_diff.parsers.JsonProcessor;
import com.guilherme.json_diff.parsers.Processor;
import com.guilherme.json_diff.services.ServiceUtils;
import lombok.Getter;

import java.util.function.Function;

public enum DiffFileType {
    JSON(s -> true, ServiceUtils::decodeBase64, new JsonProcessor());

    private Function<String, Boolean> validator;
    private Function<String, String> decoder;

    @Getter
    private Processor comparator;

    DiffFileType(Function<String, Boolean> validator, Function<String, String> decoder, Processor comparator) {
        this.validator = validator;
        this.comparator = comparator;
        this.decoder = decoder;
    }

    public  boolean validate(String file){
        return this.validator.apply(file);
    }

    public String decode(String file){
        return this.decoder.apply(file);
    }
}
