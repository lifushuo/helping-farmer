// 小仓库：layout组件相关配置仓库
import { defineStore } from 'pinia'
const useLayOutSettingStore = defineStore('LayOutSettingStore', {
    // 小仓库存储数据的地方
    state: () => {
        return {
            fold: false,
            refresh: false, //控制页面刷新
        }
    },
})

export default useLayOutSettingStore
