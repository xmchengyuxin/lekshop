import request from '@/utils/request'

export function getAdvertList(data) {
  return request({
    url: '/system/advert/getList',
    method: 'get',
		params: data
  })
}

export function updateAdvert(data) {
  return request({
    url: 'system/advert/editSubmit',
    method: 'post',
    data
  })
}

export function updateAdvertStatus(data) {
  return request({
    url: 'system/advert/updateAdvertStatus',
    method: 'post',
    data
  })
}

export function deleteAdvert(data) {
  return request({
    url: 'system/advert/delete',
    method: 'post',
    params: {ids:data}
  })
}
