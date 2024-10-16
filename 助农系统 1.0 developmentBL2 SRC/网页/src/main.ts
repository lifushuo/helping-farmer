import { createApp } from 'vue'

import 'element-plus/dist/index.css'
import 'virtual:svg-icons-register'
import '@/styles/index.scss'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
// 配置 Element-plus 国际化
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
//@ts-expect-error
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import globalComponent from '@/components'
import pinia from '@/store/index.ts'
import './perimissiion'
//暗黑模式需要的样式
import 'element-plus/theme-chalk/dark/css-vars.css'
import { isHasButton } from '@/directive/has.ts'
// import useUserStore from '@/store/modules/user.ts'
const app = createApp(App)
// 引入自定义插件对象：注册整个项目全局组件
app.use(globalComponent)
app.use(ElementPlus, {
    locale: zhCn,
})
// const userStore = useUserStore()
app.use(pinia)
// await userStore.userInfo()
app.use(router)
// 安装仓库
isHasButton(app)
app.mount('#app')
