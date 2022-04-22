import request from '@/utils/request'

export function getMenuList() {
  return request({
    url: '/system/menu/getList',
    method: 'get'
  })
}

export function getMenu(data) {
  return request({
    url: '/system/menu/get',
    method: 'get',
	params:{id : data}
  })
}

export function saveMenu(data) {
  return request({
    url: '/system/menu/editSubmit',
    method: 'post',
	data
	})
}

export function deleteMenu(data) {
  return request({
    url: 'system/menu/delete',
    method: 'post',
    params: {id:data}
  })
}





