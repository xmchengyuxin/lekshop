import request from '@/utils/request'

export function getSourceCateList(data) {
  return request({
    url: 'member/sourceCate/getList',
    method: 'get',
		params: data
  })
}

export function updateSourceCate(data) {
  return request({
    url: 'member/sourceCate/editSubmit',
    method: 'post',
    data
  })
}

export function deleteSourceCate(data) {
  return request({
    url: 'member/sourceCate/delete',
    method: 'post',
    params: {ids:data}
  })
}

export function getSourceList(data) {
  return request({
    url: 'member/source/getList',
    method: 'get',
		params: data
  })
}

export function updateSource(data) {
  return request({
    url: 'member/source/editSubmit',
    method: 'post',
    data
  })
}

export function moveCate(data) {
  return request({
    url: 'member/source/moveCate',
    method: 'post',
    data
  })
}


export function deleteSource(data) {
  return request({
    url: 'member/source/delete',
    method: 'post',
    params: {ids:data}
  })
}
