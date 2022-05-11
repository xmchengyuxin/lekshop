import { loginByUsername, logout, getUserInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  name: '',
  avatar: '',
  introduction: '',
  roles: [],
	menus: [],
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

    // 获取用户信息
    getUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token).then(response => {
          // 由于mockjs 不支持自定义状态码只能这样hack
          if (!response.data) {
            reject('Verification failed, please login again.')
          }
          const data = response.data

          data.roles = ['admin']
          commit('SET_ROLES', data.roles)
          /* if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
          } else {
            reject('getInfo: roles must be a non-null array!')
          } */

          commit('SET_NAME', data.nickname)
          commit('SET_AVATAR', data.headImg )
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
          commit('SET_ROLES', [])
					commit('SET_MENUS', [])
          removeToken()
					resetRouter()
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

    // 动态修改权限
    changeRoles({ commit, dispatch }, role) {
      return new Promise(async resolve => {
        const token = role + '-token'

        commit('SET_TOKEN', token)
        setToken(token)

        const { roles } = await dispatch('getInfo')

        resetRouter()

        // generate accessible routes map based on roles
        const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

        // dynamically add accessible routes
        router.addRoutes(accessRoutes)

        resolve()
      })
    }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
