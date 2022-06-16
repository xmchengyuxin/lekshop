package com.chengyu.core.domain.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatNotice {
    private String title;
    private String content;
    private String img;
    private Integer type;
}
