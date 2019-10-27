package com.capi.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component 
public class CustomerDTO {
	private Integer id;

    private String name;

    private String phone;

    private String region;

    private String gender;

    private String birthday;

    private String idCardNo;

    private BigDecimal demandAccount;

    private String monthlyIncome;

    private String careerType;

    private String isHasFund;

    private String isHasSecurity;

    private String payrollForm;

    private String isHadCredit;

    private String isHadOverdue;

    private String residenceTime;

    private String houseProperty;

    private String carProperty;

    private String insurancePolicy;

    private String mediaNumber;
    
    private String createDate;
    
    private String recordDate;
    
    private String transportMark;
    
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
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public BigDecimal getDemandAccount() {
		return demandAccount;
	}

	public void setDemandAccount(BigDecimal demandAccount) {
		this.demandAccount = demandAccount;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getCareerType() {
		return careerType;
	}

	public void setCareerType(String careerType) {
		this.careerType = careerType;
	}

	public String getIsHasFund() {
		return isHasFund;
	}

	public void setIsHasFund(String isHasFund) {
		this.isHasFund = isHasFund;
	}

	public String getIsHasSecurity() {
		return isHasSecurity;
	}

	public void setIsHasSecurity(String isHasSecurity) {
		this.isHasSecurity = isHasSecurity;
	}

	public String getPayrollForm() {
		return payrollForm;
	}

	public void setPayrollForm(String payrollForm) {
		this.payrollForm = payrollForm;
	}

	public String getIsHadCredit() {
		return isHadCredit;
	}

	public void setIsHadCredit(String isHadCredit) {
		this.isHadCredit = isHadCredit;
	}

	public String getIsHadOverdue() {
		return isHadOverdue;
	}

	public void setIsHadOverdue(String isHadOverdue) {
		this.isHadOverdue = isHadOverdue;
	}

	public String getResidenceTime() {
		return residenceTime;
	}

	public void setResidenceTime(String residenceTime) {
		this.residenceTime = residenceTime;
	}

	public String getHouseProperty() {
		return houseProperty;
	}

	public void setHouseProperty(String houseProperty) {
		this.houseProperty = houseProperty;
	}

	public String getCarProperty() {
		return carProperty;
	}

	public void setCarProperty(String carProperty) {
		this.carProperty = carProperty;
	}

	public String getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public String getMediaNumber() {
		return mediaNumber;
	}

	public void setMediaNumber(String mediaNumber) {
		this.mediaNumber = mediaNumber;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getTransportMark() {
		return transportMark;
	}

	public void setTransportMark(String transportMark) {
		this.transportMark = transportMark;
	}

	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", phone=" + phone + ", region=" + region + ", gender="
				+ gender + ", birthday=" + birthday + ", idCardNo=" + idCardNo + ", demandAccount=" + demandAccount
				+ ", monthlyIncome=" + monthlyIncome + ", careerType=" + careerType + ", isHasFund=" + isHasFund
				+ ", isHasSecurity=" + isHasSecurity + ", payrollForm=" + payrollForm + ", isHadCredit=" + isHadCredit
				+ ", isHadOverdue=" + isHadOverdue + ", residenceTime=" + residenceTime + ", houseProperty="
				+ houseProperty + ", carProperty=" + carProperty + ", insurancePolicy=" + insurancePolicy
				+ ", mediaNumber=" + mediaNumber + ", recordDate=" + recordDate + ", transportMark=" + transportMark
				+ ", createDate=" + createDate + "]";
	}

	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public CustomerDTO(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = customer.getName();
		this.phone = customer.getPhone();
		if (customer.getRegion() != null) {
			this.region = customer.getRegion();
		}else {
			this.region = "未知";
		}
		if (customer.getGender() != null) {
			if (customer.getGender() == 1) {
				this.gender = "男";
			}else if(customer.getGender() == 0) {
				this.gender = "女";
			}else {
				this.gender = "未知";
			}
		}else {
			this.gender = "未知";
		}
		if (!"".equals(customer.getBirthday())) {
			this.birthday = customer.getBirthday();
		}else {
			this.birthday = "未知";
		}
		if (!"".equals(customer.getIdCardNo())) {
			this.idCardNo = customer.getIdCardNo();
		}else {
			this.idCardNo = "未知";
		}
		if (customer.getDemandAccount() != null) {
			this.demandAccount = customer.getDemandAccount();
		}else {
			this.demandAccount = new BigDecimal(0);
		}
		if (customer.getMonthlyIncome() != null) {
			switch (customer.getMonthlyIncome()) {
			case 0:
				this.monthlyIncome = "无";
				break;
			case 1:
				this.monthlyIncome = "5000元以下";
				break;
			case 2:
				this.monthlyIncome = "5000-10000元";
				break;
			case 3:
				this.monthlyIncome = "10000元以上";
				break;
			default:
				this.monthlyIncome = "未知";
				break;
			}
		}else {
			this.monthlyIncome = "未知";
		}
		if (customer.getCareerType() != null) {
			switch (customer.getCareerType()) {
			case 0:
				this.careerType = "无";
				break;
			case 1:
				this.careerType = "公务员";
				break;
			case 2:
				this.careerType = "公司职员";
				break;
			case 3:
				this.careerType = "企业主";
				break;
			case 4:
				this.careerType = "个体户";
				break;
			case 5:
				this.careerType = "自由职业";
				break;
			case 6:
				this.careerType = "其它";
				break;
			default:
				this.careerType = "未知";
				break;
			}
		}else {
			this.careerType = "未知";
		}
		if (customer.getIsHasFund() != null) {
			if (customer.getIsHasFund() == 1) {
				this.isHasFund = "有";
			}else if(customer.getIsHasFund() == 0) {
				this.isHasFund = "无";
			}else {
				this.isHasFund = "未知";
			}
		}else {
			this.isHasFund = "未知";
		}
		if (customer.getIsHasSecurity() != null) {
			if (customer.getIsHasSecurity() == 1) {
				this.isHasSecurity = "有";
			}else if(customer.getIsHasSecurity() == 0) {
				this.isHasSecurity = "无";
			}else {
				this.isHasSecurity = "未知";
			}
		}else {
			this.isHasSecurity = "未知";
		}
		if (customer.getPayrollForm() != null) {
			switch (customer.getPayrollForm()) {
			case 0:
				this.payrollForm = "无";
				break;
			case 1:
				this.payrollForm = "银行代发";
				break;
			case 2:
				this.payrollForm = "转账工资";
				break;
			case 3:
				this.payrollForm = "现金发放";
				break;
			default:
				this.payrollForm = "未知";
				break;
			}
		}else {
			this.payrollForm = "未知";
		}
		if (customer.getIsHadCredit() != null) {
			if (customer.getIsHadCredit() == 1) {
				this.isHadCredit = "有";
			}else if(customer.getIsHadCredit() == 0) {
				this.isHadCredit = "无";
			}else {
				this.isHadCredit = "未知";
			}
		}else {
			this.isHadCredit = "未知";
		}
		if (customer.getIsHadOverdue() != null) {
			if (customer.getIsHadOverdue() == 1) {
				this.isHadOverdue = "有";
			}else if(customer.getIsHadOverdue() == 0) {
				this.isHadOverdue = "无";
			}else {
				this.isHadOverdue = "未知";
			}
		}else {
			this.isHadOverdue = "未知";
		}
		if (customer.getResidenceTime() != null) {
			if (customer.getResidenceTime() == 1) {
				this.residenceTime = "半年以上";
			}else if(customer.getResidenceTime() == 0) {
				this.residenceTime = "半年以下";
			}else {
				this.residenceTime = "未知";
			}
		}else {
			this.residenceTime = "未知";
		}
		if (customer.getHouseProperty() != null) {
			switch (customer.getHouseProperty()) {
			case 0:
				this.houseProperty = "无房产";
				break;
			case 1:
				this.houseProperty = "有房产还贷中";
				break;
			case 2:
				this.houseProperty = "有房产无房贷";
				break;
			default:
				this.houseProperty = "未知";
				break;
			}
		}else {
			this.houseProperty = "未知";
		}
		if (customer.getCarProperty() != null) {
			switch (customer.getCarProperty()) {
			case 0:
				this.carProperty = "无车";
				break;
			case 1:
				this.carProperty = "有车还贷中";
				break;
			case 2:
				this.carProperty = "有车无车贷";
				break;
			default:
				this.carProperty = "未知";
				break;
			}
		}else {
			this.carProperty = "未知";
		}
		if (customer.getInsurancePolicy() != null) {
			switch (customer.getInsurancePolicy()) {
			case 0:
				this.insurancePolicy = "无寿险";
				break;
			case 1:
				this.insurancePolicy = "年保费2400以下";
				break;
			case 2:
				this.insurancePolicy = "年保费2400以上";
				break;
			default:
				this.insurancePolicy = "未知";
				break;
			}
		}else {
			this.insurancePolicy = "未知";
		}
		this.mediaNumber = customer.getMediaNumber();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.recordDate = sdf.format(customer.getRecordDate());
		if ("1".equals(customer.getTransportMark())) {
			this.transportMark = "已发送";
		}else if ("0".equals(customer.getTransportMark())){
			this.transportMark = "未发送";
		}else{
			this.transportMark = customer.getTransportMark();
		}
		this.createDate = sdf.format(customer.getCreateDate());
	}

}
