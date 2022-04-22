package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsMemberSpmissionLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberSpmissionLogExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andInOutIsNull() {
            addCriterion("in_out is null");
            return (Criteria) this;
        }

        public Criteria andInOutIsNotNull() {
            addCriterion("in_out is not null");
            return (Criteria) this;
        }

        public Criteria andInOutEqualTo(Integer value) {
            addCriterion("in_out =", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotEqualTo(Integer value) {
            addCriterion("in_out <>", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutGreaterThan(Integer value) {
            addCriterion("in_out >", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutGreaterThanOrEqualTo(Integer value) {
            addCriterion("in_out >=", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutLessThan(Integer value) {
            addCriterion("in_out <", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutLessThanOrEqualTo(Integer value) {
            addCriterion("in_out <=", value, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutIn(List<Integer> values) {
            addCriterion("in_out in", values, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotIn(List<Integer> values) {
            addCriterion("in_out not in", values, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutBetween(Integer value1, Integer value2) {
            addCriterion("in_out between", value1, value2, "inOut");
            return (Criteria) this;
        }

        public Criteria andInOutNotBetween(Integer value1, Integer value2) {
            addCriterion("in_out not between", value1, value2, "inOut");
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

        public Criteria andMemberNameIsNull() {
            addCriterion("member_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("member_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("member_name =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("member_name <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("member_name >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_name >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("member_name <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("member_name <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("member_name like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("member_name not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("member_name in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("member_name not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("member_name between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("member_name not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andDevoteIdIsNull() {
            addCriterion("devote_id is null");
            return (Criteria) this;
        }

        public Criteria andDevoteIdIsNotNull() {
            addCriterion("devote_id is not null");
            return (Criteria) this;
        }

        public Criteria andDevoteIdEqualTo(Integer value) {
            addCriterion("devote_id =", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdNotEqualTo(Integer value) {
            addCriterion("devote_id <>", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdGreaterThan(Integer value) {
            addCriterion("devote_id >", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("devote_id >=", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdLessThan(Integer value) {
            addCriterion("devote_id <", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("devote_id <=", value, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdIn(List<Integer> values) {
            addCriterion("devote_id in", values, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdNotIn(List<Integer> values) {
            addCriterion("devote_id not in", values, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdBetween(Integer value1, Integer value2) {
            addCriterion("devote_id between", value1, value2, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("devote_id not between", value1, value2, "devoteId");
            return (Criteria) this;
        }

        public Criteria andDevoteNameIsNull() {
            addCriterion("devote_name is null");
            return (Criteria) this;
        }

        public Criteria andDevoteNameIsNotNull() {
            addCriterion("devote_name is not null");
            return (Criteria) this;
        }

        public Criteria andDevoteNameEqualTo(String value) {
            addCriterion("devote_name =", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameNotEqualTo(String value) {
            addCriterion("devote_name <>", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameGreaterThan(String value) {
            addCriterion("devote_name >", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameGreaterThanOrEqualTo(String value) {
            addCriterion("devote_name >=", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameLessThan(String value) {
            addCriterion("devote_name <", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameLessThanOrEqualTo(String value) {
            addCriterion("devote_name <=", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameLike(String value) {
            addCriterion("devote_name like", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameNotLike(String value) {
            addCriterion("devote_name not like", value, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameIn(List<String> values) {
            addCriterion("devote_name in", values, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameNotIn(List<String> values) {
            addCriterion("devote_name not in", values, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameBetween(String value1, String value2) {
            addCriterion("devote_name between", value1, value2, "devoteName");
            return (Criteria) this;
        }

        public Criteria andDevoteNameNotBetween(String value1, String value2) {
            addCriterion("devote_name not between", value1, value2, "devoteName");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeIsNull() {
            addCriterion("amount_before is null");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeIsNotNull() {
            addCriterion("amount_before is not null");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeEqualTo(BigDecimal value) {
            addCriterion("amount_before =", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeNotEqualTo(BigDecimal value) {
            addCriterion("amount_before <>", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeGreaterThan(BigDecimal value) {
            addCriterion("amount_before >", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_before >=", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeLessThan(BigDecimal value) {
            addCriterion("amount_before <", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_before <=", value, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeIn(List<BigDecimal> values) {
            addCriterion("amount_before in", values, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeNotIn(List<BigDecimal> values) {
            addCriterion("amount_before not in", values, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_before between", value1, value2, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountBeforeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_before not between", value1, value2, "amountBefore");
            return (Criteria) this;
        }

        public Criteria andAmountAfterIsNull() {
            addCriterion("amount_after is null");
            return (Criteria) this;
        }

        public Criteria andAmountAfterIsNotNull() {
            addCriterion("amount_after is not null");
            return (Criteria) this;
        }

        public Criteria andAmountAfterEqualTo(BigDecimal value) {
            addCriterion("amount_after =", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterNotEqualTo(BigDecimal value) {
            addCriterion("amount_after <>", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterGreaterThan(BigDecimal value) {
            addCriterion("amount_after >", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_after >=", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterLessThan(BigDecimal value) {
            addCriterion("amount_after <", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount_after <=", value, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterIn(List<BigDecimal> values) {
            addCriterion("amount_after in", values, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterNotIn(List<BigDecimal> values) {
            addCriterion("amount_after not in", values, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_after between", value1, value2, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andAmountAfterNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount_after not between", value1, value2, "amountAfter");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNull() {
            addCriterion("operate_ip is null");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNotNull() {
            addCriterion("operate_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIpEqualTo(String value) {
            addCriterion("operate_ip =", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotEqualTo(String value) {
            addCriterion("operate_ip <>", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThan(String value) {
            addCriterion("operate_ip >", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThanOrEqualTo(String value) {
            addCriterion("operate_ip >=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThan(String value) {
            addCriterion("operate_ip <", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThanOrEqualTo(String value) {
            addCriterion("operate_ip <=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLike(String value) {
            addCriterion("operate_ip like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotLike(String value) {
            addCriterion("operate_ip not like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpIn(List<String> values) {
            addCriterion("operate_ip in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotIn(List<String> values) {
            addCriterion("operate_ip not in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpBetween(String value1, String value2) {
            addCriterion("operate_ip between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotBetween(String value1, String value2) {
            addCriterion("operate_ip not between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressIsNull() {
            addCriterion("operate_ip_address is null");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressIsNotNull() {
            addCriterion("operate_ip_address is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressEqualTo(String value) {
            addCriterion("operate_ip_address =", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressNotEqualTo(String value) {
            addCriterion("operate_ip_address <>", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressGreaterThan(String value) {
            addCriterion("operate_ip_address >", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("operate_ip_address >=", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressLessThan(String value) {
            addCriterion("operate_ip_address <", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressLessThanOrEqualTo(String value) {
            addCriterion("operate_ip_address <=", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressLike(String value) {
            addCriterion("operate_ip_address like", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressNotLike(String value) {
            addCriterion("operate_ip_address not like", value, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressIn(List<String> values) {
            addCriterion("operate_ip_address in", values, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressNotIn(List<String> values) {
            addCriterion("operate_ip_address not in", values, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressBetween(String value1, String value2) {
            addCriterion("operate_ip_address between", value1, value2, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andOperateIpAddressNotBetween(String value1, String value2) {
            addCriterion("operate_ip_address not between", value1, value2, "operateIpAddress");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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