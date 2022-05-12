/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const freightRouter = {
  path: '/freight',
  component: Layout,
  redirect: 'noredirect',
  name: 'freight',
  meta: {
    title: '运费模板',
    icon: 'table'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/shop/freight/index'),
      name: 'freightTemplate',
      meta: { title: '运费模板', icon:'promotion-freedelivery', noCache: true, role: ['admin'] }
    },
    {
    	path: 'add',
    	component: () => import('@/views/shop/freight/add'),
    	name: 'addFreight',
    	meta: { title: '添加运费模板', noCache: true , role: ['admin']},
    	hidden: true
    },
    {
    	path: 'edit/:id(\\d+)',
    	component: () => import('@/views/shop/freight/edit'),
    	name: 'editFreight',
    	meta: { title: '编辑运费模板', noCache: true, role: ['admin'] },
    	hidden: true
    },
  ]
}
export default freightRouter
