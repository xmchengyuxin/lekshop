package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImChatSessionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImChatSessionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIsNull() {
            addCriterion("member_nickname is null");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIsNotNull() {
            addCriterion("member_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameEqualTo(String value) {
            addCriterion("member_nickname =", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotEqualTo(String value) {
            addCriterion("member_nickname <>", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameGreaterThan(String value) {
            addCriterion("member_nickname >", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("member_nickname >=", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLessThan(String value) {
            addCriterion("member_nickname <", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLessThanOrEqualTo(String value) {
            addCriterion("member_nickname <=", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLike(String value) {
            addCriterion("member_nickname like", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotLike(String value) {
            addCriterion("member_nickname not like", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIn(List<String> values) {
            addCriterion("member_nickname in", values, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotIn(List<String> values) {
            addCriterion("member_nickname not in", values, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameBetween(String value1, String value2) {
            addCriterion("member_nickname between", value1, value2, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotBetween(String value1, String value2) {
            addCriterion("member_nickname not between", value1, value2, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgIsNull() {
            addCriterion("member_head_img is null");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgIsNotNull() {
            addCriterion("member_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgEqualTo(String value) {
            addCriterion("member_head_img =", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgNotEqualTo(String value) {
            addCriterion("member_head_img <>", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgGreaterThan(String value) {
            addCriterion("member_head_img >", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("member_head_img >=", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgLessThan(String value) {
            addCriterion("member_head_img <", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgLessThanOrEqualTo(String value) {
            addCriterion("member_head_img <=", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgLike(String value) {
            addCriterion("member_head_img like", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgNotLike(String value) {
            addCriterion("member_head_img not like", value, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgIn(List<String> values) {
            addCriterion("member_head_img in", values, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgNotIn(List<String> values) {
            addCriterion("member_head_img not in", values, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgBetween(String value1, String value2) {
            addCriterion("member_head_img between", value1, value2, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andMemberHeadImgNotBetween(String value1, String value2) {
            addCriterion("member_head_img not between", value1, value2, "memberHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetIdIsNull() {
            addCriterion("target_id is null");
            return (Criteria) this;
        }

        public Criteria andTargetIdIsNotNull() {
            addCriterion("target_id is not null");
            return (Criteria) this;
        }

        public Criteria andTargetIdEqualTo(Integer value) {
            addCriterion("target_id =", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdNotEqualTo(Integer value) {
            addCriterion("target_id <>", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdGreaterThan(Integer value) {
            addCriterion("target_id >", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_id >=", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdLessThan(Integer value) {
            addCriterion("target_id <", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdLessThanOrEqualTo(Integer value) {
            addCriterion("target_id <=", value, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdIn(List<Integer> values) {
            addCriterion("target_id in", values, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdNotIn(List<Integer> values) {
            addCriterion("target_id not in", values, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdBetween(Integer value1, Integer value2) {
            addCriterion("target_id between", value1, value2, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("target_id not between", value1, value2, "targetId");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameIsNull() {
            addCriterion("target_nickname is null");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameIsNotNull() {
            addCriterion("target_nickname is not null");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameEqualTo(String value) {
            addCriterion("target_nickname =", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameNotEqualTo(String value) {
            addCriterion("target_nickname <>", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameGreaterThan(String value) {
            addCriterion("target_nickname >", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("target_nickname >=", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameLessThan(String value) {
            addCriterion("target_nickname <", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameLessThanOrEqualTo(String value) {
            addCriterion("target_nickname <=", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameLike(String value) {
            addCriterion("target_nickname like", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameNotLike(String value) {
            addCriterion("target_nickname not like", value, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameIn(List<String> values) {
            addCriterion("target_nickname in", values, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameNotIn(List<String> values) {
            addCriterion("target_nickname not in", values, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameBetween(String value1, String value2) {
            addCriterion("target_nickname between", value1, value2, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetNicknameNotBetween(String value1, String value2) {
            addCriterion("target_nickname not between", value1, value2, "targetNickname");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgIsNull() {
            addCriterion("target_head_img is null");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgIsNotNull() {
            addCriterion("target_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgEqualTo(String value) {
            addCriterion("target_head_img =", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgNotEqualTo(String value) {
            addCriterion("target_head_img <>", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgGreaterThan(String value) {
            addCriterion("target_head_img >", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("target_head_img >=", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgLessThan(String value) {
            addCriterion("target_head_img <", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgLessThanOrEqualTo(String value) {
            addCriterion("target_head_img <=", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgLike(String value) {
            addCriterion("target_head_img like", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgNotLike(String value) {
            addCriterion("target_head_img not like", value, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgIn(List<String> values) {
            addCriterion("target_head_img in", values, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgNotIn(List<String> values) {
            addCriterion("target_head_img not in", values, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgBetween(String value1, String value2) {
            addCriterion("target_head_img between", value1, value2, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andTargetHeadImgNotBetween(String value1, String value2) {
            addCriterion("target_head_img not between", value1, value2, "targetHeadImg");
            return (Criteria) this;
        }

        public Criteria andUnReadNumIsNull() {
            addCriterion("un_read_num is null");
            return (Criteria) this;
        }

        public Criteria andUnReadNumIsNotNull() {
            addCriterion("un_read_num is not null");
            return (Criteria) this;
        }

        public Criteria andUnReadNumEqualTo(Integer value) {
            addCriterion("un_read_num =", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumNotEqualTo(Integer value) {
            addCriterion("un_read_num <>", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumGreaterThan(Integer value) {
            addCriterion("un_read_num >", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("un_read_num >=", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumLessThan(Integer value) {
            addCriterion("un_read_num <", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("un_read_num <=", value, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumIn(List<Integer> values) {
            addCriterion("un_read_num in", values, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumNotIn(List<Integer> values) {
            addCriterion("un_read_num not in", values, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumBetween(Integer value1, Integer value2) {
            addCriterion("un_read_num between", value1, value2, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andUnReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("un_read_num not between", value1, value2, "unReadNum");
            return (Criteria) this;
        }

        public Criteria andLastMsgIsNull() {
            addCriterion("last_msg is null");
            return (Criteria) this;
        }

        public Criteria andLastMsgIsNotNull() {
            addCriterion("last_msg is not null");
            return (Criteria) this;
        }

        public Criteria andLastMsgEqualTo(String value) {
            addCriterion("last_msg =", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotEqualTo(String value) {
            addCriterion("last_msg <>", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgGreaterThan(String value) {
            addCriterion("last_msg >", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgGreaterThanOrEqualTo(String value) {
            addCriterion("last_msg >=", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLessThan(String value) {
            addCriterion("last_msg <", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLessThanOrEqualTo(String value) {
            addCriterion("last_msg <=", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLike(String value) {
            addCriterion("last_msg like", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotLike(String value) {
            addCriterion("last_msg not like", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgIn(List<String> values) {
            addCriterion("last_msg in", values, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotIn(List<String> values) {
            addCriterion("last_msg not in", values, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgBetween(String value1, String value2) {
            addCriterion("last_msg between", value1, value2, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotBetween(String value1, String value2) {
            addCriterion("last_msg not between", value1, value2, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIsNull() {
            addCriterion("upd_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIsNotNull() {
            addCriterion("upd_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdTimeEqualTo(Date value) {
            addCriterion("upd_time =", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotEqualTo(Date value) {
            addCriterion("upd_time <>", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeGreaterThan(Date value) {
            addCriterion("upd_time >", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upd_time >=", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeLessThan(Date value) {
            addCriterion("upd_time <", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeLessThanOrEqualTo(Date value) {
            addCriterion("upd_time <=", value, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeIn(List<Date> values) {
            addCriterion("upd_time in", values, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotIn(List<Date> values) {
            addCriterion("upd_time not in", values, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeBetween(Date value1, Date value2) {
            addCriterion("upd_time between", value1, value2, "updTime");
            return (Criteria) this;
        }

        public Criteria andUpdTimeNotBetween(Date value1, Date value2) {
            addCriterion("upd_time not between", value1, value2, "updTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}