/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const promotionRouter = {
  path: '/promotion',
  component: Layout,
  redirect: 'noredirect',
  name: 'promotion',
  alwaysShow: true,
  meta: {
    title: '营销活动',
    icon: 'lock'
  },
  children: [
    {
      path: 'coupon',
      component: () => import('@/views/promotion/coupon/index'),
      name: 'coupon',
      meta: { title: '优惠券', icon:'list', role: ['admin'] }
    },
  ]
}
export default promotionRouter
