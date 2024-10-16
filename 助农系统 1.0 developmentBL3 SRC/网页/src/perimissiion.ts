// 路由鉴权 项目中路由能不能访问的权限的设置
// 路由在什么条件下可以被访问
import pinia from '@/store/index.ts'
// 获取用户相关的小仓库的 TOKEN数据 判断用户是否登录
import useUserStore from '@/store/modules/user'
import router from './router'
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
import nprogress from 'nprogress'
// 引入进度条的样式
import 'nprogress/nprogress.css'
import { ElMessage } from 'element-plus'
nprogress.configure({ showSpinner: false })
import setting from '@/setting.ts'

const userStore = useUserStore(pinia)

// console.log(userStore.token)
// 全局守卫：项目当中任意路由的切换都会触发的狗子
// 全局的前置守卫
router.beforeEach(async (to, from, next) => {
    // 访问某一个路由之前的守卫
    nprogress.start()
    // 仅仅是为了取消 from的未引用的报错
    from.path
    const token = userStore.token
    // console.log(token)
    // 获取用户名字
    const username = userStore.username
    // 用户登录判断
    if (token) {
        if (to.path == '/login') {
            next({ path: '/home' })
        } else {
            // 登录成功 访问六个路由
            if (username) {
                // 有用户信息
                next()
            } else {
                // 没有用户信息
                // 发请求获取用户信息
                try {
                    await userStore.userInfo()
                    // 万一：刷新的时候是异步路由，有可能获取到用户信息，异步路由还没加载完毕 出现了空白的效果
                    // next({ ...to })
                    next()
                } catch (error) {
                    // token 过期
                    // 退出登录
                    await userStore.logout()
                    next({ path: '/login', query: { redirect: to.path } })
                    ElMessage.error('超时退出登录')
                }
            }
        }
        // next()
    } else {
        // 用户未登录
        if (to.path == '/login') {
            next()
        } else {
            next({ path: '/login', query: { redirect: to.path } })
        }
    }
    nprogress.done()
})
// 全局的后置守卫

router.beforeEach((to) => {
    document.title = `${setting.title}-${to.meta.title}`
})

// 路由组件 登录 注册 404 首页 数据大屏
// 首页的二级路由 权限管理（三个子路由） 商品管理 （四个子路由）
// 用户未登录 可以访问login 其余六个路由不能登录
// 用户登录成功 不可以访问login 其余路由可以访问
