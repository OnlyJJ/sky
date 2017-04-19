package com.huaiwang.starsky.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//种植地对象
@Table(name = "tb_farm")
public class Farm extends BasePojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;// 主键id
	private Integer planetId;// 所属星球id
	private Integer notReNum;// 种植地上还没收获的作物数量
	private Integer grow_num;// 种植地上正在种植的作物数量
	private Integer level;// 种植地等级,与星球等级对应(1:农地2:月球3:火星)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanetId() {
		return planetId;
	}
	public void setPlanetId(Integer planetId) {
		this.planetId = planetId;
	}
	public Integer getNotReNum() {
		return notReNum;
	}
	public void setNotReNum(Integer notReNum) {
		this.notReNum = notReNum;
	}
	public Integer getGrow_num() {
		return grow_num;
	}
	public void setGrow_num(Integer grow_num) {
		this.grow_num = grow_num;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
