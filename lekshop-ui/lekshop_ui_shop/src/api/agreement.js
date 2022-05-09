import request from '@/utils/request'

export function getAgreementList(data) {
  return request({
    url: '/system/agreement/getList',
    method: 'get',
		params: data
  })
}

export function updateAgreement(data) {
  return request({
    url: 'system/agreement/editSubmit',
    method: 'post',
    data
  })
}
