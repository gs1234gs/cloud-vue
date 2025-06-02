// 导入 Element Plus 的所有图标组件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import type { App } from 'vue'

// 创建图标安装插件
const ElementPlusIcons = {
  install: (app: App) => {
    // 全局注册所有图标组件
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
      app.component(key, component)
    }
  }
}

export default ElementPlusIcons