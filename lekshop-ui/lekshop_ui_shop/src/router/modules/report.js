/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const reportRouter = {
  path: '/report',
  component: Layout,
  redirect: 'noredirect',
  name: 'report',
  meta: {
    title: '报表统计',
    icon: 'search'
  },
  children: [
		{
		  path: 'shopReport',
		  component: () => import('@/views/report/shop/index'),
		  name: 'shopReport',
		  meta: { title: '店铺营业', icon: 'list', role: ['admin'] }
		},
		{
		  path: 'goodsReport',
		  component: () => import('@/views/report/goods/index'),
		  name: 'goodsReport',
		  meta: { title: '商品统计', icon: 'list',  role: ['admin'] }
		},
  ]
}
export default reportRouter
