import request from '@/utils/request'

export function loginByUsername(username, password, brandId) {
  const data = {
    username,
    password,
    brandId
  }
  return request({
    url: '/system/common/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/system/common/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/system/info/get',
    method: 'get',
    params: { token }
  })
}

export function refreshToken() {
  return request({
    url: '/system/common/refreshToken',
    method: 'post'
  })
}
