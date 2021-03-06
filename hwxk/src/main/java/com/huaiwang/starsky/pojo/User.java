package com.huaiwang.starsky.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 玩家类
 * @author Administrator
 *
 */
@Table(name = "tb_user")
public class User extends BasePojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//用户Id
	private String phoneNum;//用户电话号码（帐号）
	private String password;//登录密码
	private String tradePw;//交易密码
	private User puser;//上级帐号（师傅的帐号）
	private Integer score;//积分
	private Integer state;//状态（0：未激活，1：已激活，2：已冻结）
	private Integer isSend;//是否已发放实物皮具（0：未发放，1：已发放）
	private Integer isChangeP;//是否已经更改过上级（师傅），（0：未更改过，1：已更改过），师傅只能更改一次
	private User auser;//激活者帐号
	private String ip;//登录ip
	private String nickname;//玩家昵称
	private Integer level;//玩家等级（1：在地球，2：在月球3：在火星，所有帐号的初始值为1）
	public String getNickname() {
		return nickname;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public User getPuser() {
		return puser;
	}
	public void setPuser(User puser) {
		this.puser = puser;
	}
	public User getAuser() {
		return auser;
	}
	public void setAuser(User auser) {
		this.auser = auser;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTradePw() {
		return tradePw;
	}
	public void setTradePw(String tradePw) {
		this.tradePw = tradePw;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public Integer getIsChangeP() {
		return isChangeP;
	}
	public void setIsChangeP(Integer isChangeP) {
		this.isChangeP = isChangeP;
	}	
}
