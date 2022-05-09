import { getWebInfo } from '@/api/system'

const state = {
  webName: '',
  logo: '',
  brandList: []
}

const mutations = {
  SET_WEB_NAME: (state, webName) => {
    state.webName = webName
  },
  SET_LOGO: (state, logo) => {
    state.logo = logo
  },
  SET_BRAND_LIST: (state, brandList) => {
    state.brandList = brandList
  }
}

 const actions = {
    // 获取系统信息
    /* getWebInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getWebInfo().then(response => {
          const data = response.data
          commit('SET_WEB_NAME', data.webName)
          commit('SET_LOGO', data.logo)
          commit('SET_BRAND_LIST', data.brandList)
          //commit('SET_INTRODUCTION', data.introduction)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    } */

}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
