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
    url: 'member/getLoginInfo',
    method: 'get'
  })
}
