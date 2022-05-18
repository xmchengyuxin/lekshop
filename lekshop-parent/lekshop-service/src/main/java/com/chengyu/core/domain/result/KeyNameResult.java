package com.chengyu.core.domain.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyNameResult {

    private final String name;
    private final String key;

    public KeyNameResult(String name, String key){
        this.name = name;
        this.key = key;
    }
}
