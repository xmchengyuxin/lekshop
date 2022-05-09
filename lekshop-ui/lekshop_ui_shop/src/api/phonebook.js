import request from '@/utils/request'

export function getPhonebookList(data) {
  return request({
    url: '/system/phonebook/getList',
    method: 'get',
    params: data
  })
}

export function deletePhonebook(data) {
  return request({
    url: 'system/phonebook/delete',
    method: 'post',
    params: {ids:data}
  })
}


export function getPhonebookLogList(data) {
  return request({
    url: '/system/phonebookLog/getList',
    method: 'get',
    params: data
  })
}


export function getPhonebookMsgList(data) {
  return request({
    url: '/system/phonebookMessage/getList',
    method: 'get',
    params: data
  })
}
