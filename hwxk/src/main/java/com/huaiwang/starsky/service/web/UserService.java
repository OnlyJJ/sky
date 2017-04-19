package com.huaiwang.starsky.service.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huaiwang.starsky.common.util.MD5Util;
import com.huaiwang.starsky.mapper.manage.UserMapper;
import com.huaiwang.starsky.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public void saveUser(User user) {
		user.setState(0);// 设置初始状态为未激活
		user.setPassword(MD5Util.getMd5Hash(user.getPhoneNum(), user.getPassword()));// 将明文密码加密后设置回user
		user.setTradePw(MD5Util.getMd5Hash(user.getPhoneNum(), user.getTradePw()));// 将明文交易密码加密后设置回user
		user.setCreated(new Date());// 设置注册时间
		user.setIsChangeP(0);// 设置是否更改过上级（师傅）的初始状态为未更改过
		user.setIsSend(0);// 设置是否已发送实体皮具的初始状态为未发放
		user.setScore(0);// 设置初始积分为0
		user.setLevel(1);// 设置玩家初始等级，全部都为1，表示在地球
		userMapper.saveUser(user);// 保存玩家信息到玩家表中
		Integer id = user.getId();
		List<Integer> pList = new ArrayList<Integer>();
		// 获取注册时填写的推荐人对象
		if (user.getPuser() != null) {// 判断注册的时候是否填写了推荐人
			User pUser = userMapper.queryUserByPhoneNum(user.getPuser().getPhoneNum());// 通过推荐人的帐号查找推荐人帐号信息
			pList = userMapper.queryPlistById(pUser.getId());// 根据推荐人的Id查找其所有上级的Id
		}
		pList.add(id);// 将注册的玩家id添加到其自身的上级列表中
		// 保存玩家的上级Id列表
		userMapper.saveUserPid(id, pList, new Date());

	}

	// 通过用户帐号(电话)查找用户信息
	public User queryUserByPhoneNum(String phoneNum) {
		return userMapper.queryUserByPhoneNum(phoneNum);
	}

	// 用户登录时更新其登录ip和登录时间
	public void updateLoginInfo(User cur_user) {
		userMapper.updateByPrimaryKeySelective(cur_user);
	}

}
