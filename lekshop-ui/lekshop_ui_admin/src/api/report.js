import request from '@/utils/request'

export function getShopReportDayList(data) {
  return request({
    url: 'system/shopReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getShopReportMonthList(data) {
  return request({
    url: 'system/shopReport/getMonthList',
    method: 'get',
		params: data
  })
}

export function getPlatformReportDayList(data) {
  return request({
    url: 'system/platformReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getPlatformReportMonthList(data) {
  return request({
    url: 'system/platformReport/getMonthList',
    method: 'get',
		params: data
  })
}


export function getGoodsReportDayList(data) {
  return request({
    url: 'system/goodsReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getGoodsReportMonthList(data) {
  return request({
    url: 'system/goodsReport/getMonthList',
    method: 'get',
		params: data
  })
}

export function getMemberReportDayList(data) {
  return request({
    url: 'system/memberReport/getDayList',
    method: 'get',
		params: data
  })
}

export function getMemberReportMonthList(data) {
  return request({
    url: 'system/memberReport/getMonthList',
    method: 'get',
		params: data
  })
}

export function getConversionReportList(data) {
  return request({
    url: 'system/conversionReport/getList',
    method: 'get',
		params: data
  })
}

export function genDayReport(data) {
  return request({
    url: 'system/genDayReport',
    method: 'post',
    data
  })
}

export function genMonthReport(data) {
  return request({
    url: 'system/genMonthReport',
    method: 'post',
    data
  })
}
