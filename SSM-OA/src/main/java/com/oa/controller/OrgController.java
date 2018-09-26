package com.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.oa.entity.SysOrg;
import com.oa.entity.SysUser;
import com.oa.service.Org_Service;
import com.oa.service.User_Service;

@Controller()
@RequestMapping("/org")
public class OrgController extends BaseController {
	
	@Resource
	private Org_Service orgService;
	
	@Resource
	private User_Service userService;
	
	
	@RequestMapping("/orgMana")
	public String orgMana(){
		
		return "org/orgMana";
	}

	/**
	 * ��ѯ��֯�б�
	 */
	@RequestMapping("/orgList")
public String orgList(HttpServletRequest request){
		
		Map<String,Object> paramMap = getParam(request);
		
		Map<String,Object> resMap = orgService.selectOrgList(paramMap);
		
		HttpSession session = request.getSession();
		session.setAttribute("orgList", resMap.get("orgList"));
		session.setAttribute("total", resMap.get("total"));
		
		return "org/orgList";
	
		
}

	/**
	 * ��ҳ
	 * @param request
	 * @return
	 */
@RequestMapping("/orgNumber")
public ModelAndView orgNumber(HttpServletRequest request){
	Map<String,Object> paramMap = getParam(request);
	
	int total = Integer.parseInt(paramMap.get("total")+"");
	int startIndex = Integer.parseInt(paramMap.get("startIndex")+"");
	int pageSize = Integer.parseInt(paramMap.get("pageSize")+"");
	
	ModelAndView result =  new ModelAndView();
	result = super.getPageNoInfo(total, pageSize, total, result);
	result.setViewName("org/orgPageNumber");
	
	return result;
}
/**
 * ��֯����
 * @param request
 * @param response
 */
@RequestMapping("/addOrg")
public void addOrg(HttpServletRequest request,HttpServletResponse response){
	
	Map<String,Object> paramMap = super.getParam(request);
	paramMap.put("orgPath", paramMap.get("orgParentOrgPath")+""+(orgService.selectOrgId()+1));
	
	Map<String, Object> map = new HashMap<String,Object>();
	
	orgService.insert(paramMap); //����
	
	map.put("isSuccess", true);
	
	super.printJson(response, "true");
	//super.printJson(response, new GSON().toJson(map));
}

/**
 * ��֯������ѯ
 * @param request
 * @param response
 * @param orgId
 */
@RequestMapping("/queryOrg")
public void queryOrg(HttpServletRequest request,HttpServletResponse response,long orgId ){
	
	SysOrg org = orgService.selectOrgAndParentIdByOrgId(orgId);
	
	//���� json
	super.printJson(response, new Gson().toJson(org));
}
/**
 * ��֯����
 * @param org
 * @param response
 */
@RequestMapping("/updateOrg")
public void updateOrg(SysOrg org,HttpServletResponse response){
	
	int i = orgService.updateByPrimaryKeySelective(org);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	if(i>0){
		map.put("isSuccess", true);
	}else{
		map.put("isSuccess", false);
	}
	
	super.printJson(response, new Gson().toJson(map));
}

/**
 * ��֯ɾ��
 * @param orgId
 * @param response
 */
@RequestMapping("/delete")
public void delete(long orgId,HttpServletResponse response){
	//�ж���֯�����û�����ɾ����֯
	List<SysUser> userList = userService.selectUserListByOrgId(orgId);
	
	//��֯�������û�����ɾ��
	List<SysOrg> orgList = orgService.selectOrgListByParentId(orgId);
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	if(orgList !=null && orgList.size()>0){
		map.put("isSuccess", "hashSubOrg");
	}else if(userList != null && userList.size()>0){
		map.put("isSuccess", "hashUsers");
		
	}else{
		orgService.deleteByPrimaryKey(orgId);
		map.put("isSuccess", true);
	}
	
	super.printJson(response, new Gson().toJson(map));
}




}

