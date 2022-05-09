import request from '@/utils/request'

export function getWebInfo() {
  return request({
    url: '/system/getWebInfo',
    method: 'get'
  })
}

export function getBankSelector() {
  return request({
    url: '/system/getBankSelector',
    method: 'get'
  })
}


