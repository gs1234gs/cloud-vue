import { fileURLToPath, URL } from 'node:url'

import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueDevTools from 'vite-plugin-vue-devtools'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
// 扩展 ImportMetaEnv 类型以支持自定义环境变量

// https://vite.dev/config/
export default defineConfig(({mode}) =>{
  const env = loadEnv(mode, process.cwd(), '')

return{
  plugins: [
    vue({
      template: {
        compilerOptions: {
          isCustomElement: (tag) => tag === 'dev personal-dialog,',
        },
      },
    }),
    vueJsx(),
    vueDevTools(),
    AutoImport({
      dts: true,
      imports: ['vue'],
      resolvers: [ElementPlusResolver(),
        // Auto import icon components
        // 自动导入图标组件
        IconsResolver({
        }),
      ]
    }),
    Components({
      resolvers: [ElementPlusResolver(),
        // Auto register icon components
        // 自动注册图标组件
        IconsResolver({
          enabledCollections: ['ep'],
        }),
      ],

    }),
    Icons({
      autoInstall: true,
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    // 服务器主机名，如果允许外部访问，可设置为 "0.0.0.0" 也可设置成你的ip地址
    host: '0.0.0.0',
    port: Number(env.VITE_PORT),
    open: true,
    // https: undefined,
    cors: true,

    // 代理跨域（模拟示例）
    proxy: {
      '/api': {
        target: env.VITE_APP_BASE_API, // 代理目标地址
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },

}
})
