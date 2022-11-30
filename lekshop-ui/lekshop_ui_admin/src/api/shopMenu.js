import request from '@/utils/request'

export function getMenuList(data) {
  return request({
    url: '/system/orgMenu/getList',
    method: 'get',
		params: data
  })
}

export function updateMenu(data) {
  return request({
    url: '/system/orgMenu/editSubmit',
    method: 'post',
    data
  })
}

export function deleteMenu(data) {
  return request({
    url: '/system/orgMenu/delete',
    method: 'post',
    params: {ids:data}
  })
}
