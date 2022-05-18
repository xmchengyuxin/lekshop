import request from '@/utils/request'

export function countGoods() {
  return request({
    url: 'member/countGoods',
    method: 'get'
  })
}

export function getOrderAddList() {
  return request({
    url: 'member/getOrderAddList',
    method: 'get'
  })
}

export function countNumber(data) {
  return request({
    url: 'member/countNumber',
    method: 'get',
		params: data
  })
}

export function getGoodsSellList(data) {
  return request({
    url: 'member/getGoodsSellList',
    method: 'get',
		params: data
  })
}


export function getLoginInfo() {
  return request({
    url: 'member/getLoginInfo',
    method: 'get'
  })
}
