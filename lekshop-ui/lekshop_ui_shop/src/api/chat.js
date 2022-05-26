import request from '@/utils/request'

export function getChatSessionList(data) {
  return request({
    url: 'member/chatSession/getList',
    method: 'get',
		params: data
  })
}

export function deleteChatSession(data) {
  return request({
    url: 'member/chatSession/delete',
    method: 'post',
    data
  })
}

export function getChatList(data) {
  return request({
    url: 'member/chat/getList',
    method: 'get',
		params: data
  })
}

export function countUnReadNum(data) {
  return request({
    url: 'member/chat/countUnReadNum',
    method: 'get',
		params: data
  })
}


export function sendMsg(data) {
  return request({
    url: 'member/chat/send',
    method: 'post',
    data
  })
}

export function readBySession(data) {
  return request({
    url: 'member/chat/read',
    method: 'post',
    data
  })
}

