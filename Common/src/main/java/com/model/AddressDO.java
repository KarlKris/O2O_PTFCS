package com.model;

public class AddressDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.uid
     *
     * @mbg.generated
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.cid
     *
     * @mbg.generated
     */
    private Integer cid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column address.addressDetail
     *
     * @mbg.generated
     */
    private String addressdetail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.id
     *
     * @return the value of address.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.id
     *
     * @param id the value for address.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.uid
     *
     * @return the value of address.uid
     *
     * @mbg.generated
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.uid
     *
     * @param uid the value for address.uid
     *
     * @mbg.generated
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.cid
     *
     * @return the value of address.cid
     *
     * @mbg.generated
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.cid
     *
     * @param cid the value for address.cid
     *
     * @mbg.generated
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column address.addressDetail
     *
     * @return the value of address.addressDetail
     *
     * @mbg.generated
     */
    public String getAddressdetail() {
        return addressdetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column address.addressDetail
     *
     * @param addressdetail the value for address.addressDetail
     *
     * @mbg.generated
     */
    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }
}