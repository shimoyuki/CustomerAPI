package com.capi.model;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
    private Integer id;

    private String name;

    private String phone;

    private String region;

    private Short gender;

    private String birthday;

    private String idCardNo;

    private BigDecimal demandAccount;

    private Short monthlyIncome;

    private Short careerType;

    private Short isHasFund;

    private Short isHasSecurity;

    private Short payrollForm;

    private Short isHadCredit;

    private Short isHadOverdue;

    private Short residenceTime;

    private Short houseProperty;

    private Short carProperty;

    private Short insurancePolicy;

    private String mediaNumber;

    private Date recordDate;

    private String transportMark;

    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public BigDecimal getDemandAccount() {
        return demandAccount;
    }

    public void setDemandAccount(BigDecimal demandAccount) {
        this.demandAccount = demandAccount;
    }

    public Short getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Short monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Short getCareerType() {
        return careerType;
    }

    public void setCareerType(Short careerType) {
        this.careerType = careerType;
    }

    public Short getIsHasFund() {
        return isHasFund;
    }

    public void setIsHasFund(Short isHasFund) {
        this.isHasFund = isHasFund;
    }

    public Short getIsHasSecurity() {
        return isHasSecurity;
    }

    public void setIsHasSecurity(Short isHasSecurity) {
        this.isHasSecurity = isHasSecurity;
    }

    public Short getPayrollForm() {
        return payrollForm;
    }

    public void setPayrollForm(Short payrollForm) {
        this.payrollForm = payrollForm;
    }

    public Short getIsHadCredit() {
        return isHadCredit;
    }

    public void setIsHadCredit(Short isHadCredit) {
        this.isHadCredit = isHadCredit;
    }

    public Short getIsHadOverdue() {
        return isHadOverdue;
    }

    public void setIsHadOverdue(Short isHadOverdue) {
        this.isHadOverdue = isHadOverdue;
    }

    public Short getResidenceTime() {
        return residenceTime;
    }

    public void setResidenceTime(Short residenceTime) {
        this.residenceTime = residenceTime;
    }

    public Short getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(Short houseProperty) {
        this.houseProperty = houseProperty;
    }

    public Short getCarProperty() {
        return carProperty;
    }

    public void setCarProperty(Short carProperty) {
        this.carProperty = carProperty;
    }

    public Short getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(Short insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }

    public String getMediaNumber() {
        return mediaNumber;
    }

    public void setMediaNumber(String mediaNumber) {
        this.mediaNumber = mediaNumber == null ? null : mediaNumber.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getTransportMark() {
        return transportMark;
    }

    public void setTransportMark(String transportMark) {
        this.transportMark = transportMark == null ? null : transportMark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}