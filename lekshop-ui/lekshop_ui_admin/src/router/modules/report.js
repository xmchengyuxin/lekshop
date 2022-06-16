/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const reportRouter = {
  path: '/report',
  component: Layout,
  redirect: 'noredirect',
  name: 'report',
  meta: {
    title: '报表',
    icon: 'search'
  },
  children: [
    {
      path: 'platformReport',
      component: () => import('@/views/report/platform/index'),
      name: 'platformReport',
      meta: { title: '平台统计',  role: ['admin'] }
    },
		{
		  path: 'shopReport',
		  component: () => import('@/views/report/shop/index'),
		  name: 'shopReport',
		  meta: { title: '店铺营业',  role: ['admin'] }
		},
		{
		  path: 'goodsReport',
		  component: () => import('@/views/report/goods/index'),
		  name: 'goodsReport',
		  meta: { title: '商品统计',   role: ['admin'] }
		},
    {
      path: 'memberReport',
      component: () => import('@/views/report/member/index'),
      name: 'memberReport',
      meta: { title: '顾客人数',   role: ['admin'] }
    },
    {
      path: 'conversionReport',
      component: () => import('@/views/report/conversion/index'),
      name: 'conversionReport',
      meta: { title: '实际转化',   role: ['admin'] }
    },
  ]
}
export default reportRouter
