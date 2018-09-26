package com.oa.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


public class BaseController {
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public Map<String,Object> getParam(HttpServletRequest request){
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		 
		Enumeration<String> keys = request.getParameterNames();
		
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			String value= request.getParameter(key);
			
			resultMap.put(key, value);
		}
		return resultMap;
	}
	/**
	 * 
	 * @param startIndex ��ʼҳ
	 * @param pageSize ÿҳ����
	 * @param total ������
	 * @param result ���ؽ��
	 * @return
	 */
	public ModelAndView getPageNoInfo(int startIndex,int pageSize,int total,ModelAndView result){
		//Math.ceil������Ϊ��������Math.ceilС����Ϊ�����������
		int current = (int) Math.ceil((startIndex+1.0)/pageSize); //��ǰҳ��ʾ
		
		result.addObject("current", current);
		result.addObject("startIndex",startIndex );
		result.addObject("pageSize",pageSize );
		result.addObject("total", total);
		if(total>0){
			int page = (int)Math.ceil(total/pageSize);//��������ÿҳҳ��=��ҳ��;
		
			double totald = total;
			
			if(totald/pageSize>total/pageSize){
				page+=1;
			}
		result.addObject("page", page);
		
		int startPage=0;
		int endPage=0;
		
		if(page<8){
			startPage = 1;
			endPage = page;
		}else{
			if(current<5){//չʾǰ��ҳ
				startPage = 1;
				endPage = 6;
			}else if(page-current<6){//չʾ����ҳ
				startPage = page-5;
				endPage = page;
			}else{
				startPage = current - 2;
				endPage = current + 2;
			}
		}
		result.addObject("startPage", startPage);
		result.addObject("endPage", endPage);
		}else{
			result.addObject("page",0);
		}
		
		return result;
	}

	public void printJson(HttpServletResponse response,String content ){
		response.setContentType("application/json;chartset=utf-8");
	
			try {
				response.getWriter().println(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
}
