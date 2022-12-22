import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axios from 'axios'

import 'element-plus/dist/index.css'


const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
 app.component(key, component)
}

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
// 由vite.config.ts中的server.proxy配置代理自动转化/api为http://boer.ink:5004,因此这里不需要再加任何东西
axios.defaults.baseURL = ''

app.mount('#app')
