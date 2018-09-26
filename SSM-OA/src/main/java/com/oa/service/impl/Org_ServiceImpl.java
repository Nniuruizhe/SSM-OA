package com.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oa.entity.SysOrg;
import com.oa.mapper.SysOrgMapper;
import com.oa.service.Org_Service;

@Service
public class Org_ServiceImpl implements Org_Service {

	@Resource
	private SysOrgMapper orgMapper;
	
	/**
	 * ҳ����ʾ��֯�б���Ϣ
	 * @param paramMap
	 * @return
	 */
	@Override
	public Map<String, Object> selectOrgList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		//��ʼҳ��¼ ÿҳ��ʾ������
		paramMap.put("startIndex", Integer.parseInt(paramMap.get("startIndex")+""));
		paramMap.put("pageSize", Integer.parseInt(paramMap.get("pageSize")+""));
		
		//�����������
		List<SysOrg> list = orgMapper.selectOrgByPage(paramMap);
		//z�ܼ�¼��
		long total = orgMapper.selectTotalCount(paramMap);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgList", list);
		map.put("total", total);
		
		return map;
	}
	
	/**
	 * ������ѯ����֯
	 */
	@Override
	public List<SysOrg> selectOrgListByParentId(long parentId) {
		// TODO Auto-generated method stub
		return orgMapper.selectOrgListByParentId(parentId);
	}

	/**
	 * ������֯ʱ����֯��Ż���
	 */
	@Override
	public long selectOrgId() {
		// TODO Auto-generated method stub
		return orgMapper.selectOrgId();// ��ѯid �����ֵ
	}

	/**
	 * ��ѯ��ǰ��֯������֯
	 */
	@Override
	public SysOrg selectOrgAndParentIdByOrgId(long orgId) {
		// TODO Auto-generated method stub
		return orgMapper.selectOrgAndParentByOrgId(orgId);
	}

	/**
	 * ��֯����
	 */
	@Override
	public int insert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return orgMapper.insert(paramMap);
	}

	/**
	 * ��֯����
	 */
	@Override
	public int updateByPrimaryKeySelective(SysOrg org) {
		// TODO Auto-generated method stub
		return orgMapper.updateByPrimaryKeySelective(org);
	}

	/**
	 * ��֯ɾ��
	 */
	@Override
	public int deleteByPrimaryKey(long orgId) {
		// TODO Auto-generated method stub
		return orgMapper.deleteByPriamryKey(orgId);
	}
	

}
