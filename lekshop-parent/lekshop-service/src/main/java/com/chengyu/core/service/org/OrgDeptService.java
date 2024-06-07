package com.chengyu.core.service.org;


import com.chengyu.core.domain.result.DeptResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.model.OrgDept;
import com.chengyu.core.model.UmsShop;

import java.util.List;

/**
 * @title  部门
 * @author LeGreen
 * @date   2022/7/6
 */
public interface OrgDeptService {

    /**
     * 添加部门
     * @author LeGreen
     * @date   2022/7/6
     * @param  dept 部门信息
     */
    void addDept(OrgDept dept);

    /**
     * 删除部门
     * @author LeGreen
     * @date   2022/7/6
     * @param  id 部门ID
     * @throws ServiceException 业务异常
     */
    void deleteDept(Integer id) throws ServiceException;

    /**
     * 修改部门信息
     * @author LeGreen
     * @date   2022/7/6
     * @param  dept 部门信息
     */
    void updateDept(OrgDept dept);

    /**
     * 获取所有的下级部门
     * @author LeGreen
     * @date   2022/7/6
     * @param  currentDeptId 当前部门
     * @return List<DeptResult>
     */
    List<DeptResult> getAllDepts(Integer shopId, Integer currentDeptId);

    /**
     * 查询直属下级部门
     * @author LeGreen
     * @date   2022/7/8
     * @param  deptId 当前部门ID
     * @return List<OrgDept>
     */
    List<OrgDept> getJuniorDeptList(Integer shopId, Integer deptId);

    /**
     * 获取部门信息
     * @author LeGreen
     * @date   2022/7/13
     * @param  deptId 部门ID
     * @return OrgDept
     */
    OrgDept getDept(Integer deptId);

    /**
     * 获取所有的下级部门
     * @author LeGreen
     * @date   2022/7/14
     * @param  deptId 当前部门ID
     * @param  limit 限制级别
     * @return List<DeptResult>
     */
    List<DeptResult> getDownDeptList(Integer shopId, Integer deptId, Integer limit, boolean queryMember, boolean queryRole);

    /**
     * 获取所有的上级部门
     * @author LeGreen
     * @date   2022/7/14
     * @param  deptId 当前部门ID
     * @param  limit 限制级别
     * @return List<DeptResult>
     */
    List<DeptResult> getUpDeptList(Integer shopId, Integer deptId, Integer limit, boolean queryMember, boolean queryRole);

    /**
     * 获取下级部门TreeIds
     * @author LeGreen
     * @date   2022/7/29
     * @param  deptId 当前部门ID
     * @param  limit 限制级别
     * @return String
     */
    String getDownDeptTreeIdsList(Integer shopId, Integer deptId, Integer limit);

    /**
     * 初始化内置部门和角色
     * @author LeGreen
     * @date   2022/9/16
     * @param  shop 店铺
     */
    void init(UmsShop shop);
}
