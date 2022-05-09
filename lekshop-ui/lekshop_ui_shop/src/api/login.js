import request from '@/utils/request'

export function loginByUsername(username, password, brandId) {
  const data = {
    username,
    password,
    brandId
  }
  return request({
    url: '/common/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/common/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/member/getUser',
    method: 'get',
    params: { token }
  })
}

export function refreshToken() {
  return request({
    url: '/common/refreshToken',
    method: 'post'
  })
}
