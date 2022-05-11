/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const logRouter = {
  path: '/log',
  component: Layout,
  redirect: 'noredirect',
  name: 'log',
  meta: {
    title: '操作监控',
    icon: 'message'
  },
  children: [
		{
		  path: 'loginLog',
		  component: () => import('@/views/log/login/index'),
		  name: 'loginLogTable',
		  meta: { title: '登录日志', icon: 'list', role: ['admin'] }
		},
		{
		  path: 'operationLog',
		  component: () => import('@/views/log/operation/index'),
		  name: 'operationLogTable',
		  meta: { title: '操作日志', icon: 'list',  role: ['admin'] }
		},
  ]
}
export default logRouter
