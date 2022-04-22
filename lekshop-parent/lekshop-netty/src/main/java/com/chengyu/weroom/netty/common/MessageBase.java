package com.chengyu.weroom.netty.common;

import com.chengyu.weroom.netty.common.Command.CommandType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LeGreen
 * @date 2021/12/25
 */
@Getter
@Setter
public class MessageBase {
    private String clientId;
    private int cmd;
    private String data;
}
