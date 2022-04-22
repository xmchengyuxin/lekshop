package com.chengyu.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsMemberAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberAccountExample() {
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

        public Criteria andFreezeAmountIsNull() {
            addCriterion("freeze_amount is null");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountIsNotNull() {
            addCriterion("freeze_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountEqualTo(BigDecimal value) {
            addCriterion("freeze_amount =", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountNotEqualTo(BigDecimal value) {
            addCriterion("freeze_amount <>", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountGreaterThan(BigDecimal value) {
            addCriterion("freeze_amount >", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_amount >=", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountLessThan(BigDecimal value) {
            addCriterion("freeze_amount <", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_amount <=", value, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountIn(List<BigDecimal> values) {
            addCriterion("freeze_amount in", values, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountNotIn(List<BigDecimal> values) {
            addCriterion("freeze_amount not in", values, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_amount between", value1, value2, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andFreezeAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_amount not between", value1, value2, "freezeAmount");
            return (Criteria) this;
        }

        public Criteria andMissionIsNull() {
            addCriterion("mission is null");
            return (Criteria) this;
        }

        public Criteria andMissionIsNotNull() {
            addCriterion("mission is not null");
            return (Criteria) this;
        }

        public Criteria andMissionEqualTo(BigDecimal value) {
            addCriterion("mission =", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotEqualTo(BigDecimal value) {
            addCriterion("mission <>", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionGreaterThan(BigDecimal value) {
            addCriterion("mission >", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mission >=", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionLessThan(BigDecimal value) {
            addCriterion("mission <", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mission <=", value, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionIn(List<BigDecimal> values) {
            addCriterion("mission in", values, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotIn(List<BigDecimal> values) {
            addCriterion("mission not in", values, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mission between", value1, value2, "mission");
            return (Criteria) this;
        }

        public Criteria andMissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mission not between", value1, value2, "mission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionIsNull() {
            addCriterion("freeze_mission is null");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionIsNotNull() {
            addCriterion("freeze_mission is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionEqualTo(BigDecimal value) {
            addCriterion("freeze_mission =", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionNotEqualTo(BigDecimal value) {
            addCriterion("freeze_mission <>", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionGreaterThan(BigDecimal value) {
            addCriterion("freeze_mission >", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_mission >=", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionLessThan(BigDecimal value) {
            addCriterion("freeze_mission <", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_mission <=", value, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionIn(List<BigDecimal> values) {
            addCriterion("freeze_mission in", values, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionNotIn(List<BigDecimal> values) {
            addCriterion("freeze_mission not in", values, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_mission between", value1, value2, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andFreezeMissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_mission not between", value1, value2, "freezeMission");
            return (Criteria) this;
        }

        public Criteria andSpmissionIsNull() {
            addCriterion("spmission is null");
            return (Criteria) this;
        }

        public Criteria andSpmissionIsNotNull() {
            addCriterion("spmission is not null");
            return (Criteria) this;
        }

        public Criteria andSpmissionEqualTo(BigDecimal value) {
            addCriterion("spmission =", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionNotEqualTo(BigDecimal value) {
            addCriterion("spmission <>", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionGreaterThan(BigDecimal value) {
            addCriterion("spmission >", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spmission >=", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionLessThan(BigDecimal value) {
            addCriterion("spmission <", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spmission <=", value, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionIn(List<BigDecimal> values) {
            addCriterion("spmission in", values, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionNotIn(List<BigDecimal> values) {
            addCriterion("spmission not in", values, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spmission between", value1, value2, "spmission");
            return (Criteria) this;
        }

        public Criteria andSpmissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spmission not between", value1, value2, "spmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionIsNull() {
            addCriterion("freeze_spmission is null");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionIsNotNull() {
            addCriterion("freeze_spmission is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionEqualTo(BigDecimal value) {
            addCriterion("freeze_spmission =", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionNotEqualTo(BigDecimal value) {
            addCriterion("freeze_spmission <>", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionGreaterThan(BigDecimal value) {
            addCriterion("freeze_spmission >", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_spmission >=", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionLessThan(BigDecimal value) {
            addCriterion("freeze_spmission <", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_spmission <=", value, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionIn(List<BigDecimal> values) {
            addCriterion("freeze_spmission in", values, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionNotIn(List<BigDecimal> values) {
            addCriterion("freeze_spmission not in", values, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_spmission between", value1, value2, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andFreezeSpmissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_spmission not between", value1, value2, "freezeSpmission");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(BigDecimal value) {
            addCriterion("point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(BigDecimal value) {
            addCriterion("point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(BigDecimal value) {
            addCriterion("point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(BigDecimal value) {
            addCriterion("point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<BigDecimal> values) {
            addCriterion("point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<BigDecimal> values) {
            addCriterion("point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andFreezePointIsNull() {
            addCriterion("freeze_point is null");
            return (Criteria) this;
        }

        public Criteria andFreezePointIsNotNull() {
            addCriterion("freeze_point is not null");
            return (Criteria) this;
        }

        public Criteria andFreezePointEqualTo(BigDecimal value) {
            addCriterion("freeze_point =", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointNotEqualTo(BigDecimal value) {
            addCriterion("freeze_point <>", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointGreaterThan(BigDecimal value) {
            addCriterion("freeze_point >", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_point >=", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointLessThan(BigDecimal value) {
            addCriterion("freeze_point <", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_point <=", value, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointIn(List<BigDecimal> values) {
            addCriterion("freeze_point in", values, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointNotIn(List<BigDecimal> values) {
            addCriterion("freeze_point not in", values, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_point between", value1, value2, "freezePoint");
            return (Criteria) this;
        }

        public Criteria andFreezePointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_point not between", value1, value2, "freezePoint");
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