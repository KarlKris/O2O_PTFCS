package com.model;

import java.util.ArrayList;
import java.util.List;

public class DealDOExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table deal
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table deal
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table deal
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public DealDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table deal
     *
     * @mbg.generated
     */
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

        public Criteria andPromoterIsNull() {
            addCriterion("promoter is null");
            return (Criteria) this;
        }

        public Criteria andPromoterIsNotNull() {
            addCriterion("promoter is not null");
            return (Criteria) this;
        }

        public Criteria andPromoterEqualTo(String value) {
            addCriterion("promoter =", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterNotEqualTo(String value) {
            addCriterion("promoter <>", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterGreaterThan(String value) {
            addCriterion("promoter >", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterGreaterThanOrEqualTo(String value) {
            addCriterion("promoter >=", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterLessThan(String value) {
            addCriterion("promoter <", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterLessThanOrEqualTo(String value) {
            addCriterion("promoter <=", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterLike(String value) {
            addCriterion("promoter like", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterNotLike(String value) {
            addCriterion("promoter not like", value, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterIn(List<String> values) {
            addCriterion("promoter in", values, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterNotIn(List<String> values) {
            addCriterion("promoter not in", values, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterBetween(String value1, String value2) {
            addCriterion("promoter between", value1, value2, "promoter");
            return (Criteria) this;
        }

        public Criteria andPromoterNotBetween(String value1, String value2) {
            addCriterion("promoter not between", value1, value2, "promoter");
            return (Criteria) this;
        }

        public Criteria andAssistIdIsNull() {
            addCriterion("assist_id is null");
            return (Criteria) this;
        }

        public Criteria andAssistIdIsNotNull() {
            addCriterion("assist_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssistIdEqualTo(String value) {
            addCriterion("assist_id =", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdNotEqualTo(String value) {
            addCriterion("assist_id <>", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdGreaterThan(String value) {
            addCriterion("assist_id >", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdGreaterThanOrEqualTo(String value) {
            addCriterion("assist_id >=", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdLessThan(String value) {
            addCriterion("assist_id <", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdLessThanOrEqualTo(String value) {
            addCriterion("assist_id <=", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdLike(String value) {
            addCriterion("assist_id like", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdNotLike(String value) {
            addCriterion("assist_id not like", value, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdIn(List<String> values) {
            addCriterion("assist_id in", values, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdNotIn(List<String> values) {
            addCriterion("assist_id not in", values, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdBetween(String value1, String value2) {
            addCriterion("assist_id between", value1, value2, "assistId");
            return (Criteria) this;
        }

        public Criteria andAssistIdNotBetween(String value1, String value2) {
            addCriterion("assist_id not between", value1, value2, "assistId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdIsNull() {
            addCriterion("recruit_id is null");
            return (Criteria) this;
        }

        public Criteria andRecruitIdIsNotNull() {
            addCriterion("recruit_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitIdEqualTo(Integer value) {
            addCriterion("recruit_id =", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdNotEqualTo(Integer value) {
            addCriterion("recruit_id <>", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdGreaterThan(Integer value) {
            addCriterion("recruit_id >", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("recruit_id >=", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdLessThan(Integer value) {
            addCriterion("recruit_id <", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdLessThanOrEqualTo(Integer value) {
            addCriterion("recruit_id <=", value, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdIn(List<Integer> values) {
            addCriterion("recruit_id in", values, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdNotIn(List<Integer> values) {
            addCriterion("recruit_id not in", values, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdBetween(Integer value1, Integer value2) {
            addCriterion("recruit_id between", value1, value2, "recruitId");
            return (Criteria) this;
        }

        public Criteria andRecruitIdNotBetween(Integer value1, Integer value2) {
            addCriterion("recruit_id not between", value1, value2, "recruitId");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNull() {
            addCriterion("star_num is null");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNotNull() {
            addCriterion("star_num is not null");
            return (Criteria) this;
        }

        public Criteria andStarNumEqualTo(Integer value) {
            addCriterion("star_num =", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotEqualTo(Integer value) {
            addCriterion("star_num <>", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThan(Integer value) {
            addCriterion("star_num >", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("star_num >=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThan(Integer value) {
            addCriterion("star_num <", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThanOrEqualTo(Integer value) {
            addCriterion("star_num <=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumIn(List<Integer> values) {
            addCriterion("star_num in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotIn(List<Integer> values) {
            addCriterion("star_num not in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumBetween(Integer value1, Integer value2) {
            addCriterion("star_num between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotBetween(Integer value1, Integer value2) {
            addCriterion("star_num not between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourIsNull() {
            addCriterion("monry_per_hour is null");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourIsNotNull() {
            addCriterion("monry_per_hour is not null");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourEqualTo(Double value) {
            addCriterion("monry_per_hour =", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourNotEqualTo(Double value) {
            addCriterion("monry_per_hour <>", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourGreaterThan(Double value) {
            addCriterion("monry_per_hour >", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourGreaterThanOrEqualTo(Double value) {
            addCriterion("monry_per_hour >=", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourLessThan(Double value) {
            addCriterion("monry_per_hour <", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourLessThanOrEqualTo(Double value) {
            addCriterion("monry_per_hour <=", value, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourIn(List<Double> values) {
            addCriterion("monry_per_hour in", values, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourNotIn(List<Double> values) {
            addCriterion("monry_per_hour not in", values, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourBetween(Double value1, Double value2) {
            addCriterion("monry_per_hour between", value1, value2, "monryPerHour");
            return (Criteria) this;
        }

        public Criteria andMonryPerHourNotBetween(Double value1, Double value2) {
            addCriterion("monry_per_hour not between", value1, value2, "monryPerHour");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table deal
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table deal
     *
     * @mbg.generated
     */
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