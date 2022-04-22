import request from '@/utils/request'

export function getConfigList(data) {
  return request({
    url: '/system/config/getList',
    method: 'get',
		params: data
  })
}

export function updateConfig(data) {
  return request({
    url: 'system/config/editSubmit',
    method: 'post',
    data
  })
}

export function deleteConfig(data) {
  return request({
    url: 'system/config/delete',
    method: 'post',
    params: {nids:data}
  })
}

export function getConfigByNid(data) {
  return request({
    url: '/system/config/getByNid',
    method: 'get',
		params: data
  })
}

export function getModConfigList(data) {
  return request({
    url: '/system/modConfig/getList',
    method: 'get',
		params: data
  })
}

export function updateModConfig(data) {
  return request({
    url: 'system/modConfig/editSubmit',
    method: 'post',
    data
  })
}

