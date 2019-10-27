package com.capi.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component 
public class Statistics {
    private Integer id;

    private Integer successCount;

    private Integer failCount;

    private String adsId;

    private Date transDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String getAdsId() {
        return adsId;
    }

    public void setAdsId(String adsId) {
        this.adsId = adsId == null ? null : adsId.trim();
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Statistics(Integer successCount, Integer failCount, String adsId, Date transDate) {
		super();
		this.successCount = successCount;
		this.failCount = failCount;
		this.adsId = adsId;
		this.transDate = transDate;
	}
    
}