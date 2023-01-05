/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const pointRouter = {
  path: '/point',
  component: Layout,
  name: 'pointMall',
	alwaysShow: true,
  meta: {
    title: '积分',
    icon: 'example'
  },
  children: [
    {
      path: 'config',
      component: () => import('@/views/point/config/index'),
      name: 'pointConfig',
      meta: { title: '积分配置',  role: ['admin'] }
    },
    {
      path: 'goodsCate',
      component: () => import('@/views/point/cate/index'),
      name: 'pointGoodsCate',
      meta: { title: '积分类目',  role: ['admin'] }
    },
    {
      path: 'goods',
      component: () => import('@/views/point/goods/index'),
      name: 'pointGoodsList',
      meta: { title: '积分商品',  role: ['admin'], noCache: true,  }
    },
    {
      path: 'goods/publish',
      component: () => import('@/views/point/goods/publish/add'),
      name: 'publishPointGoods',
      meta: { title: '发布积分商品', icon:'list' },
      hidden: true
    },
    {
    	path: 'goods/edit/:id(\\d+)',
    	component: () => import('@/views/point/goods/publish/edit'),
    	name: 'editPointGoods',
    	meta: { title: '编辑积分商品', noCache: true, },
    	hidden: true
    },
    {
      path: 'order',
      component: () => import('@/views/point/order/index'),
      name: 'pointOrderList',
      meta: { title: '订单管理', noCache: true, }
    },
  ]
}
export default pointRouter
