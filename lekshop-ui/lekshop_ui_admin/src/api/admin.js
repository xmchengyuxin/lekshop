import request from '@/utils/request'

export function getAdminList(data) {
  return request({
    url: '/system/admin/getList',
    method: 'get',
		params: data
  })
}

export function getAdmin(data) {
  return request({
    url: '/system/admin/get',
    method: 'get',
		params: data
  })
}

export function updateAdmin(data) {
  return request({
    url: 'system/admin/editSubmit',
    method: 'post',
    data
  })
}

export function deleteAdmin(data) {
  return request({
    url: 'system/admin/delete',
    method: 'post',
    params: {ids:data}
  })
}


