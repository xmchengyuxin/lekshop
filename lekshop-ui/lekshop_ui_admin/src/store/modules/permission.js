import { asyncRoutes, constantRoutes } from '@/router'

/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.role) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

/**
 * 根据权限匹配路由
 * @param {array} permission 权限列表（菜单列表）
 * @param {array} asyncRouter 异步路由对象
 */
export function createRouter(item) {
		let accessedRoutes = Object.assign({}, routeFilterByPath(1, item.url));
		if(item.children && item.children.length > 0){
			let r2 = [];
			item.children.forEach(item2 =>{
				let accessedRoutes2 = routeFilterByPath(2, item2.url);
				if(accessedRoutes2 && accessedRoutes2.path){
					if(item2.children && item2.children.length > 0){
						let r3 = [];
						item2.children.forEach(item3 =>{
							let accessedRoutes3 = routeFilterByPath(3, item3.url);
							r3.push(accessedRoutes3);
						})
						accessedRoutes2.children = r3;
					}
					r2.push(accessedRoutes2);
				}
			})
			accessedRoutes.children = r2;
		}
		return accessedRoutes;
}

export function routeFilterByPath(type, path) {
	  let accessedRoute = {}
		if(type == 1){
			for(var i = 0; i < asyncRoutes.length; i++){
				let route = Object.assign({}, asyncRoutes[i]) ;
				if(route.path == path){
					route.children = []
					return route;
				}
			}
		}else if(type == 2){
			let isreturn = false;
			for(var i = 0; i < asyncRoutes.length; i++){
				if(isreturn) return accessedRoute;
				if(asyncRoutes[i].children && asyncRoutes[i].children.length > 0){
					for(var j = 0; j < asyncRoutes[i].children.length; j++){
						if(isreturn) return accessedRoute;
						let route2 = Object.assign({}, asyncRoutes[i].children[j]);
						if(path == (asyncRoutes[i].path+'/'+route2.path)){
							route2.children = [];
							accessedRoute = route2;
							isreturn = true
						}
					}
				}
			}
		}else if(type == 3){
			let isreturn = false;
			for(var i = 0; i < asyncRoutes.length; i++){
				if(isreturn) return accessedRoute;
				if(asyncRoutes[i].children && asyncRoutes[i].children.length > 0){
					let route1 = Object.assign({}, asyncRoutes[i]);
					for(var j = 0; j < route1.children.length; j++){
						if(isreturn) return accessedRoute;
						let route2 = Object.assign({}, route1.children[j]);
						if(route2.children && route2.children.length > 0){
							for(var k = 0; k < route2.children.length; k++){
								if(isreturn) return accessedRoute;
								let route3 = Object.assign({}, route2.children[k]);
								if(path == (route1.path+'/'+route2.path+'/'+route3.path)){
									route3.children = [];
									accessedRoute = route3;
									isreturn = true
								}
							}
						}
					}
				}
			}

		}

		return accessedRoute;
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, menusRoles) {
    return new Promise(resolve => {
			const { roles, menus } = menusRoles;
			let accessedRoutes = [];
			menus.forEach(function(item){
				const router = createRouter(item);
				if(router && router.path && router.path != ''){
					accessedRoutes.push(router);
				}
			})
			accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
