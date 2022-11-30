package com.chengyu.core.service.org.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import com.chengyu.core.domain.CommonConstant;
import com.chengyu.core.domain.result.DeptResult;
import com.chengyu.core.exception.ServiceException;
import com.chengyu.core.mapper.OrgDeptMapper;
import com.chengyu.core.mapper.OrgRoleMapper;
import com.chengyu.core.model.*;
import com.chengyu.core.service.org.OrgDeptService;
import com.chengyu.core.service.org.OrgRoleService;
import com.chengyu.core.service.shop.ShopAccountService;
import com.chengyu.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrgDeptServiceImpl implements OrgDeptService {

    @Autowired
    private OrgDeptMapper deptMapper;
    @Autowired
    private OrgRoleMapper roleMapper;
    @Autowired
    private ShopAccountService shopAccountService;
    @Autowired
    private OrgRoleService roleService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addDept(OrgDept dept) {
        if (dept.getParentId() != null && dept.getParentId() != 0) {
            OrgDept parentDept = deptMapper.selectByPrimaryKey(dept.getParentId());
            dept.setTreeIds(parentDept.getTreeIds() + parentDept.getId());
        } else {
            dept.setParentId(0);
            dept.setTreeIds("0");
        }
        //查询时可使用like ",1,"
        dept.setTreeIds(dept.getTreeIds()+",");
        dept.setAddTime(DateUtil.date());
        dept.setUpdTime(dept.getAddTime());
        deptMapper.insertSelective(dept);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteDept(Integer id) throws ServiceException {
        //如果有下级或者，该部门下有角色，无法删除
        OrgDeptExample example = new OrgDeptExample();
        example.createCriteria().andParentIdEqualTo(id).andDelIndEqualTo(CommonConstant.NO_INT);
        if (deptMapper.countByExample(example) > 0) {
            throw new ServiceException("请先删除下级部门");
        }
        OrgRoleExample roleExample = new OrgRoleExample();
        roleExample.createCriteria().andDeptIdEqualTo(id).andDelIndEqualTo(CommonConstant.NO_INT);
        if (roleMapper.countByExample(roleExample) > 0) {
            throw new ServiceException("请先删除该部门下的所有角色");
        }

        //逻辑删除
        OrgDept updatedDept = new OrgDept();
        updatedDept.setId(id);
        updatedDept.setDelInd(CommonConstant.YES_INT);
        updatedDept.setUpdTime(DateUtil.date());
        deptMapper.updateByPrimaryKeySelective(updatedDept);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateDept(OrgDept dept) {
        OrgDept updateDept = new OrgDept();
        updateDept.setId(dept.getId());
        updateDept.setDeptName(dept.getDeptName());
        updateDept.setSort(dept.getSort());
        updateDept.setLeader(dept.getLeader());
        updateDept.setPhone(dept.getPhone());
        updateDept.setEmail(dept.getEmail());
        updateDept.setStatus(dept.getStatus());
        updateDept.setUpdTime(DateUtil.date());
        deptMapper.updateByPrimaryKeySelective(updateDept);
    }

    @Override
    public List<DeptResult> getAllDepts(Integer shopId, Integer currentDeptId) {
        List<OrgDept> list = this.getDeptListByParentId(shopId, currentDeptId);
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        return this.initTreeList(list, currentDeptId,false, false);
    }

    @Override
    public List<OrgDept> getJuniorDeptList(Integer shopId, Integer deptId) {
        OrgDeptExample example = new OrgDeptExample();
        example.setOrderByClause("sort asc");
        OrgDeptExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(deptId);
        if(shopId != null) {
            criteria.andShopIdEqualTo(shopId);
        }
        return deptMapper.selectByExample(example);
    }

    @Override
    public OrgDept getDept(Integer deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public List<DeptResult> getDownDeptList(Integer shopId, Integer deptId, Integer limit, boolean queryMember, boolean queryRole) {
        List<OrgDept> list = this.getDeptListByParentId(shopId, deptId);
        if (CollectionUtil.isEmpty(list)) {
            list = new ArrayList<>();
        }
        OrgDept currentDept = deptMapper.selectByPrimaryKey(deptId);
        list.add(0, currentDept);
        return this.initTreeList(list, currentDept.getParentId(), queryMember, queryRole);
    }

    @Override
    public List<DeptResult> getUpDeptList(Integer shopId, Integer deptId, Integer limit, boolean queryMember, boolean queryRole) {
        OrgDept dept = this.getDept(deptId);
        List<String> parentIdStrs = Arrays.stream(dept.getTreeIds().split(CommonConstant.DH_REGEX)).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        CollectionUtil.reverse(parentIdStrs);
        List<String> newIds = CollectionUtil.sub(parentIdStrs, 0, limit);
        List<Integer> idList = Convert.convert(new TypeReference<List<Integer>>() {}, newIds);
        idList.add(deptId);

        OrgDeptExample example = new OrgDeptExample();
        example.setOrderByClause("sort asc");
        OrgDeptExample.Criteria criteria = example.createCriteria();
        criteria.andDelIndEqualTo(CommonConstant.NO_INT).andIdIn(idList);

        return this.initTreeList(deptMapper.selectByExample(example), 0, queryMember, queryRole);
    }

    @Override
    public String getDownDeptTreeIdsList(Integer shopId, Integer deptId, Integer limit) {
        List<OrgDept> list = this.getDeptListByParentId(shopId, deptId);
        if (CollectionUtil.isEmpty(list)) {
            return "";
        }
        return CommonConstant.DH_REGEX+CollUtil.join(list.stream().map(OrgDept::getId).collect(Collectors.toList()), CommonConstant.DH_REGEX)+CommonConstant.DH_REGEX;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void init(UmsShop shop) {
        //获取预制的部门
        OrgDept dept = new OrgDept();
        dept.setShopId(shop.getId());
        dept.setShopName(shop.getName());
        dept.setDeptName("公司总部");
        this.addDept(dept);

        //二级部门
        List<String> twoDeptNames = CollectionUtil.newArrayList("老板", "采购部", "销售部", "仓储部", "配送部", "财务部");
        List<String[]> twoRoleNames = CollectionUtil.newArrayList(
                new String[]{"老板"},
                new String[]{"采购主管", "采购专员", "文员"},
                new String[]{"KA销售", "二批业务", "订单专员"},
                new String[]{"仓储主管", "进仓审核", "拣货专员", "出仓审核"},
                new String[]{"司机", "车销业务员"},
                new String[]{"总账会计", "出纳", "文员"});
        Integer salesDeptId = null;
        int i = 0;
        for(String deptName : twoDeptNames) {
            OrgDept twoDept = new OrgDept();
            twoDept.setShopId(shop.getId());
            twoDept.setShopName(shop.getName());
            twoDept.setDeptName(deptName);
            twoDept.setParentId(dept.getId());
            this.addDept(twoDept);
            if(i == 2) {
                salesDeptId = twoDept.getId();
            }

            for(String roleName : twoRoleNames.get(i)) {
                OrgRole role = new OrgRole();
                role.setDeptId(twoDept.getId());
                role.setDeptName(twoDept.getDeptName());
                role.setShopId(shop.getId());
                role.setShopName(shop.getName());
                role.setRoleName(roleName);
                roleMapper.insertSelective(role);
            }
            i++;
        }

        //三级部门
        List<String> threeDeptNames = CollectionUtil.newArrayList("门店", "电商运营");
        List<String[]> threeRoleNames = CollectionUtil.newArrayList(
                new String[]{"店长", "店员", "收银", "业务员"},
                new String[]{"客服"});
        int j = 0;
        for(String deptName : threeDeptNames) {
            OrgDept threeDept = new OrgDept();
            threeDept.setShopId(shop.getId());
            threeDept.setShopName(shop.getName());
            threeDept.setDeptName(deptName);
            threeDept.setParentId(salesDeptId);
            this.addDept(threeDept);

            for(String roleName : threeRoleNames.get(j)) {
                OrgRole role = new OrgRole();
                role.setDeptId(threeDept.getId());
                role.setDeptName(threeDept.getDeptName());
                role.setShopId(shop.getId());
                role.setShopName(shop.getName());
                role.setRoleName(roleName);
                roleMapper.insertSelective(role);
            }
            j++;
        }
    }

    private List<DeptResult> initTreeList(List<OrgDept> list, Integer topDeptId, boolean queryMember, boolean queryRole){
        //将部门转换成Result
        List<DeptResult> deptList = list.stream().map((dept) -> {
            DeptResult result = new DeptResult();
            BeanUtil.copyProperties(dept, result);
            result.setParentName(getDeptName(dept.getParentId()));
            if(queryMember){
                result.setMemberList(shopAccountService.getAccountListByDept(dept.getId()));
            }
            if(queryRole){
                result.setRoleList(roleService.getAllRolesByDeptId(dept.getShopId(), dept.getId()));
            }
            return result;
        }).collect(Collectors.toList());
        //使用filter去进行拦截，进行判断
        return deptList.stream().filter(dept -> Objects.equals(topDeptId, dept.getParentId()))
                .peek(dept -> dept.setChildren(createChildList(dept, deptList)))
                .sorted(Comparator.comparing(DeptResult::getSort))
                .collect(Collectors.toList());
    }

    private static List<DeptResult> createChildList(DeptResult dept, List<DeptResult> deptList) {
        return deptList.stream().filter(model -> dept.getId().equals(model.getParentId()))
                .peek(model -> model.setChildren(createChildList(model, deptList)))
                .sorted((Comparator.comparing(DeptResult::getSort))).collect(Collectors.toList());
    }

    private List<OrgDept> getDeptListByParentId(Integer shopId, Integer parentId) {
        OrgDeptExample example = new OrgDeptExample();
        example.setOrderByClause("sort asc");
        OrgDeptExample.Criteria criteria = example.createCriteria();
        criteria.andDelIndEqualTo(CommonConstant.NO_INT);
        if (parentId != null && parentId != 0) {
            criteria.andTreeIdsLike("%," + parentId + ",%");
        }
        if(shopId != null) {
            criteria.andShopIdEqualTo(shopId);
        }
        return deptMapper.selectByExample(example);
    }

    private String getDeptName(Integer id){
        if(id == null || id == 0){
            return "";
        }else{
            return deptMapper.selectByPrimaryKey(id).getDeptName();
        }
    }
}