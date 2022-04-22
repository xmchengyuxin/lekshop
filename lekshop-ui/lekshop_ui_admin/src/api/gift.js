import request from '@/utils/request'

export function getGiftList(data) {
  return request({
    url: '/system/gift/getList',
    method: 'get',
		params: data
  })
}

export function importGift(data) {
  return request({
    url: 'system/gift/import',
    method: 'post',
    data
  })
}

export function updateGiftStatus(data) {
  return request({
    url: 'system/gift/updateStatus',
    method: 'post',
    data
  })
}

export function updateGiftPrice(data) {
  return request({
    url: 'system/gift/updatePrice',
    method: 'post',
    data
  })
}

export function addPrice(data) {
  return request({
    url: 'system/gift/addPrice',
    method: 'post',
    data
  })
}

export function batchUpdateStatus(data) {
  return request({
    url: 'system/gift/batchUpdateStatus',
    method: 'post',
    data
  })
}

