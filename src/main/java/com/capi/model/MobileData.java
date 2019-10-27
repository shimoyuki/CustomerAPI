package com.capi.model;

import org.springframework.stereotype.Component;

@Component 
public class MobileData {
    private Integer id;

    private String mobilePrefix;

    private String mobileNumber;

    private String province;

    private String city;

    private String mobileType;

    private String areaCode;

    private String postCode;

    private String isp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobilePrefix() {
        return mobilePrefix;
    }

    public void setMobilePrefix(String mobilePrefix) {
        this.mobilePrefix = mobilePrefix == null ? null : mobilePrefix.trim();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType == null ? null : mobileType.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }

	@Override
	public String toString() {
		return "MobileData [id=" + id + ", mobilePrefix=" + mobilePrefix + ", mobileNumber=" + mobileNumber
				+ ", province=" + province + ", city=" + city + ", mobileType=" + mobileType + ", areaCode=" + areaCode
				+ ", postCode=" + postCode + ", isp=" + isp + "]";
	}
    
}