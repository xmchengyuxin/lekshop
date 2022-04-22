/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

//三方管理路由配置
const thirdRouter = {
  path: '/third',
  component: Layout,
  redirect: 'noredirect',
  name: 'third',
  meta: {
    title: '三方管理',
    icon: 'lock'
  },
  children: [
    {
      path: 'qiniu',
      component: () => import('@/views/third/qiniu/index'),
      name: 'qiniu',
      meta: { title: '七牛配置', icon:'list', role: ['admin'] }
    },
    {
      path: 'sms',
      component: () => import('@/views/third/sms/index'),
      name: 'sms',
      meta: { title: '短信配置', icon:'list', role: ['admin'] }
    },
    {
      path: 'weixin',
      component: () => import('@/views/third/weixin/index'),
      name: 'weixin',
      meta: { title: '微信配置', icon:'list', role: ['admin'] }
    },
    {
      path: 'weixinTemplate',
      component: () => import('@/views/third/weixin/template'),
      name: 'weixinTemplate',
      meta: { title: '微信模板消息', icon:'list', role: ['admin'] }
    },
    {
      path: 'zfb',
      component: () => import('@/views/third/zfb/index'),
      name: 'zfb',
      meta: { title: '支付宝配置', icon:'list', role: ['admin'] }
    },
    {
      path: 'realnameApi',
      component: () => import('@/views/third/approve/index'),
      name: 'realnameApi',
      meta: { title: '实名认证接口', icon:'list', role: ['admin'] }
    },
  ]
}
export default thirdRouter
