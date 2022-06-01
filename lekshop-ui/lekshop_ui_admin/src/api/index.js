import request from '@/utils/request'

export function countMember() {
  return request({
    url: '/system/countMember',
    method: 'get'
  })
}

export function getOrderAddList() {
  return request({
    url: '/system/getMemberAddList',
    method: 'get'
  })
}

export function countNumber(data) {
  return request({
    url: '/system/countNumber',
    method: 'get',
		params: data
  })
}

export function countVerifyNum(data) {
  return request({
    url: '/system/countVerifyNum',
    method: 'get',
		params: data
  })
}

export function countAmount(data) {
  return request({
    url: '/system/countAmount',
    method: 'get',
		params: data
  })
}


export function getLoginInfo() {
  return request({
    url: '/system/getLoginInfo',
    method: 'get'
  })
}

export function countPlatformIncome(data) {
  return request({
    url: '/system/countPlatformIncome',
    method: 'get',
		params: data
  })
}

export function getShopSellList(data) {
  return request({
    url: 'system/getShopSellList',
    method: 'get',
		params: data
  })
}

export function getGoodsSellList(data) {
  return request({
    url: 'system/getGoodsSellList',
    method: 'get',
		params: data
  })
}

export function getConversionReport() {
  return request({
    url: 'system/getConversionReport',
    method: 'get'
  })
}