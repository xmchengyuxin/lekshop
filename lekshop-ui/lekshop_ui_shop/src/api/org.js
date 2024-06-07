import request from '@/utils/request'

export function getDeptList(data) {
  return request({
    url: '/member/dept/getList',
    method: 'get',
		params: data
  })
}

export function getUpDeptList(data) {
  return request({
    url: '/member/dept/getUpList',
    method: 'get',
		params: data
  })
}

export function getJuniorList(data) {
  return request({
    url: '/member/dept/getJuniorList',
    method: 'get',
		params: data
  })
}

export function updateDept(data) {
  return request({
    url: '/member/dept/editSubmit',
    method: 'post',
    data
  })
}

export function initDept(data) {
  return request({
    url: '/member/dept/init',
    method: 'post',
    data
  })
}

export function deleteDept(data) {
  return request({
    url: '/member/dept/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getDeptAttrList(data) {
  return request({
    url: '/member/dept/getAttrList',
    method: 'get',
		params: data
  })
}

export function getMenuList(data) {
  return request({
    url: '/member/menu/getList',
    method: 'get',
		params: data
  })
}

export function updateMenu(data) {
  return request({
    url: '/member/menu/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMenu(data) {
  return request({
    url: '/member/menu/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getRoleList(data) {
  return request({
    url: '/member/role/getList',
    method: 'get',
		params: data
  })
}

export function getRoleSelector(data) {
  return request({
    url: '/member/role/getSelector',
    method: 'get',
		params: data
  })
}

export function updateRole(data) {
  return request({
    url: '/member/role/editSubmit',
    method: 'post',
    data
  })
}

export function deleteRole(data) {
  return request({
    url: '/member/role/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getRoleMenus(data) {
  return request({
    url: '/member/role/getMenus',
    method: 'get',
		params: data
  })
}

export function getShopAccountList(query) {
  return request({
    url: '/member/shopAccount/getList',
    method: 'get',
    params: query
  })
}

export function getShopAccountListByDept(query) {
  return request({
    url: '/member/shopAccount/getListByDept',
    method: 'get',
    params: query
  })
}

export function getShopAccountDetail(query) {
  return request({
    url: '/member/shopAccount/getDetail',
    method: 'get',
    params: query
  })
}

export function updateShopAccount(data) {
  return request({
    url: '/member/shopAccount/editSubmit',
    method: 'post',
    data
  })
}

export function getShopAccountInfo(query) {
  return request({
    url: '/member/shopAccount/getInfo',
    method: 'get',
    params: query
  })
}

export function updateShopAccountInfo(data) {
  return request({
    url: '/member/shopAccount/updateInfo',
    method: 'post',
    data
  })
}

export function register(data) {
  return request({
    url: '/member/shopAccount/register',
    method: 'post',
    data
  })
}

export function getShopAccountRoleList(query) {
  return request({
    url: '/member/shopAccount/getRoleList',
    method: 'get',
    params: query
  })
}

export function addShopAccountRole(data) {
  return request({
    url: '/member/shopAccount/addRole',
    method: 'post',
    data
  })
}

export function deleteShopAccountRole(data) {
  return request({
    url: '/member/shopAccount/deleteRole',
    method: 'post',
    data
  })
}
