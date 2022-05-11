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
    url: '/member/shop/get',
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
export function register(data) {
  return request({
    url: '/common/register',
    method: 'POST',
    data
  })
}
export function getCodeImg() {
  return request({
    url: '/common/captcha',
    method: 'get',
  })
}

export function getCodePhone(data) {
  return request({
    url: '/common/sendCode',
    method: 'POST',
    data
  })
}
export function phoneLogin(data) {
  return request({
    url: '/common/forgetPassword',
    method: 'POST',
    data
  })
}
export function setPassword(data) {
  return request({
    url: '/member/setPassword',
    method: 'POST',
    data
  })
}
