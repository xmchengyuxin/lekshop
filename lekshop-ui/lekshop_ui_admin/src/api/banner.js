import request from '@/utils/request'

export function getBannerList(data) {
  return request({
    url: '/system/scrollImg/getList',
    method: 'get',
		params: data
  })
}

export function updateBanner(data) {
  return request({
    url: 'system/scrollImg/editSubmit',
    method: 'post',
    data
  })
}

export function deleteBanner(data) {
  return request({
    url: 'system/scrollImg/delete',
    method: 'post',
    params: {ids:data}
  })
}


