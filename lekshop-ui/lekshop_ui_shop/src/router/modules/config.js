/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const configRouter = {
  path: '/config',
  component: Layout,
  redirect: 'noredirect',
  name: 'config',
  meta: {
    title: '店铺配置',
    icon: 'order-setting'
  },
  children: [
		{
		  path: 'config',
		  component: () => import('@/views/config/shop/index'),
		  name: 'shopConfig',
		  meta: { title: '店铺配置', icon:'order-setting', role: ['admin'] }
		},
  ]
}
export default configRouter
