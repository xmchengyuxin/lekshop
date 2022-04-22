package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigMissionDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConfigMissionDetailExample() {
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

        public Criteria andMissionConfigIdIsNull() {
            addCriterion("mission_config_id is null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdIsNotNull() {
            addCriterion("mission_config_id is not null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdEqualTo(Integer value) {
            addCriterion("mission_config_id =", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotEqualTo(Integer value) {
            addCriterion("mission_config_id <>", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdGreaterThan(Integer value) {
            addCriterion("mission_config_id >", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mission_config_id >=", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdLessThan(Integer value) {
            addCriterion("mission_config_id <", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdLessThanOrEqualTo(Integer value) {
            addCriterion("mission_config_id <=", value, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdIn(List<Integer> values) {
            addCriterion("mission_config_id in", values, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotIn(List<Integer> values) {
            addCriterion("mission_config_id not in", values, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdBetween(Integer value1, Integer value2) {
            addCriterion("mission_config_id between", value1, value2, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mission_config_id not between", value1, value2, "missionConfigId");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameIsNull() {
            addCriterion("mission_config_name is null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameIsNotNull() {
            addCriterion("mission_config_name is not null");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameEqualTo(String value) {
            addCriterion("mission_config_name =", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameNotEqualTo(String value) {
            addCriterion("mission_config_name <>", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameGreaterThan(String value) {
            addCriterion("mission_config_name >", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameGreaterThanOrEqualTo(String value) {
            addCriterion("mission_config_name >=", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameLessThan(String value) {
            addCriterion("mission_config_name <", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameLessThanOrEqualTo(String value) {
            addCriterion("mission_config_name <=", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameLike(String value) {
            addCriterion("mission_config_name like", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameNotLike(String value) {
            addCriterion("mission_config_name not like", value, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameIn(List<String> values) {
            addCriterion("mission_config_name in", values, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameNotIn(List<String> values) {
            addCriterion("mission_config_name not in", values, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameBetween(String value1, String value2) {
            addCriterion("mission_config_name between", value1, value2, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andMissionConfigNameNotBetween(String value1, String value2) {
            addCriterion("mission_config_name not between", value1, value2, "missionConfigName");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneIsNull() {
            addCriterion("buyer_finish_rate_one is null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneIsNotNull() {
            addCriterion("buyer_finish_rate_one is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_one =", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneNotEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_one <>", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneGreaterThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_one >", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_one >=", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneLessThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_one <", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_one <=", value, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_one in", values, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneNotIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_one not in", values, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_one between", value1, value2, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateOneNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_one not between", value1, value2, "buyerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoIsNull() {
            addCriterion("buyer_finish_rate_two is null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoIsNotNull() {
            addCriterion("buyer_finish_rate_two is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_two =", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoNotEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_two <>", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoGreaterThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_two >", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_two >=", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoLessThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_two <", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_two <=", value, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_two in", values, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoNotIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_two not in", values, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_two between", value1, value2, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateTwoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_two not between", value1, value2, "buyerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeIsNull() {
            addCriterion("buyer_finish_rate_three is null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeIsNotNull() {
            addCriterion("buyer_finish_rate_three is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_three =", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeNotEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_three <>", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeGreaterThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_three >", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_three >=", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeLessThan(BigDecimal value) {
            addCriterion("buyer_finish_rate_three <", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyer_finish_rate_three <=", value, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_three in", values, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeNotIn(List<BigDecimal> values) {
            addCriterion("buyer_finish_rate_three not in", values, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_three between", value1, value2, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerFinishRateThreeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyer_finish_rate_three not between", value1, value2, "buyerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayIsNull() {
            addCriterion("buyer_valid_day is null");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayIsNotNull() {
            addCriterion("buyer_valid_day is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayEqualTo(Integer value) {
            addCriterion("buyer_valid_day =", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayNotEqualTo(Integer value) {
            addCriterion("buyer_valid_day <>", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayGreaterThan(Integer value) {
            addCriterion("buyer_valid_day >", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("buyer_valid_day >=", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayLessThan(Integer value) {
            addCriterion("buyer_valid_day <", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayLessThanOrEqualTo(Integer value) {
            addCriterion("buyer_valid_day <=", value, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayIn(List<Integer> values) {
            addCriterion("buyer_valid_day in", values, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayNotIn(List<Integer> values) {
            addCriterion("buyer_valid_day not in", values, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayBetween(Integer value1, Integer value2) {
            addCriterion("buyer_valid_day between", value1, value2, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andBuyerValidDayNotBetween(Integer value1, Integer value2) {
            addCriterion("buyer_valid_day not between", value1, value2, "buyerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneIsNull() {
            addCriterion("seller_finish_rate_one is null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneIsNotNull() {
            addCriterion("seller_finish_rate_one is not null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_one =", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneNotEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_one <>", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneGreaterThan(BigDecimal value) {
            addCriterion("seller_finish_rate_one >", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_one >=", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneLessThan(BigDecimal value) {
            addCriterion("seller_finish_rate_one <", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_one <=", value, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_one in", values, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneNotIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_one not in", values, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_one between", value1, value2, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateOneNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_one not between", value1, value2, "sellerFinishRateOne");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoIsNull() {
            addCriterion("seller_finish_rate_two is null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoIsNotNull() {
            addCriterion("seller_finish_rate_two is not null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_two =", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoNotEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_two <>", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoGreaterThan(BigDecimal value) {
            addCriterion("seller_finish_rate_two >", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_two >=", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoLessThan(BigDecimal value) {
            addCriterion("seller_finish_rate_two <", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_two <=", value, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_two in", values, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoNotIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_two not in", values, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_two between", value1, value2, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateTwoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_two not between", value1, value2, "sellerFinishRateTwo");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeIsNull() {
            addCriterion("seller_finish_rate_three is null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeIsNotNull() {
            addCriterion("seller_finish_rate_three is not null");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_three =", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeNotEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_three <>", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeGreaterThan(BigDecimal value) {
            addCriterion("seller_finish_rate_three >", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_three >=", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeLessThan(BigDecimal value) {
            addCriterion("seller_finish_rate_three <", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("seller_finish_rate_three <=", value, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_three in", values, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeNotIn(List<BigDecimal> values) {
            addCriterion("seller_finish_rate_three not in", values, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_three between", value1, value2, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerFinishRateThreeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("seller_finish_rate_three not between", value1, value2, "sellerFinishRateThree");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayIsNull() {
            addCriterion("seller_valid_day is null");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayIsNotNull() {
            addCriterion("seller_valid_day is not null");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayEqualTo(Integer value) {
            addCriterion("seller_valid_day =", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayNotEqualTo(Integer value) {
            addCriterion("seller_valid_day <>", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayGreaterThan(Integer value) {
            addCriterion("seller_valid_day >", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_valid_day >=", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayLessThan(Integer value) {
            addCriterion("seller_valid_day <", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayLessThanOrEqualTo(Integer value) {
            addCriterion("seller_valid_day <=", value, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayIn(List<Integer> values) {
            addCriterion("seller_valid_day in", values, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayNotIn(List<Integer> values) {
            addCriterion("seller_valid_day not in", values, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayBetween(Integer value1, Integer value2) {
            addCriterion("seller_valid_day between", value1, value2, "sellerValidDay");
            return (Criteria) this;
        }

        public Criteria andSellerValidDayNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_valid_day not between", value1, value2, "sellerValidDay");
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

        public Criteria andMissionTypeIsNull() {
            addCriterion("mission_type is null");
            return (Criteria) this;
        }

        public Criteria andMissionTypeIsNotNull() {
            addCriterion("mission_type is not null");
            return (Criteria) this;
        }

        public Criteria andMissionTypeEqualTo(Integer value) {
            addCriterion("mission_type =", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeNotEqualTo(Integer value) {
            addCriterion("mission_type <>", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeGreaterThan(Integer value) {
            addCriterion("mission_type >", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mission_type >=", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeLessThan(Integer value) {
            addCriterion("mission_type <", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("mission_type <=", value, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeIn(List<Integer> values) {
            addCriterion("mission_type in", values, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeNotIn(List<Integer> values) {
            addCriterion("mission_type not in", values, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeBetween(Integer value1, Integer value2) {
            addCriterion("mission_type between", value1, value2, "missionType");
            return (Criteria) this;
        }

        public Criteria andMissionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mission_type not between", value1, value2, "missionType");
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