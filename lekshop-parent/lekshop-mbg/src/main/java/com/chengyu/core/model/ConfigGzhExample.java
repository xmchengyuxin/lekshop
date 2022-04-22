package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigGzhExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigGzhExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGzhNameIsNull() {
            addCriterion("gzh_name is null");
            return (Criteria) this;
        }

        public Criteria andGzhNameIsNotNull() {
            addCriterion("gzh_name is not null");
            return (Criteria) this;
        }

        public Criteria andGzhNameEqualTo(String value) {
            addCriterion("gzh_name =", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotEqualTo(String value) {
            addCriterion("gzh_name <>", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameGreaterThan(String value) {
            addCriterion("gzh_name >", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_name >=", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLessThan(String value) {
            addCriterion("gzh_name <", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLessThanOrEqualTo(String value) {
            addCriterion("gzh_name <=", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameLike(String value) {
            addCriterion("gzh_name like", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotLike(String value) {
            addCriterion("gzh_name not like", value, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameIn(List<String> values) {
            addCriterion("gzh_name in", values, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotIn(List<String> values) {
            addCriterion("gzh_name not in", values, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameBetween(String value1, String value2) {
            addCriterion("gzh_name between", value1, value2, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhNameNotBetween(String value1, String value2) {
            addCriterion("gzh_name not between", value1, value2, "gzhName");
            return (Criteria) this;
        }

        public Criteria andGzhImgIsNull() {
            addCriterion("gzh_img is null");
            return (Criteria) this;
        }

        public Criteria andGzhImgIsNotNull() {
            addCriterion("gzh_img is not null");
            return (Criteria) this;
        }

        public Criteria andGzhImgEqualTo(String value) {
            addCriterion("gzh_img =", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgNotEqualTo(String value) {
            addCriterion("gzh_img <>", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgGreaterThan(String value) {
            addCriterion("gzh_img >", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_img >=", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgLessThan(String value) {
            addCriterion("gzh_img <", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgLessThanOrEqualTo(String value) {
            addCriterion("gzh_img <=", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgLike(String value) {
            addCriterion("gzh_img like", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgNotLike(String value) {
            addCriterion("gzh_img not like", value, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgIn(List<String> values) {
            addCriterion("gzh_img in", values, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgNotIn(List<String> values) {
            addCriterion("gzh_img not in", values, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgBetween(String value1, String value2) {
            addCriterion("gzh_img between", value1, value2, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andGzhImgNotBetween(String value1, String value2) {
            addCriterion("gzh_img not between", value1, value2, "gzhImg");
            return (Criteria) this;
        }

        public Criteria andSellerStatusIsNull() {
            addCriterion("seller_status is null");
            return (Criteria) this;
        }

        public Criteria andSellerStatusIsNotNull() {
            addCriterion("seller_status is not null");
            return (Criteria) this;
        }

        public Criteria andSellerStatusEqualTo(Integer value) {
            addCriterion("seller_status =", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusNotEqualTo(Integer value) {
            addCriterion("seller_status <>", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusGreaterThan(Integer value) {
            addCriterion("seller_status >", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_status >=", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusLessThan(Integer value) {
            addCriterion("seller_status <", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("seller_status <=", value, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusIn(List<Integer> values) {
            addCriterion("seller_status in", values, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusNotIn(List<Integer> values) {
            addCriterion("seller_status not in", values, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusBetween(Integer value1, Integer value2) {
            addCriterion("seller_status between", value1, value2, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andSellerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_status not between", value1, value2, "sellerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusIsNull() {
            addCriterion("buyer_status is null");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusIsNotNull() {
            addCriterion("buyer_status is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusEqualTo(Integer value) {
            addCriterion("buyer_status =", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusNotEqualTo(Integer value) {
            addCriterion("buyer_status <>", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusGreaterThan(Integer value) {
            addCriterion("buyer_status >", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("buyer_status >=", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusLessThan(Integer value) {
            addCriterion("buyer_status <", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusLessThanOrEqualTo(Integer value) {
            addCriterion("buyer_status <=", value, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusIn(List<Integer> values) {
            addCriterion("buyer_status in", values, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusNotIn(List<Integer> values) {
            addCriterion("buyer_status not in", values, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusBetween(Integer value1, Integer value2) {
            addCriterion("buyer_status between", value1, value2, "buyerStatus");
            return (Criteria) this;
        }

        public Criteria andBuyerStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("buyer_status not between", value1, value2, "buyerStatus");
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

        public Criteria andAddByIsNull() {
            addCriterion("add_by is null");
            return (Criteria) this;
        }

        public Criteria andAddByIsNotNull() {
            addCriterion("add_by is not null");
            return (Criteria) this;
        }

        public Criteria andAddByEqualTo(String value) {
            addCriterion("add_by =", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotEqualTo(String value) {
            addCriterion("add_by <>", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThan(String value) {
            addCriterion("add_by >", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByGreaterThanOrEqualTo(String value) {
            addCriterion("add_by >=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThan(String value) {
            addCriterion("add_by <", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLessThanOrEqualTo(String value) {
            addCriterion("add_by <=", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByLike(String value) {
            addCriterion("add_by like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotLike(String value) {
            addCriterion("add_by not like", value, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByIn(List<String> values) {
            addCriterion("add_by in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotIn(List<String> values) {
            addCriterion("add_by not in", values, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByBetween(String value1, String value2) {
            addCriterion("add_by between", value1, value2, "addBy");
            return (Criteria) this;
        }

        public Criteria andAddByNotBetween(String value1, String value2) {
            addCriterion("add_by not between", value1, value2, "addBy");
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

        public Criteria andUpdByIsNull() {
            addCriterion("upd_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdByIsNotNull() {
            addCriterion("upd_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdByEqualTo(String value) {
            addCriterion("upd_by =", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotEqualTo(String value) {
            addCriterion("upd_by <>", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByGreaterThan(String value) {
            addCriterion("upd_by >", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByGreaterThanOrEqualTo(String value) {
            addCriterion("upd_by >=", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLessThan(String value) {
            addCriterion("upd_by <", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLessThanOrEqualTo(String value) {
            addCriterion("upd_by <=", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByLike(String value) {
            addCriterion("upd_by like", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotLike(String value) {
            addCriterion("upd_by not like", value, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByIn(List<String> values) {
            addCriterion("upd_by in", values, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotIn(List<String> values) {
            addCriterion("upd_by not in", values, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByBetween(String value1, String value2) {
            addCriterion("upd_by between", value1, value2, "updBy");
            return (Criteria) this;
        }

        public Criteria andUpdByNotBetween(String value1, String value2) {
            addCriterion("upd_by not between", value1, value2, "updBy");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusIsNull() {
            addCriterion("sms_notice_status is null");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusIsNotNull() {
            addCriterion("sms_notice_status is not null");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusEqualTo(Integer value) {
            addCriterion("sms_notice_status =", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusNotEqualTo(Integer value) {
            addCriterion("sms_notice_status <>", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusGreaterThan(Integer value) {
            addCriterion("sms_notice_status >", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_notice_status >=", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusLessThan(Integer value) {
            addCriterion("sms_notice_status <", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sms_notice_status <=", value, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusIn(List<Integer> values) {
            addCriterion("sms_notice_status in", values, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusNotIn(List<Integer> values) {
            addCriterion("sms_notice_status not in", values, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusBetween(Integer value1, Integer value2) {
            addCriterion("sms_notice_status between", value1, value2, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNoticeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_notice_status not between", value1, value2, "smsNoticeStatus");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskIsNull() {
            addCriterion("sms_new_task is null");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskIsNotNull() {
            addCriterion("sms_new_task is not null");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskEqualTo(Integer value) {
            addCriterion("sms_new_task =", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskNotEqualTo(Integer value) {
            addCriterion("sms_new_task <>", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskGreaterThan(Integer value) {
            addCriterion("sms_new_task >", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_new_task >=", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskLessThan(Integer value) {
            addCriterion("sms_new_task <", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskLessThanOrEqualTo(Integer value) {
            addCriterion("sms_new_task <=", value, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskIn(List<Integer> values) {
            addCriterion("sms_new_task in", values, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskNotIn(List<Integer> values) {
            addCriterion("sms_new_task not in", values, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskBetween(Integer value1, Integer value2) {
            addCriterion("sms_new_task between", value1, value2, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNewTaskNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_new_task not between", value1, value2, "smsNewTask");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentIsNull() {
            addCriterion("sms_need_comment is null");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentIsNotNull() {
            addCriterion("sms_need_comment is not null");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentEqualTo(Integer value) {
            addCriterion("sms_need_comment =", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentNotEqualTo(Integer value) {
            addCriterion("sms_need_comment <>", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentGreaterThan(Integer value) {
            addCriterion("sms_need_comment >", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_need_comment >=", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentLessThan(Integer value) {
            addCriterion("sms_need_comment <", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentLessThanOrEqualTo(Integer value) {
            addCriterion("sms_need_comment <=", value, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentIn(List<Integer> values) {
            addCriterion("sms_need_comment in", values, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentNotIn(List<Integer> values) {
            addCriterion("sms_need_comment not in", values, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentBetween(Integer value1, Integer value2) {
            addCriterion("sms_need_comment between", value1, value2, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsNeedCommentNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_need_comment not between", value1, value2, "smsNeedComment");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectIsNull() {
            addCriterion("sms_comment_reject is null");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectIsNotNull() {
            addCriterion("sms_comment_reject is not null");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectEqualTo(Integer value) {
            addCriterion("sms_comment_reject =", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectNotEqualTo(Integer value) {
            addCriterion("sms_comment_reject <>", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectGreaterThan(Integer value) {
            addCriterion("sms_comment_reject >", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_comment_reject >=", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectLessThan(Integer value) {
            addCriterion("sms_comment_reject <", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectLessThanOrEqualTo(Integer value) {
            addCriterion("sms_comment_reject <=", value, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectIn(List<Integer> values) {
            addCriterion("sms_comment_reject in", values, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectNotIn(List<Integer> values) {
            addCriterion("sms_comment_reject not in", values, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectBetween(Integer value1, Integer value2) {
            addCriterion("sms_comment_reject between", value1, value2, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsCommentRejectNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_comment_reject not between", value1, value2, "smsCommentReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitIsNull() {
            addCriterion("sms_order_will_quit is null");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitIsNotNull() {
            addCriterion("sms_order_will_quit is not null");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitEqualTo(Integer value) {
            addCriterion("sms_order_will_quit =", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitNotEqualTo(Integer value) {
            addCriterion("sms_order_will_quit <>", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitGreaterThan(Integer value) {
            addCriterion("sms_order_will_quit >", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_order_will_quit >=", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitLessThan(Integer value) {
            addCriterion("sms_order_will_quit <", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitLessThanOrEqualTo(Integer value) {
            addCriterion("sms_order_will_quit <=", value, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitIn(List<Integer> values) {
            addCriterion("sms_order_will_quit in", values, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitNotIn(List<Integer> values) {
            addCriterion("sms_order_will_quit not in", values, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitBetween(Integer value1, Integer value2) {
            addCriterion("sms_order_will_quit between", value1, value2, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsOrderWillQuitNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_order_will_quit not between", value1, value2, "smsOrderWillQuit");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountIsNull() {
            addCriterion("sms_close_account is null");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountIsNotNull() {
            addCriterion("sms_close_account is not null");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountEqualTo(Integer value) {
            addCriterion("sms_close_account =", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountNotEqualTo(Integer value) {
            addCriterion("sms_close_account <>", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountGreaterThan(Integer value) {
            addCriterion("sms_close_account >", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_close_account >=", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountLessThan(Integer value) {
            addCriterion("sms_close_account <", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountLessThanOrEqualTo(Integer value) {
            addCriterion("sms_close_account <=", value, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountIn(List<Integer> values) {
            addCriterion("sms_close_account in", values, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountNotIn(List<Integer> values) {
            addCriterion("sms_close_account not in", values, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountBetween(Integer value1, Integer value2) {
            addCriterion("sms_close_account between", value1, value2, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsCloseAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_close_account not between", value1, value2, "smsCloseAccount");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusIsNull() {
            addCriterion("sms_withdraw_sus is null");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusIsNotNull() {
            addCriterion("sms_withdraw_sus is not null");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusEqualTo(Integer value) {
            addCriterion("sms_withdraw_sus =", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusNotEqualTo(Integer value) {
            addCriterion("sms_withdraw_sus <>", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusGreaterThan(Integer value) {
            addCriterion("sms_withdraw_sus >", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_withdraw_sus >=", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusLessThan(Integer value) {
            addCriterion("sms_withdraw_sus <", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusLessThanOrEqualTo(Integer value) {
            addCriterion("sms_withdraw_sus <=", value, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusIn(List<Integer> values) {
            addCriterion("sms_withdraw_sus in", values, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusNotIn(List<Integer> values) {
            addCriterion("sms_withdraw_sus not in", values, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusBetween(Integer value1, Integer value2) {
            addCriterion("sms_withdraw_sus between", value1, value2, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawSusNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_withdraw_sus not between", value1, value2, "smsWithdrawSus");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailIsNull() {
            addCriterion("sms_withdraw_fail is null");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailIsNotNull() {
            addCriterion("sms_withdraw_fail is not null");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailEqualTo(Integer value) {
            addCriterion("sms_withdraw_fail =", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailNotEqualTo(Integer value) {
            addCriterion("sms_withdraw_fail <>", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailGreaterThan(Integer value) {
            addCriterion("sms_withdraw_fail >", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_withdraw_fail >=", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailLessThan(Integer value) {
            addCriterion("sms_withdraw_fail <", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailLessThanOrEqualTo(Integer value) {
            addCriterion("sms_withdraw_fail <=", value, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailIn(List<Integer> values) {
            addCriterion("sms_withdraw_fail in", values, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailNotIn(List<Integer> values) {
            addCriterion("sms_withdraw_fail not in", values, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailBetween(Integer value1, Integer value2) {
            addCriterion("sms_withdraw_fail between", value1, value2, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsWithdrawFailNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_withdraw_fail not between", value1, value2, "smsWithdrawFail");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectIsNull() {
            addCriterion("sms_order_reject is null");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectIsNotNull() {
            addCriterion("sms_order_reject is not null");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectEqualTo(Integer value) {
            addCriterion("sms_order_reject =", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectNotEqualTo(Integer value) {
            addCriterion("sms_order_reject <>", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectGreaterThan(Integer value) {
            addCriterion("sms_order_reject >", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_order_reject >=", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectLessThan(Integer value) {
            addCriterion("sms_order_reject <", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectLessThanOrEqualTo(Integer value) {
            addCriterion("sms_order_reject <=", value, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectIn(List<Integer> values) {
            addCriterion("sms_order_reject in", values, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectNotIn(List<Integer> values) {
            addCriterion("sms_order_reject not in", values, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectBetween(Integer value1, Integer value2) {
            addCriterion("sms_order_reject between", value1, value2, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsOrderRejectNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_order_reject not between", value1, value2, "smsOrderReject");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderIsNull() {
            addCriterion("sms_confirm_order is null");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderIsNotNull() {
            addCriterion("sms_confirm_order is not null");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderEqualTo(Integer value) {
            addCriterion("sms_confirm_order =", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderNotEqualTo(Integer value) {
            addCriterion("sms_confirm_order <>", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderGreaterThan(Integer value) {
            addCriterion("sms_confirm_order >", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_confirm_order >=", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderLessThan(Integer value) {
            addCriterion("sms_confirm_order <", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderLessThanOrEqualTo(Integer value) {
            addCriterion("sms_confirm_order <=", value, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderIn(List<Integer> values) {
            addCriterion("sms_confirm_order in", values, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderNotIn(List<Integer> values) {
            addCriterion("sms_confirm_order not in", values, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderBetween(Integer value1, Integer value2) {
            addCriterion("sms_confirm_order between", value1, value2, "smsConfirmOrder");
            return (Criteria) this;
        }

        public Criteria andSmsConfirmOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_confirm_order not between", value1, value2, "smsConfirmOrder");
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