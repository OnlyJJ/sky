package com.huaiwang.starsky.pojo;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//星球对象
@Table(name = "tb_planet")
public class Planet extends BasePojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;//主键id
	private Integer userId;//星球所属的用户id
	private Integer level;//星球等级,与用户等级对应
	private List<Farm> farmList;//星球上的种植地列表
	public List<Farm> getFarmList() {
		return farmList;
	}
	public void setFarmList(List<Farm> farmList) {
		this.farmList = farmList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	

}
