package com.huaiwang.starsky.controller.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaiwang.starsky.common.util.IpUtils;
import com.huaiwang.starsky.common.util.MD5Util;
import com.huaiwang.starsky.common.vo.SysResult;
import com.huaiwang.starsky.pojo.User;
import com.huaiwang.starsky.service.web.PhoneVerificaitonService;
import com.huaiwang.starsky.service.web.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	public PhoneVerificaitonService phoneVerificaitonService;

	private static final Logger log = Logger.getLogger(UserController.class);
	private String msg = "";

	@RequestMapping("/regist")
	@ResponseBody
	public SysResult regist(User user, HttpServletRequest request) {
		try {
			String ip = IpUtils.getRemoteHost(request);
			user.setIp(ip);
			userService.saveUser(user);
			msg = "恭喜您注册成功！";
			return SysResult.build(SysResult.SUCCESS, msg);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			msg = "对不起注册失败！请检查所填信息是否正确!";
			return SysResult.build(SysResult.FAULT, msg);
		}
	}

	// 注册时获取验证码，一天只允许三次
	@RequestMapping(value = "/getVCode", method = RequestMethod.GET)
	@ResponseBody
	public SysResult getVCode(String phoneNum) {
		try {
			if (phoneNum != null) {
				if (phoneNum.length() != 11) {
					return SysResult.build(SysResult.FAULT, "手机长度不对，请重新输入");
				}
				return phoneVerificaitonService.sendMsg(phoneNum);
			}
			msg = "请输入正确的手机号";
			return SysResult.build(SysResult.FAULT, msg);
		}catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			msg = "系统正忙，请稍后重试";
			return SysResult.build(SysResult.FAULT, msg);
		}
	}

	@RequestMapping("tologin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/login")
	@ResponseBody
	public SysResult login(User user, HttpServletRequest request) {
		try {
			User cur_user = userService.queryUserByPhoneNum(user.getPhoneNum());
			// 判断帐号(电话号码是否存在)
			if (StringUtils.isEmpty(cur_user.getPhoneNum())) {
				msg = "帐号不存在!";
				return SysResult.build(SysResult.FAULT, msg);
			}
			String pw = MD5Util.getMd5Hash(user.getPhoneNum(), user.getPassword());
			if (cur_user.getPassword().equals(pw)) {
				msg = "登录成功!";
				request.getSession().setAttribute("user", cur_user);
				String ip = IpUtils.getRemoteHost(request);
				// 记录用户登录的ip和登录时间
				cur_user.setIp(ip);
				cur_user.setUpdated(new Date());
				userService.updateLoginInfo(cur_user);
				return SysResult.build(SysResult.SUCCESS, msg, cur_user);
			} else {
				msg = "密码错误!";
				return SysResult.build(SysResult.FAULT, msg);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			msg = "网络异常,请稍后再试!";
			return SysResult.build(SysResult.FAULT, msg);
		}

	}

}
