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
      meta: { title: '运费模板', icon:'promotion-freedelivery', role: ['admin'] }
    },
  ]
}
export default freightRouter
