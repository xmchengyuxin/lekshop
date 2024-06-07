import request from '@/utils/request'

export function getSalesList(data) {
  return request({
    url: 'member/sales/getList',
    method: 'get',
		params: data
  })
}

export function addSales(data) {
  return request({
    url: 'member/sales/editSubmit',
    method: 'post',
    data
  })
}

export function deleteSales(data) {
  return request({
    url: 'member/sales/delete',
    method: 'post',
    params: {ids:data}
  })
}
