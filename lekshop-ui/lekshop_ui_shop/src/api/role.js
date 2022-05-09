import request from '@/utils/request'

export function getRoleList(data) {
  return request({
    url: '/system/adminType/getList',
    method: 'get',
		params: data
  })
}

export function getRoutes() {
  return request({
    url: '/system/menu/getList',
    method: 'get'
  })
}

export function updateRole(data) {
  return request({
    url: 'system/adminType/editSubmit',
    method: 'post',
    data
  })
}

export function deleteRole(data) {
  return request({
    url: 'system/adminType/delete',
    method: 'post',
    params: {ids:data}
  })
}
