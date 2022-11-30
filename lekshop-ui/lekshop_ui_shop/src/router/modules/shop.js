/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const shopRouter = {
  path: '/shop',
  component: Layout,
  name: 'shop',
	alwaysShow: true,
  meta: {
    title: '店铺管理',
    icon: 'shop-list',
    roles: ['shop']
  },
  children: [
    {
      path: 'info',
      component: () => import('@/views/shop/info/index'),
      name: 'shopInfo',
      meta: { title: '店铺资料', icon:'list', roles: ['shop:info'] }
    },
    {
      path: 'source',
      component: () => import('@/views/shop/source/index'),
      name: 'source',
      meta: { title: '店铺素材', icon:'list', roles: ['shop:source'] }
    },
  ]
}
export default shopRouter
