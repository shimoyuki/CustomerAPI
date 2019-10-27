package com.capi.model;

import org.springframework.stereotype.Component;

@Component 
public class User {
    private Integer id;

    private String email;

    private String username;

    private String password;

    private String nickname;

    private String channelId;

    private String adsId1;

    private String adsId2;

    private String destUrl;

    private String profile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getAdsId1() {
        return adsId1;
    }

    public void setAdsId1(String adsId1) {
        this.adsId1 = adsId1 == null ? null : adsId1.trim();
    }

    public String getAdsId2() {
        return adsId2;
    }

    public void setAdsId2(String adsId2) {
        this.adsId2 = adsId2 == null ? null : adsId2.trim();
    }

    public String getDestUrl() {
        return destUrl;
    }

    public void setDestUrl(String destUrl) {
        this.destUrl = destUrl == null ? null : destUrl.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", nickname=" + nickname + ", channelId=" + channelId + ", adsId1=" + adsId1 + ", adsId2=" + adsId2
				+ ", destUrl=" + destUrl + ", profile=" + profile + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
}