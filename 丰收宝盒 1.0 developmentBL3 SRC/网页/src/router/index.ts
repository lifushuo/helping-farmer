// 通过vue-router插件实现模板路由配置

import { createRouter, createWebHistory } from 'vue-router'
import { anyRoute, asyncRoute, constantRoute } from './routes'
// 创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: [...constantRoute, ...asyncRoute, ...anyRoute],
    scrollBehavior() {
        return {
            left: 0,
            top: 0,
        }
    },
})

export default router
