/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const shopRouter = {
  path: '/shop',
  component: Layout,
  name: 'shop',
	alwaysShow: true,
  meta: {
    title: '店铺管理',
    icon: 'shop-list'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/member/index/index'),
      name: 'shopList',
      meta: { title: '店铺列表', icon:'list', role: ['admin'] }
    },
    {
      path: 'verify',
      component: () => import('@/views/member/group/index'),
      name: 'verifyShop',
      meta: { title: '新店铺审核', icon:'list', role: ['admin'] }
    },
    {
      path: 'shopService',
      component: () => import('@/views/member/realname/index'),
      name: 'shopService',
      meta: { title: '服务管理', icon:'list', role: ['admin'] }
    },
    {
      path: 'freight',
      component: () => import('@/views/member/bank/index'),
      name: 'freightTemplate',
      meta: { title: '运费模板', icon:'list', role: ['admin'] }
    },
  ]
}
export default shopRouter
