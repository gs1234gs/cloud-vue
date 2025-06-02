import { defineStore } from 'pinia'
import loginApi from '@/api/request'
import  {type loginUserType}  from '@/types/loginType/loginType'
import  {type LoginResponse}  from '@/types/userType/userType'
import { cacheLocal, cacheSession } from '@/utils/cache'
import router from '@/router'
import LOGIN_TOKEN, { PERMISSION_MENUS, USER_INFO, USER_MENUS } from '@/global/constants'
import { getUserInfoById } from '@/api/login'
import { getUserMenusById } from '@/api/login'
import { reactive, ref } from 'vue'
import mapMenusRouts, { mapMenusToPermissions } from '@/utils/map-menus'
import type { RouteRecordRaw } from 'vue-router'


 // 定义 Store，使用 Pinia 的 defineStore 方法
export const useLoginStore = defineStore(LOGIN_TOKEN, () => {

  // 定义状态和操作
      let token = ref('')
      const userInfo : any = ref(cacheLocal.getCache(USER_INFO)|| [])
      let userMenus :any = reactive(cacheLocal.getCache(USER_MENUS)|| [])
      const permsMenus = ref(cacheSession.getCache(PERMISSION_MENUS) || [])
      // 登录账号操作
  const loginAccountAction = async (loginData: loginUserType) => {
      // 发送登录请求
      const response = await loginApi({
        method: 'post',
        url: '/api/login',
        data: loginData
    });
    // 解构响应数据
    const { code, msg, data } = response.data as LoginResponse;
    if (code !== 200) {
      throw new Error(msg || '登录失败');
    }
      // 获取登录用户id
      let userId : number = response.data.data.userId
      //获取token,将 token 存储到 cacheLocal
      token.value = response.data.data.token
      // console.log("token",token.value)
      cacheSession.setCache(LOGIN_TOKEN, token.value)

      // console.log("hello，me",cacheLocal.getCache(LOGIN_TOKEN))
      // 获取用户信息
      const userData : any = await getUserInfoById({
        method: 'get',
        url: `/api/user/list/${userId}`,
      })
      // 将用户信息存储到 cacheLocal
          cacheLocal.setCache(USER_INFO, userData.data.data)
          // Object.assign(userInfo, userData.data.data)
          userInfo.value = userData.data.data;
      // console.log('userInfoResult', userData.data.data)

      // 获取用户菜单
       const Menus : any  = await getUserMenusById(userId)

       userMenus = Menus.data.data;

        permsMenus.value  = mapMenusToPermissions(userMenus)
        cacheSession.setCache(PERMISSION_MENUS, permsMenus.value)
      console.log('permsMenus', permsMenus.value)
      // console.log('userMenus', userMenus)
      // 将用户菜单存储到 localStorage
      cacheLocal.setCache(USER_MENUS, userMenus)
      //路由加载
      const routes = mapMenusRouts(cacheLocal.getCache(USER_MENUS))
      routes.forEach((route) => {
        router.addRoute('main', route)

      })

      // 打印最终路由配置
      // console.log('当前路由树:', router.getRoutes())
      // 在添加完所有路由后,等待路由初始化完成
      await router.isReady()

      //页面跳转(modules)
        await  router.push('/main')
  }
  //防止刷新页面时，路由丢失
  const loadCachePages = () => {
    if(
      cacheSession.getCache(LOGIN_TOKEN)
      && cacheLocal.getCache(USER_INFO) &&
     cacheLocal.getCache(USER_MENUS)
    ){
      //  console.log('加载缓存页面',cacheLocal.getCache(USER_MENUS))
      //  console.log('加载缓存页面',cacheLocal.getCache(LOGIN_TOKEN))
      const routes = mapMenusRouts(cacheLocal.getCache(USER_MENUS))
      routes.forEach((route: RouteRecordRaw) => {
        // 添加路由
        router.addRoute('main', route)
        // 等待路由初始化完成
       router.isReady()
      })
    }
  }
    // 将 state 和 actions 暴露出去，供组件使用
    return {
      loginAccountAction,
      loadCachePages,
      token,
      userInfo,
      userMenus,
      permsMenus
    }
  },
  {
    persist: true ,// 启用持久化
  })

