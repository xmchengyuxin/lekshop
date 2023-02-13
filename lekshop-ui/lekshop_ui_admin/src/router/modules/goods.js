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
      meta: { title: '店铺商品',  role: ['admin'] }
    },
    {
      path: 'publish',
      component: () => import('@/views/goods/platform/publish/add'),
      name: 'publishGoods',
      meta: { title: '添加商品', activeMenu: '/goods/platform' },
      hidden: true
    },
    {
    	path: 'edit/:id(\\d+)',
    	component: () => import('@/views/goods/platform/publish/edit'),
    	name: 'editGoods',
    	meta: { title: '编辑商品', noCache: true, activeMenu: '/goods/platform'},
    	hidden: true
    },
    {
      path: 'platform',
      component: () => import('@/views/goods/platform/index/index'),
      name: 'platformGoodsList',
      meta: { title: '商品库', noCache: true, }
    },
  ]
}
export default goodsRouter
