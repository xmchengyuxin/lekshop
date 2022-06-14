/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const floorRouter = {
  path: '/floor',
  component: Layout,
  redirect: 'noredirect',
  name: 'floor',
  meta: {
    title: '装修商城',
    icon: 'qu'
  },
  children: [
    {
      path: 'mobile',
      component: () => import('@/views/floor/index'),
      name: 'floorMobile',
      meta: { title: '移动端', icon: 'list', role: ['admin'] }
    },
    {
    	path: 'mobile/create',
    	component: () => import('@/views/floor/wap/main'),
    	name: 'createMobile',
    	meta: { title: '添加模板', role: ['admin']},
    	hidden: true
    },
    {
    	path: 'mobile/edit',
    	component: () => import('@/views/floor/wap/main'),
    	name: 'editMobile',
    	meta: { title: '编辑模板', role: [] },
    	hidden: true
    },
  ]
}
export default floorRouter
