package com.chengyu.core.domain.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueResult {

    private final String name;
    private final Integer value;

    public KeyValueResult(String name, Integer value){
        this.name = name;
        this.value = value;
    }
}
