/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const trendsRouter = {
  path: '/trends',
  component: Layout,
  name: 'trends',
	alwaysShow: true,
  meta: {
    title: '逛逛社区',
    icon: 'theme'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/trends/index/index'),
      name: 'trendsList',
      meta: { title: '种草短视频', noCache: true, icon:'list', role: ['admin'] }
    },
  ]
}
export default trendsRouter
