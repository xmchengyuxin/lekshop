import request from '@/utils/request'

export function getGoodsCateList(data) {
  return request({
    url: 'system/goodsCate/getList',
    method: 'get',
		params: data
  })
}

export function addGoodsCate(data) {
  return request({
    url: 'system/goodsCate/add',
    method: 'post',
    data
  })
}

export function updateGoodsCate(data) {
  return request({
    url: 'system/goodsCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteGoodsCate(data) {
  return request({
    url: 'system/goodsCate/delete',
    method: 'post',
    params: {ids:data}
  })
}
