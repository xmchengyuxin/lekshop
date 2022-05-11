/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const orderRouter = {
  path: '/order',
  component: Layout,
  name: 'order',
	alwaysShow: true,
  meta: {
    title: '交易管理',
    icon: 'order-refund'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/member/index/index'),
      name: 'orderList',
      meta: { title: '订单管理', icon:'list', role: ['admin'] }
    },
    {
      path: 'comment',
      component: () => import('@/views/member/group/index'),
      name: 'commentList',
      meta: { title: '评论管理', icon:'list', role: ['admin'] }
    },
    {
      path: 'refund',
      component: () => import('@/views/member/group/index'),
      name: 'refundList',
      meta: { title: '退款管理', icon:'list', role: ['admin'] }
    },
  ]
}
export default orderRouter
