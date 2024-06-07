/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const cateRouter = {
  path: '/cate',
  component: Layout,
  redirect: 'noredirect',
  name: 'cate',
  meta: {
    title: '类目管理',
    icon: 'table',
    roles: ['cate']
  },
  children: [
		{
		  path: 'cateList',
		  component: () => import('@/views/goods/cate/index'),
		  name: 'cateList',
		  meta: { title: '类目管理', icon:'table', roles: ['cate:list'] }
		},
  ]
}
export default cateRouter
