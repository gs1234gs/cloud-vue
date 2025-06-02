// src/stores/index.ts
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import { useLoginStore } from './modules/login/login'
import type { App } from 'vue'
const pinia = createPinia()
const registerStore = (app: App<Element>) => {
  // 注册pinia
  pinia.use(piniaPluginPersistedstate)
  // 使用 Pinia 状态管理库（需在路由之前注册）
  app.use(pinia)

  // 加载缓存的菜单页面
  const useUserStore = useLoginStore()
  useUserStore.loadCachePages()
}

export default registerStore
