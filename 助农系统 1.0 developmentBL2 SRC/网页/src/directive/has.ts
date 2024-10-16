import useUserStore from '@/store/modules/user.ts'

const userStore = useUserStore()
export const isHasButton = (app: any) => {
    app.directive('has', {
        // 代表使用 这个全局自定义指令的DOM|组件挂载完毕时候会执行一次
        mounted(el: any, options: any) {
            if (!userStore.button.includes(options.value)) {
                // console.log(options)
                el.parentNode.removeChild(el)
            }
        },
    })
}
