package com.chengyu.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysWeixinConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysWeixinConfigExample() {
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

        public Criteria andKfAppIdIsNull() {
            addCriterion("kf_app_id is null");
            return (Criteria) this;
        }

        public Criteria andKfAppIdIsNotNull() {
            addCriterion("kf_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andKfAppIdEqualTo(String value) {
            addCriterion("kf_app_id =", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdNotEqualTo(String value) {
            addCriterion("kf_app_id <>", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdGreaterThan(String value) {
            addCriterion("kf_app_id >", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("kf_app_id >=", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdLessThan(String value) {
            addCriterion("kf_app_id <", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdLessThanOrEqualTo(String value) {
            addCriterion("kf_app_id <=", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdLike(String value) {
            addCriterion("kf_app_id like", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdNotLike(String value) {
            addCriterion("kf_app_id not like", value, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdIn(List<String> values) {
            addCriterion("kf_app_id in", values, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdNotIn(List<String> values) {
            addCriterion("kf_app_id not in", values, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdBetween(String value1, String value2) {
            addCriterion("kf_app_id between", value1, value2, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfAppIdNotBetween(String value1, String value2) {
            addCriterion("kf_app_id not between", value1, value2, "kfAppId");
            return (Criteria) this;
        }

        public Criteria andKfSecretIsNull() {
            addCriterion("kf_secret is null");
            return (Criteria) this;
        }

        public Criteria andKfSecretIsNotNull() {
            addCriterion("kf_secret is not null");
            return (Criteria) this;
        }

        public Criteria andKfSecretEqualTo(String value) {
            addCriterion("kf_secret =", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretNotEqualTo(String value) {
            addCriterion("kf_secret <>", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretGreaterThan(String value) {
            addCriterion("kf_secret >", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretGreaterThanOrEqualTo(String value) {
            addCriterion("kf_secret >=", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretLessThan(String value) {
            addCriterion("kf_secret <", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretLessThanOrEqualTo(String value) {
            addCriterion("kf_secret <=", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretLike(String value) {
            addCriterion("kf_secret like", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretNotLike(String value) {
            addCriterion("kf_secret not like", value, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretIn(List<String> values) {
            addCriterion("kf_secret in", values, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretNotIn(List<String> values) {
            addCriterion("kf_secret not in", values, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretBetween(String value1, String value2) {
            addCriterion("kf_secret between", value1, value2, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andKfSecretNotBetween(String value1, String value2) {
            addCriterion("kf_secret not between", value1, value2, "kfSecret");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdIsNull() {
            addCriterion("xcx_app_id is null");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdIsNotNull() {
            addCriterion("xcx_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdEqualTo(String value) {
            addCriterion("xcx_app_id =", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdNotEqualTo(String value) {
            addCriterion("xcx_app_id <>", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdGreaterThan(String value) {
            addCriterion("xcx_app_id >", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("xcx_app_id >=", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdLessThan(String value) {
            addCriterion("xcx_app_id <", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdLessThanOrEqualTo(String value) {
            addCriterion("xcx_app_id <=", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdLike(String value) {
            addCriterion("xcx_app_id like", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdNotLike(String value) {
            addCriterion("xcx_app_id not like", value, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdIn(List<String> values) {
            addCriterion("xcx_app_id in", values, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdNotIn(List<String> values) {
            addCriterion("xcx_app_id not in", values, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdBetween(String value1, String value2) {
            addCriterion("xcx_app_id between", value1, value2, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxAppIdNotBetween(String value1, String value2) {
            addCriterion("xcx_app_id not between", value1, value2, "xcxAppId");
            return (Criteria) this;
        }

        public Criteria andXcxSecretIsNull() {
            addCriterion("xcx_secret is null");
            return (Criteria) this;
        }

        public Criteria andXcxSecretIsNotNull() {
            addCriterion("xcx_secret is not null");
            return (Criteria) this;
        }

        public Criteria andXcxSecretEqualTo(String value) {
            addCriterion("xcx_secret =", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretNotEqualTo(String value) {
            addCriterion("xcx_secret <>", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretGreaterThan(String value) {
            addCriterion("xcx_secret >", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretGreaterThanOrEqualTo(String value) {
            addCriterion("xcx_secret >=", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretLessThan(String value) {
            addCriterion("xcx_secret <", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretLessThanOrEqualTo(String value) {
            addCriterion("xcx_secret <=", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretLike(String value) {
            addCriterion("xcx_secret like", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretNotLike(String value) {
            addCriterion("xcx_secret not like", value, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretIn(List<String> values) {
            addCriterion("xcx_secret in", values, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretNotIn(List<String> values) {
            addCriterion("xcx_secret not in", values, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretBetween(String value1, String value2) {
            addCriterion("xcx_secret between", value1, value2, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andXcxSecretNotBetween(String value1, String value2) {
            addCriterion("xcx_secret not between", value1, value2, "xcxSecret");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdIsNull() {
            addCriterion("gzh_app_id is null");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdIsNotNull() {
            addCriterion("gzh_app_id is not null");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdEqualTo(String value) {
            addCriterion("gzh_app_id =", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdNotEqualTo(String value) {
            addCriterion("gzh_app_id <>", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdGreaterThan(String value) {
            addCriterion("gzh_app_id >", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_app_id >=", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdLessThan(String value) {
            addCriterion("gzh_app_id <", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdLessThanOrEqualTo(String value) {
            addCriterion("gzh_app_id <=", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdLike(String value) {
            addCriterion("gzh_app_id like", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdNotLike(String value) {
            addCriterion("gzh_app_id not like", value, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdIn(List<String> values) {
            addCriterion("gzh_app_id in", values, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdNotIn(List<String> values) {
            addCriterion("gzh_app_id not in", values, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdBetween(String value1, String value2) {
            addCriterion("gzh_app_id between", value1, value2, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhAppIdNotBetween(String value1, String value2) {
            addCriterion("gzh_app_id not between", value1, value2, "gzhAppId");
            return (Criteria) this;
        }

        public Criteria andGzhSecretIsNull() {
            addCriterion("gzh_secret is null");
            return (Criteria) this;
        }

        public Criteria andGzhSecretIsNotNull() {
            addCriterion("gzh_secret is not null");
            return (Criteria) this;
        }

        public Criteria andGzhSecretEqualTo(String value) {
            addCriterion("gzh_secret =", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretNotEqualTo(String value) {
            addCriterion("gzh_secret <>", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretGreaterThan(String value) {
            addCriterion("gzh_secret >", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretGreaterThanOrEqualTo(String value) {
            addCriterion("gzh_secret >=", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretLessThan(String value) {
            addCriterion("gzh_secret <", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretLessThanOrEqualTo(String value) {
            addCriterion("gzh_secret <=", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretLike(String value) {
            addCriterion("gzh_secret like", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretNotLike(String value) {
            addCriterion("gzh_secret not like", value, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretIn(List<String> values) {
            addCriterion("gzh_secret in", values, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretNotIn(List<String> values) {
            addCriterion("gzh_secret not in", values, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretBetween(String value1, String value2) {
            addCriterion("gzh_secret between", value1, value2, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andGzhSecretNotBetween(String value1, String value2) {
            addCriterion("gzh_secret not between", value1, value2, "gzhSecret");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("mch_id =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("mch_id >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("mch_id <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("mch_id like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("mch_id not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("mch_id in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNull() {
            addCriterion("mch_key is null");
            return (Criteria) this;
        }

        public Criteria andMchKeyIsNotNull() {
            addCriterion("mch_key is not null");
            return (Criteria) this;
        }

        public Criteria andMchKeyEqualTo(String value) {
            addCriterion("mch_key =", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotEqualTo(String value) {
            addCriterion("mch_key <>", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThan(String value) {
            addCriterion("mch_key >", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyGreaterThanOrEqualTo(String value) {
            addCriterion("mch_key >=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThan(String value) {
            addCriterion("mch_key <", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLessThanOrEqualTo(String value) {
            addCriterion("mch_key <=", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyLike(String value) {
            addCriterion("mch_key like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotLike(String value) {
            addCriterion("mch_key not like", value, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyIn(List<String> values) {
            addCriterion("mch_key in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotIn(List<String> values) {
            addCriterion("mch_key not in", values, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyBetween(String value1, String value2) {
            addCriterion("mch_key between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andMchKeyNotBetween(String value1, String value2) {
            addCriterion("mch_key not between", value1, value2, "mchKey");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIsNull() {
            addCriterion("pay_notify_url is null");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIsNotNull() {
            addCriterion("pay_notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlEqualTo(String value) {
            addCriterion("pay_notify_url =", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotEqualTo(String value) {
            addCriterion("pay_notify_url <>", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlGreaterThan(String value) {
            addCriterion("pay_notify_url >", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pay_notify_url >=", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLessThan(String value) {
            addCriterion("pay_notify_url <", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("pay_notify_url <=", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlLike(String value) {
            addCriterion("pay_notify_url like", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotLike(String value) {
            addCriterion("pay_notify_url not like", value, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlIn(List<String> values) {
            addCriterion("pay_notify_url in", values, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotIn(List<String> values) {
            addCriterion("pay_notify_url not in", values, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlBetween(String value1, String value2) {
            addCriterion("pay_notify_url between", value1, value2, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("pay_notify_url not between", value1, value2, "payNotifyUrl");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNull() {
            addCriterion("cert_path is null");
            return (Criteria) this;
        }

        public Criteria andCertPathIsNotNull() {
            addCriterion("cert_path is not null");
            return (Criteria) this;
        }

        public Criteria andCertPathEqualTo(String value) {
            addCriterion("cert_path =", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotEqualTo(String value) {
            addCriterion("cert_path <>", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThan(String value) {
            addCriterion("cert_path >", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathGreaterThanOrEqualTo(String value) {
            addCriterion("cert_path >=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThan(String value) {
            addCriterion("cert_path <", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLessThanOrEqualTo(String value) {
            addCriterion("cert_path <=", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathLike(String value) {
            addCriterion("cert_path like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotLike(String value) {
            addCriterion("cert_path not like", value, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathIn(List<String> values) {
            addCriterion("cert_path in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotIn(List<String> values) {
            addCriterion("cert_path not in", values, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathBetween(String value1, String value2) {
            addCriterion("cert_path between", value1, value2, "certPath");
            return (Criteria) this;
        }

        public Criteria andCertPathNotBetween(String value1, String value2) {
            addCriterion("cert_path not between", value1, value2, "certPath");
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