/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const goodsRouter = {
  path: '/goods',
  component: Layout,
  name: 'goods',
	alwaysShow: true,
  meta: {
    title: '商品',
    icon: 'food-list'
  },
  children: [
    {
      path: 'goodsCate',
      component: () => import('@/views/goods/cate/index'),
      name: 'goodsCate',
      meta: { title: '电商类目',  role: ['admin'] }
    },
    {
      path: 'list',
      component: () => import('@/views/goods/index/index'),
      name: 'goodsList',
      meta: { title: '商品列表',  role: ['admin'] }
    },
  ]
}
export default goodsRouter
