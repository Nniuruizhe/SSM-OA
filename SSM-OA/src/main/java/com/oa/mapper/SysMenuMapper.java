package com.oa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.oa.entity.SysMenu;


public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Tue Jun 19 17:33:26 CST 2018
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbg.generated Tue Jun 19 17:33:26 CST 2018
     */
    int insertSelective(SysMenu record);

	List<SysMenu> selectMenuByRoleId(List list);
}