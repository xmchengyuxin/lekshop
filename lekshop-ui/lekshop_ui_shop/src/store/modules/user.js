import { loginByUsername, logout, getUserInfo,phoneLogin, getAllRole, changeRole } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: [],
	menus: [],
  wsId: '',
  userData: {},
  shopData: {},
  rolesList: [],
  rolesId: null,
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_INTRODUCTION: (state, introduction) => {
    state.introduction = introduction
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
	SET_MENUS: (state, menus) => {
	  state.menus = menus
	},
  SET_BRAND_NAME: (state, brandName) => {
    state.brandName = brandName
  },
  SET_BRAND_LOGO: (state, brandLogo) => {
    state.brandLogo = brandLogo
  },
  SET_WS_ID: (state, wsId) => {
    state.wsId = wsId
  },
  SET_ID: (state, id) => {
    state.id = id
  },
  SET_USER_DATA: (state, data) => {
    state.userData = Object.assign({},data)
  },
  SET_SHOP_DATA: (state, data) => {
    state.shopData = Object.assign({},data)
  },
  SET_ROLES_LIST: (state, data) => {
    state.rolesList = data
  },
  SET_ROLESID:(state,id) => {
    state.rolesId = id
  },
}

 const actions = {
    // 用户名登录
    login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        loginByUsername(username, userInfo.password, userInfo.brandId).then(response => {
          const data = response.data
          const token = data.token
          commit('SET_TOKEN', token)
          setToken(token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //忘记密码手机登录
    phoneLogin({ commit }, data) {
      return new Promise((resolve, reject) => {
        phoneLogin(data).then(response => {
          const data = response.data
          const token = data.token;
          commit('SET_TOKEN', token)
          setToken(token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    getUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          // 由于mockjs 不支持自定义状态码只能这样hack
          /* if (!response.data) {
            reject('Verification failed, please login again.')
          } */
          let data = response.data
          if(!data){
            data = {};
          }
          let allPermissions = [];
          allPermissions = allPermissions.concat(data.roles);
          if(data.permissions) {
           allPermissions = allPermissions.concat(data.permissions);
          }
          commit('SET_ROLES', allPermissions)
          data.roles = allPermissions
          commit('SET_USER_DATA',data.member)

          let shopInfo = data.shopInfo;
          if(shopInfo) {
            commit('SET_SHOP_DATA', shopInfo)
            commit('SET_NAME', shopInfo.name)
            commit('SET_AVATAR', shopInfo.logo )
            commit('SET_WS_ID', 'member-'+shopInfo.memberId )
            commit('SET_ID', shopInfo.memberId )
          }

          /* commit('SET_MENUS', data.menus )
          commit('SET_BRAND_NAME', data.brandName )
          commit('SET_BRAND_LOGO', data.brandLogo ) */
          //commit('SET_INTRODUCTION', data.introduction)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    logout({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_NAME', '')
          commit('SET_ROLES', [])
					commit('SET_MENUS', [])
          removeToken()
					resetRouter()
          location.reload()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // remove token
    resetToken({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_ROLES', [])
				commit('SET_MENUS', [])
        removeToken()
        resolve()
      })
    },

  getRolesList({commit,dispatch}) {
      return new Promise(async resolve => {
        await getAllRole().then(res => {
          commit('SET_ROLES_LIST',res.data)
          commit('SET_ROLESID',res.data && res.data.length > 0 ? res.data[0].roleId : null)
          resolve()
        })
      })
    },

    // 动态修改权限
    changeRoles({ commit, dispatch }, roleId) {
      return new Promise(resolve => {
        changeRole({ roleId:roleId }).then(async res => {
            let data = await dispatch('getUserInfo')
            commit('SET_ROLESID',roleId)
            resolve()
            //刷新页面
            router.push(`/dashboard`)
            location.reload()
        })
        resolve()
      })
    },

    // 动态修改权限
    // changeRoles({ commit, dispatch }, role) {
    //   return new Promise(async resolve => {
    //     const token = role + '-token'

    //     commit('SET_TOKEN', token)
    //     setToken(token)

    //     const { roles } = await dispatch('getInfo')

    //     resetRouter()

    //     // generate accessible routes map based on roles
    //     const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

    //     // dynamically add accessible routes
    //     router.addRoutes(accessRoutes)

    //     resolve()
    //   })
    // }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
