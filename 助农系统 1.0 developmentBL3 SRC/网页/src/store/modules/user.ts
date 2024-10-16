// 创建用户相关的小仓库
import { defineStore } from 'pinia'
import { reqLogin, reqUserInfo, reqLogout } from '@/api/user/index.ts'
// import { loginForm, loginResponseData } from '@/api/user/type.ts'
import {
    LoginFormData,
    LoginResponseData,
    UserInfoResponseData,
} from '@/api/user/type'
import { UserState } from '../types/type'
// 引入路由(本地路由)
// import { constantRoute, asyncRoute, anyRoute } from "@/router/routes.ts";
// 引入
import { GET_TOKEN, SET_TOKEN, REMOVE_TOKEN } from '@/utils/token'
// import router from "@/router";
// 引入深拷贝的方法
// import cloneDeep from 'lodash/cloneDeep';
// import cloneDeep from 'lodash/cloneDeep';

// function filterRoutes(asyncroute: any, router: any) {
//     return asyncroute.filter((item: any) => {
//         // console.log(item)
//         if (router.includes(item.name)) {
//             if (item.children && item.children.length > 0) {
//                 item.children = filterRoutes(item.children, router);
//             }
//             return true;
//         }
//     });
// }

import { anyRoute, asyncRoute, constantRoute } from '@/router/routes.ts'

const useUserStore = defineStore('UserStore', {
    // 小仓库存储数据地方
    state: (): UserState => {
        return {
            token: GET_TOKEN(),
            menuRoutes: [...constantRoute, ...asyncRoute, ...anyRoute],
            username: '',
            avatar: '',
            button: [],
        }
    },
    // 异步逻辑
    actions: {
        async userLogin(data: LoginFormData) {
            // console.log(data)
            // 登录请求
            const result: LoginResponseData = await reqLogin(data)
            // console.log(result)
            // 登录成功，储存token
            if (result.code === 200) {
                this.token = result.data as string
                // 本地化持久存储一份
                SET_TOKEN(this.token)
                // 保证当前的async函数返回一个成功的promise
                return 'ok'
            } else {
                return Promise.reject(new Error(result.data))
            }
        },
        async userInfo() {
            console.log('userInfo')
            const result: UserInfoResponseData = await reqUserInfo()
            if (result.code === 200) {
                this.username = result.data.username
                this.avatar = result.data.avatar

                // 这里是处理权限的

                // const userRouter = filterRoutes(
                //     cloneDeep(asyncRoute),
                //     result.data.routes
                // );

                // this.menuRoutes = [...constantRoute, ...userRouter, anyRoute]
                // ;[...userRouter, anyRoute].forEach((route) =>
                //     router.addRoute(route)
                // );
                // this.button = result.data.buttons;

                return 'ok'
            } else {
                /* empty */
                return Promise.reject(result.message)
            }
        },
        // 退出登录
        async logout() {
            // 目前没有mock接口，退出登录接口（通知服务器本地用户唯一标识失效）
            const result = await reqLogout()
            if (result.code === 200) {
                this.token = ''
                this.username = ''
                this.avatar = ''
                REMOVE_TOKEN()
                return 'ok'
            } else {
                return Promise.reject(new Error(result.message))
            }
        },
    },
    // 计算属性
    getters: {},
    // 开启自动的本地化存储
    // persist: true,
})

export default useUserStore
