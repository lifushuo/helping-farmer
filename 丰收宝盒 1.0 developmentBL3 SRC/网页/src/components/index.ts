import SvgIcon from './Svgicon/index.vue'
import Category from './Category/index.vue'
// 引入element-plus 提供全部图标组件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// console.log(ElementPlusIconsVue)
// 全局对象
const allGloableComponents: any = { SvgIcon, Category }

// 对外暴露插件对象
export default {
    // 这里务必使用install方法
    install(app: any) {
        // 注册项目全部的全局组件
        Object.keys(allGloableComponents).forEach((key) => {
            app.component(key, allGloableComponents[key])
        })
        // 将element-plus提供的图标注册为全局组件
        for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
            app.component(key, component)
        }
    },
}
