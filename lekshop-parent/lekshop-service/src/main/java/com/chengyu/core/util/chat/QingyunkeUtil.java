package com.chengyu.core.util.chat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @title  青云客智能聊天机器人API：http://api.qingyunke.com/
 * @author LeGreen
 * @date   2022/6/8
 */
public class QingyunkeUtil {

    public static String getAnswer(String msg){
        try {
            String result = HttpUtil.get("http://api.qingyunke.com/api.php?key=free&appid=0&msg="+msg);
            QingyunkeResult qykResult = JSONUtil.toBean(result, QingyunkeResult.class);
            if(qykResult.getResult() == 0){
                return qykResult.getContent();
            }else{
                return "听不懂~";
            }
        }catch (Exception e){
            return "听不懂~";
        }
    }

    @Getter
    @Setter
    public static class QingyunkeResult{
        Integer result;
        String content;
    }

    public static void main(String[] args) {
        System.out.println(getAnswer("你是笨蛋"));
    }
}
