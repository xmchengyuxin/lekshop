/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const goodsRouter = {
  path: '/goods',
  component: Layout,
  name: 'goods',
	alwaysShow: true,
  meta: {
    title: '宝贝管理',
    icon: 'food-list',
    roles: ['goods']
  },
  children: [
    {
      path: 'publish',
      component: () => import('@/views/goods/publish/add'),
      name: 'publishGoods',
      meta: { title: '发布宝贝', icon:'list', roles: ['goods:publish'] }
    },
    {
    	path: 'edit/:id(\\d+)',
    	component: () => import('@/views/goods/publish/edit'),
    	name: 'editGoods',
    	meta: { title: '编辑宝贝', noCache: true, activeMenu: '/goods/list'},
    	hidden: true
    },
    {
      path: 'list',
      component: () => import('@/views/goods/index/index'),
      name: 'goodsList',
      meta: { title: '出售中的宝贝', noCache: true, icon:'list', roles: ['goods:sell'] }
    },
    {
      path: 'outlist',
      component: () => import('@/views/goods/index/index'),
      name: 'goodsOutList',
      meta: { title: '仓库中的宝贝', noCache: true, icon:'list', roles: ['goods:undercarriage'] }
    },
  ]
}
export default goodsRouter
