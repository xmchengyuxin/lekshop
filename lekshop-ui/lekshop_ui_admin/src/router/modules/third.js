/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

//三方管理路由配置
const thirdRouter = {
  path: '/third',
  component: Layout,
  redirect: 'noredirect',
  name: 'third',
  meta: {
    title: '三方',
    icon: 'lock'
  },
  children: [
    {
      path: 'qiniu',
      component: () => import('@/views/third/qiniu/index'),
      name: 'qiniu',
      meta: { title: '七牛云配置',  role: ['admin'] }
    },
    {
      path: 'sms',
      component: () => import('@/views/third/sms/index'),
      name: 'sms',
      meta: { title: '短信配置',  role: ['admin'] }
    },
    {
      path: 'weixin',
      component: () => import('@/views/third/weixin/index'),
      name: 'weixin',
      meta: { title: '微信配置',  role: ['admin'] }
    },
    {
      path: 'weixinTemplate',
      component: () => import('@/views/third/weixin/template'),
      name: 'weixinTemplate',
      meta: { title: '微信模板消息',  role: ['admin'] }
    },
    {
      path: 'zfb',
      component: () => import('@/views/third/zfb/index'),
      name: 'zfb',
      meta: { title: '支付宝配置',  role: ['admin'] }
    },
    {
      path: 'realnameApi',
      component: () => import('@/views/third/approve/index'),
      name: 'realnameApi',
      meta: { title: '实名认证接口',  role: ['admin'] }
    },
    {
      path: 'thirdConfig',
      component: () => import('@/views/third/config/index'),
      name: 'thirdConfig',
      meta: { title: '接口切换配置',  role: ['admin'] }
    },
    {
      path: 'interface',
      component: () => import('@/views/third/interface/index'),
      name: 'interface',
      meta: { title: '第三方接口',  role: ['admin'] }
    },
  ]
}
export default thirdRouter
