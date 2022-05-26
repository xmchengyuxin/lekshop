package com.chengyu.core.service.im;

import com.chengyu.core.model.ImChatLog;
import com.chengyu.core.model.ImChatSession;
import com.chengyu.core.model.UmsMember;

import java.util.List;

/**
 * @title  IM
 * @author LeGreen
 * @date   2022/5/25
 */
public interface ChatService {

    /**
     * 创建会话
     * @author LeGreen
     * @date   2022/5/25
     * @param  member
     * @param  targetMember
     * @return Integer
     */
    Long initChatSession(UmsMember member, UmsMember targetMember);

    /**
     * 会话列表
     * @author LeGreen
     * @date   2022/5/25
     * @param  memberId
     * @param  page
     * @param  pageSize
     * @return List<ImChatSession>
     */
    List<ImChatSession> getChatSessionList(Integer memberId, Integer page, Integer pageSize);

    /**
     * 删除会话
     * @author LeGreen
     * @date   2022/5/25
     * @param  member
     * @param  sessionId
     */
    void deleteChatSession(UmsMember member, Long sessionId);

    /**
     * 发消息
     * @author LeGreen
     * @date   2022/5/25
     * @param  member
     * @param  targetMemberId
     * @param  msgType
     * @param  content
     * @return ImChatLog
     */
    ImChatLog sendMsg(UmsMember member, Integer targetMemberId, String msgType, String content);

    /**
     * 获取聊天记录
     * @author LeGreen
     * @date   2022/5/25
     * @param  memberId
     * @param  targetMemberId
     * @param  page
     * @param  pageSize
     * @return List<ImChatLog>
     */
    List<ImChatLog> getChatLogList(Integer memberId, Integer targetMemberId, Integer page, Integer pageSize);

    /**
     * 将会话置为已读
     * @author LeGreen
     * @date   2022/5/26
     * @param  currentMember
     * @param  sessionId
     */
    void readBySessionId(UmsMember currentMember, Long sessionId);

    /**
     * 统计总未读数量
     * @author LeGreen
     * @date   2022/5/26
     * @param  memberId
     * @return Long
     */
    Long countUnReadNum(Integer memberId);
}
