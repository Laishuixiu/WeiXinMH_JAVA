package com.jspxcms.plug.service;

import java.util.ArrayList;
import java.util.List;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.model.Menu;
import com.jspxcms.core.ContextConfig;
import com.jspxcms.plug.domain.WeChatMenu;

public class WeiXinMenuTest {

	public static void main(String[] args) {
		// 获取accessToken -参数appid，secret
		
		
		//创建菜单
       StringBuffer json = new StringBuffer();
       json.append("{")
       		.append("	\"button\":[ ")
       		.append("	}	")
       		.append("			\"type\":\"click\", ")
       		.append("			\"name\":\"今日歌曲\",	")
       		.append("			\"key\":\"V1001_TODAY_MUSIC\"	")
       		.append(" 	},	")
       		.append("	{	")
       		.append("		 \"name\":\"菜单\",	")
       		.append("		\"sub_button\":[	")
       		.append("		{	")
       		.append("			\"type\":\"view\",	")
       		.append("			\"name\":\"搜索\",	")
       		.append("			\"url\":\"http://www.soso.com/\"	")
       		.append("		},")       		
       		.append("		{")
       		.append("		 	\"type\":\"view\",	")
       		.append("		 	\"name\":\"视频\",	")
       		.append("			\"url\":\"http://v.qq.com/\"	")
       		.append("	 	},")
       		.append("	 	{")
       		.append("			\"type\":\"click\",	")
       		.append("			\"name\":\"赞一下我们\",	")
       		.append("			\"key\":\"V1001_GOOD\"	")
       		.append("		}]	")
       		.append("	 }]	")
       		.append(" }");

        //发送请求 创建菜单

       ContextConfig config = new ContextConfig();
       WeixinProxy wx = config.weixinProxy();
       
	try {
		String accessToken = wx.getTokenManager().getAccessToken();
		System.out.println(accessToken);
	} catch (WeixinException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		


	}

}
