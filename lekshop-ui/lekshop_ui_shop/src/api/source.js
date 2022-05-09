import request from '@/utils/request'

export function getSourceCateList(data) {
  return request({
    url: '/system/sourceCate/getList',
    method: 'get',
		params: data
  })
}

export function updateSourceCate(data) {
  return request({
    url: 'system/sourceCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteSourceCate(data) {
  return request({
    url: 'system/sourceCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSourceList(data) {
  return request({
    url: '/system/source/getList',
    method: 'get',
		params: data
  })
}

export function updateSource(data) {
  return request({
    url: 'system/source/editSubmit',
    method: 'post',
    data
  })
}

export function moveCate(data) {
  return request({
    url: 'system/source/moveCate',
    method: 'post',
    data
  })
}


export function deleteSource(data) {
  return request({
    url: 'system/source/delete',
    method: 'post',
    params: {ids:data}
  })
}
