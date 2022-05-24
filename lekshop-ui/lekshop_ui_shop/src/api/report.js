import request from '@/utils/request'

export function getShopReportDayList(data) {
  return request({
    url: 'member/shopReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getShopReportMonthList(data) {
  return request({
    url: 'member/shopReport/getMonthList',
    method: 'get',
		params: data
  })
}

export function getGoodsReportDayList(data) {
  return request({
    url: 'member/goodsReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getGoodsReportMonthList(data) {
  return request({
    url: 'member/goodsReport/getMonthList',
    method: 'get',
		params: data
  })
}
