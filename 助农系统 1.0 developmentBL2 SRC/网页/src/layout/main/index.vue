<template>
    <!-- 路由组件出口位置 -->
    <router-view v-slot="{ Component }">
        <transition name="fade">
            <!-- 渲染layout 一级路由组件的子路由 -->
            <component :is="Component" v-if="flag" />
        </transition>
    </router-view>
</template>

<script setup lang="ts">
import { nextTick, ref, watch } from 'vue'
import useLayOutSettingStore from '@/store/modules/setting'

let layOutSettingStore = useLayOutSettingStore()
// 控制当前组件的销毁和重建
let flag = ref(true)
watch(
    () => {
        return layOutSettingStore.refresh
    },
    () => {
        // 点击刷新 组件销毁
        flag.value = false
        // 当dom更新完毕后 再调用这个方法
        nextTick(() => {
            flag.value = true
        })
        // layOutSettingStore.refresh = false
    },
)
</script>
<script lang="ts">
export default {
    // eslint-disable-next-line vue/no-reserved-component-names
    name: 'Main',
}
</script>
<style scoped lang="scss">
.fade-enter-from {
    opacity: 0;
    // transform: rotate(0deg);
    transform: scale(0);
}
.fade-enter-active {
    transition: all 0.3s;
}
.fade-enter-to {
    opacity: 1;
    // transform: rotate(360deg);
    transform: scale(1);
}
</style>
