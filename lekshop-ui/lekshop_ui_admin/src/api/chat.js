import request from '@/utils/request'

export function getChatSessionList(data) {
  return request({
    url: 'system/chatSession/getList',
    method: 'get',
		params: data
  })
}

export function deleteChatSession(data) {
  return request({
    url: 'system/chatSession/delete',
    method: 'post',
    data
  })
}

export function getChatList(data) {
  return request({
    url: 'system/chat/getList',
    method: 'get',
		params: data
  })
}

export function countUnReadNum(data) {
  return request({
    url: 'system/chat/countUnReadNum',
    method: 'get',
		params: data
  })
}


export function sendMsg(data) {
  return request({
    url: 'system/chat/send',
    method: 'post',
    data
  })
}

export function readBySession(data) {
  return request({
    url: 'system/chat/read',
    method: 'post',
    data
  })
}

