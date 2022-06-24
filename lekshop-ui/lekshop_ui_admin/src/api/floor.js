import request from '@/utils/request'

export function getFloorList(query) {
  return request({
    url: '/system/floor/getList',
    method: 'get',
    params: query
  })
}

export function getFloorDataList(query) {
  return request({
    url: '/system/floor/getDataList',
    method: 'get',
    params: query
  })
}

export function updateFloor(data) {
  return request({
    url: '/system/floor/editSubmit',
    method: 'post',
    data
  })
}

export function deleteFloor(data) {
  return request({
    url: '/system/floor/delete',
    method: 'post',
    params: {id:data}
  })
}

export function updateFloorData(data) {
  return request({
    url: '/system/floor/savePageData',
    method: 'post',
    data
  })
}

export function getGlobalStyle(query) {
  return request({
    url: '/system/globalStyle/get',
    method: 'get',
    params: query
  })
}

export function getDefaultGlobalStyle(query) {
  return request({
    url: '/system/globalStyle/getDefault',
    method: 'get',
    params: query
  })
}

export function updateGlobalStyle(data) {
  return request({
    url: '/system/globalStyle/editSubmit',
    method: 'post',
    data
  })
}
