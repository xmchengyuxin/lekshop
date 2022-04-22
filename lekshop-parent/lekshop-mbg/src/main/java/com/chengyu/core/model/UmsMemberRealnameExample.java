package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UmsMemberRealnameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsMemberRealnameExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIsNull() {
            addCriterion("link_phone is null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIsNotNull() {
            addCriterion("link_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneEqualTo(String value) {
            addCriterion("link_phone =", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotEqualTo(String value) {
            addCriterion("link_phone <>", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneGreaterThan(String value) {
            addCriterion("link_phone >", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("link_phone >=", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLessThan(String value) {
            addCriterion("link_phone <", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLessThanOrEqualTo(String value) {
            addCriterion("link_phone <=", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLike(String value) {
            addCriterion("link_phone like", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotLike(String value) {
            addCriterion("link_phone not like", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIn(List<String> values) {
            addCriterion("link_phone in", values, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotIn(List<String> values) {
            addCriterion("link_phone not in", values, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneBetween(String value1, String value2) {
            addCriterion("link_phone between", value1, value2, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotBetween(String value1, String value2) {
            addCriterion("link_phone not between", value1, value2, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceIsNull() {
            addCriterion("idcard_face is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceIsNotNull() {
            addCriterion("idcard_face is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceEqualTo(String value) {
            addCriterion("idcard_face =", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceNotEqualTo(String value) {
            addCriterion("idcard_face <>", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceGreaterThan(String value) {
            addCriterion("idcard_face >", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_face >=", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceLessThan(String value) {
            addCriterion("idcard_face <", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceLessThanOrEqualTo(String value) {
            addCriterion("idcard_face <=", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceLike(String value) {
            addCriterion("idcard_face like", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceNotLike(String value) {
            addCriterion("idcard_face not like", value, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceIn(List<String> values) {
            addCriterion("idcard_face in", values, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceNotIn(List<String> values) {
            addCriterion("idcard_face not in", values, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceBetween(String value1, String value2) {
            addCriterion("idcard_face between", value1, value2, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceNotBetween(String value1, String value2) {
            addCriterion("idcard_face not between", value1, value2, "idcardFace");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandIsNull() {
            addCriterion("idcard_face_hand is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandIsNotNull() {
            addCriterion("idcard_face_hand is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandEqualTo(String value) {
            addCriterion("idcard_face_hand =", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandNotEqualTo(String value) {
            addCriterion("idcard_face_hand <>", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandGreaterThan(String value) {
            addCriterion("idcard_face_hand >", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_face_hand >=", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandLessThan(String value) {
            addCriterion("idcard_face_hand <", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandLessThanOrEqualTo(String value) {
            addCriterion("idcard_face_hand <=", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandLike(String value) {
            addCriterion("idcard_face_hand like", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandNotLike(String value) {
            addCriterion("idcard_face_hand not like", value, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandIn(List<String> values) {
            addCriterion("idcard_face_hand in", values, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandNotIn(List<String> values) {
            addCriterion("idcard_face_hand not in", values, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandBetween(String value1, String value2) {
            addCriterion("idcard_face_hand between", value1, value2, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardFaceHandNotBetween(String value1, String value2) {
            addCriterion("idcard_face_hand not between", value1, value2, "idcardFaceHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIsNull() {
            addCriterion("idcard_back is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIsNotNull() {
            addCriterion("idcard_back is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackEqualTo(String value) {
            addCriterion("idcard_back =", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotEqualTo(String value) {
            addCriterion("idcard_back <>", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackGreaterThan(String value) {
            addCriterion("idcard_back >", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_back >=", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLessThan(String value) {
            addCriterion("idcard_back <", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLessThanOrEqualTo(String value) {
            addCriterion("idcard_back <=", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackLike(String value) {
            addCriterion("idcard_back like", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotLike(String value) {
            addCriterion("idcard_back not like", value, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackIn(List<String> values) {
            addCriterion("idcard_back in", values, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotIn(List<String> values) {
            addCriterion("idcard_back not in", values, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackBetween(String value1, String value2) {
            addCriterion("idcard_back between", value1, value2, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackNotBetween(String value1, String value2) {
            addCriterion("idcard_back not between", value1, value2, "idcardBack");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandIsNull() {
            addCriterion("idcard_back_hand is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandIsNotNull() {
            addCriterion("idcard_back_hand is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandEqualTo(String value) {
            addCriterion("idcard_back_hand =", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandNotEqualTo(String value) {
            addCriterion("idcard_back_hand <>", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandGreaterThan(String value) {
            addCriterion("idcard_back_hand >", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_back_hand >=", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandLessThan(String value) {
            addCriterion("idcard_back_hand <", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandLessThanOrEqualTo(String value) {
            addCriterion("idcard_back_hand <=", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandLike(String value) {
            addCriterion("idcard_back_hand like", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandNotLike(String value) {
            addCriterion("idcard_back_hand not like", value, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandIn(List<String> values) {
            addCriterion("idcard_back_hand in", values, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandNotIn(List<String> values) {
            addCriterion("idcard_back_hand not in", values, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandBetween(String value1, String value2) {
            addCriterion("idcard_back_hand between", value1, value2, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBackHandNotBetween(String value1, String value2) {
            addCriterion("idcard_back_hand not between", value1, value2, "idcardBackHand");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeIsNull() {
            addCriterion("idcard_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeIsNotNull() {
            addCriterion("idcard_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_begin_time =", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_begin_time <>", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("idcard_begin_time >", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_begin_time >=", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeLessThan(Date value) {
            addCriterionForJDBCDate("idcard_begin_time <", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_begin_time <=", value, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeIn(List<Date> values) {
            addCriterionForJDBCDate("idcard_begin_time in", values, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("idcard_begin_time not in", values, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("idcard_begin_time between", value1, value2, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardBeginTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("idcard_begin_time not between", value1, value2, "idcardBeginTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeIsNull() {
            addCriterion("idcard_end_time is null");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeIsNotNull() {
            addCriterion("idcard_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_end_time =", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_end_time <>", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("idcard_end_time >", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_end_time >=", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("idcard_end_time <", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("idcard_end_time <=", value, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("idcard_end_time in", values, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("idcard_end_time not in", values, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("idcard_end_time between", value1, value2, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andIdcardEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("idcard_end_time not between", value1, value2, "idcardEndTime");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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