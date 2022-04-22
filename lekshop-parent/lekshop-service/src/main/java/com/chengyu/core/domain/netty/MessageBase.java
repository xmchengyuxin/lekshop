package com.chengyu.core.domain.netty;

import com.chengyu.core.domain.netty.Command.CommandType;
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

    public MessageBase(String clientId, CommandType cmd, String data){
        this.clientId = clientId;
        this.cmd = cmd.value;
        this.data = data;
    }

    MessageBase(){}
}
