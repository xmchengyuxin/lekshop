/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const goodsRouter = {
  path: '/goods',
  component: Layout,
  name: 'goods',
	alwaysShow: true,
  meta: {
    title: '宝贝管理',
    icon: 'food-list'
  },
  children: [
    {
      path: 'publish',
      component: () => import('@/views/member/index/index'),
      name: 'publishGoods',
      meta: { title: '发布宝贝', icon:'list', role: ['admin'] }
    },
    {
      path: 'list',
      component: () => import('@/views/member/group/index'),
      name: 'goodsList',
      meta: { title: '出售中的宝贝', icon:'list', role: ['admin'] }
    },
    {
      path: 'outlist',
      component: () => import('@/views/member/group/index'),
      name: 'goodsOutList',
      meta: { title: '仓库中的宝贝', icon:'list', role: ['admin'] }
    },
  ]
}
export default goodsRouter
