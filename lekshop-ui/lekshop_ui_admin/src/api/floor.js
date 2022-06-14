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
