/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const orderRouter = {
  path: '/order',
  component: Layout,
  name: 'order',
	alwaysShow: true,
  meta: {
    title: '订单',
    icon: 'order-refund'
  },
  children: [
    {
      path: 'list',
      component: () => import('@/views/order/index/index'),
      name: 'orderList',
      meta: { title: '订单管理',  role: ['admin'] }
    },
    {
      path: 'group',
      component: () => import('@/views/order/group/index'),
      name: 'groupList',
      meta: { title: '拼团订单',  role: ['admin'] }
    },
    {
      path: 'comment',
      component: () => import('@/views/order/comment/index'),
      name: 'commentList',
      meta: { title: '评论管理',  role: ['admin'] }
    },
    {
      path: 'refund',
      component: () => import('@/views/order/refund/index'),
      name: 'refundList',
      meta: { title: '退款管理',  role: ['admin'] }
    },
  ]
}
export default orderRouter
