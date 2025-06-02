
import { createApp } from 'vue'
import elPlusIcon from '@/plugins/element-icons/element-icons'
import App from './App.vue'
import router from './router'
import store from './stores'

const app = createApp(App)

app.use(elPlusIcon)

app.use(store)

app.use(router)

app.mount('#app')
