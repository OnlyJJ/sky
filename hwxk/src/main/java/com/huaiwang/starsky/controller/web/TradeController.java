package com.huaiwang.starsky.controller.web;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaiwang.starsky.common.vo.SysResult;
import com.huaiwang.starsky.service.web.PhoneVerificaitonService;

@Controller
@RequestMapping("/trade")
public class TradeController {
	private static final Logger log = Logger.getLogger(TradeController.class);
	private String msg = "";
	
	@Autowired
	public PhoneVerificaitonService phoneVerificaitonService;
	
	//交易时获取验证码
	@RequestMapping(value = "/getTrade",method = RequestMethod.GET)
	@ResponseBody
	public SysResult trade(String phone){		
		try {
			if (phone == null || "".equals(phone) || phone.length() != 11) {
				msg = "请输入正确的手机号";
				return SysResult.build(SysResult.FAULT, msg);
			}
			return phoneVerificaitonService.tradeCation(phone);
		} catch (Exception e) {	
			e.printStackTrace();
			log.error(e.getMessage());
			msg = "系统繁忙，请稍后重试";
			return SysResult.build(SysResult.FAULT,msg);
		}	
	}
	
	//产出率
		public double createProRate(double inPro,double outPro){
			//inpro =百分之1.5=0.015 
			Random random = new Random();
	        String vcode = "";
	        int s = random.nextInt(15);
			
			
			return 0.0;
		}
		
		@RequestMapping(value = "/c")
		public void c(double in,double out){
			Random random = new Random();
	        double re = 0.0;
	        int min = (int) (in*10);
	        int max = (int) (out*10);
	        while(re<min){
	        	int num = random.nextInt(max);
	        	if(max > num && num > min){
	        		re = (double)num ;
	        		System.out.println(re);
	        		break ;
	        	}
	        }
	        double result = re/1000;
		}
	
	
}
