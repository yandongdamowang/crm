import { login, logout } from '@/api/login'
import { adminIndexAuthList } from '@/api/common'

import { adminUsersRead } from '@/api/personCenter/personCenter'
import { addAuth, removeAuth } from '@/utils/auth'
import Lockr from 'lockr'

const user = {
  state: {
    userInfo: null, // 用户信息
    // 权限信息
    allAuth: null, // 总权限信息 默认空 调整动态路由
    crm: {}, // 客户管理
    contract: {},
    bi: {}, // 商业智能
    manage: {}, // 管理后台
    oa: {}, // 办公
    project: {}, // 项目管理
    annex: {} // 项目管理
  },

  mutations: {
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo
    },
    SET_ALLAUTH: (state, allAuth) => {
      state.allAuth = allAuth
    },
    SET_CRM: (state, crm) => {
      state.crm = crm
    },
    SET_CONTRACT: (state, contract) => {
      state.contract = contract
    },
    SET_ANNEX: (state, contract) => {
      state.annexRouters = contract
    },
    SET_BI: (state, bi) => {
      state.bi = bi
    },
    SET_MANAGE: (state, manage) => {
      state.manage = manage
    },
    SET_OA: (state, oa) => {
      state.oa = oa
    },
    SET_PROJECT: (state, project) => {
      state.project = project
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      return new Promise((resolve, reject) => {
        login(username, userInfo.password)
          .then(data => {
            data.auth.contract = {
              contract: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              },
              rebate: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              }
            }
            data.auth.annex = {
              annex: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              },
              recycle: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              }
            }
            // console.log(123, data)
            Lockr.set('Admin-Token', data['Admin-Token'])
            Lockr.set('loginUserInfo', data.user)

            Lockr.set('authList', data.auth)

            addAuth(data['Admin-Token'])
            commit('SET_USERINFO', data.user)
            // 权限
            commit('SET_ALLAUTH', data.auth)
            commit('SET_CRM', data.auth.crm)
            // 合同
            commit('SET_CONTRACT', data.auth.contract)
            commit('SET_ANNEX', data.auth.annex)

            commit('SET_BI', data.auth.bi)
            commit('SET_MANAGE', data.auth.manage)
            commit('SET_OA', data.auth.oa)
            commit('SET_PROJECT', data.auth.project)
            resolve(data)
          })
          .catch(error => {
            reject(error)
          })
      })
    },

    // 获取权限
    getAuth({ commit }) {
      return new Promise((resolve, reject) => {
        adminIndexAuthList()
          .then(response => {
            response.data.contract = {
              contact: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              },
              rebate: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              }
            }
            response.data.annex = {
              annex: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              },
              recycle: {
                discard: true,
                read: true,
                transfer: true,
                excelexport: true,
                save: true,
                update: true,
                index: true,
                excelimport: true,
                delete: true
              }
            }
            const data = response.data
            Lockr.set('authList', data)
            commit('SET_ALLAUTH', data)
            commit('SET_CRM', data.crm)
            commit('SET_BI', data.bi)
            commit('SET_MANAGE', data.manage)
            commit('SET_OA', data.oa)
            commit('SET_PROJECT', data.project)
            commit('SET_CONTRACT', data.contract)
            commit('SET_ANNEX', data.annex)
            resolve(data)
          })
          .catch(error => {
            reject(error)
          })
      })
    },

    // 获取用户信息
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        adminUsersRead()
          .then(response => {
            commit('SET_USERINFO', response.data)
            resolve(response)
          })
          .catch(error => {
            reject(error)
          })
      })
    },

    // 登出
    LogOut({ commit }) {
      return new Promise((resolve, reject) => {
        logout()
          .then(() => {
            /** flush 清空localStorage .rm('authKey') 按照key清除 */
            removeAuth()
            resolve()
          })
          .catch(error => {
            reject(error)
          })
      })
    }
  }
}

export default user
