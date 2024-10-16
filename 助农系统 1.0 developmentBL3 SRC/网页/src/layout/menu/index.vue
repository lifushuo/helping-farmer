<template>
    <!-- <h1>我是动态菜单</h1> -->
    <template v-for="item in menuList" :key="item.path">
        <!-- 没有子路由 -->
        <template v-if="!item.children">
            <el-menu-item
                v-if="!item.meta.hidden"
                :index="item.path"
                @click="go(item.path)"
            >
                <el-icon>
                    <component :is="item.meta.icon"></component>
                </el-icon>
                <template #title>
                    <span>{{ item.meta.title }}</span>
                </template>
            </el-menu-item>
        </template>

        <!-- 有子路由但是只有一个子路由 -->
        <template v-if="item.children && item.children.length == 1">
            <el-menu-item
                v-if="!item.children[0].meta.hidden"
                :index="item.children[0].path"
                @click="go(item.children[0].path)"
            >
                <el-icon>
                    <component :is="item.children[0].meta.icon"></component>
                </el-icon>
                <template #title>
                    <span>{{ item.children[0].meta.title }}</span>
                </template>
            </el-menu-item>
        </template>

        <!-- 有子路由且个数大于一个1 -->
        <el-sub-menu
            :index="item.path"
            v-if="item.children && item.children.length > 1"
        >
            <template #title>
                <el-icon>
                    <component :is="item.meta.icon"></component>
                </el-icon>
                <span v-show="!layOutSettingStore.fold">
                    {{ item.meta.title }}
                </span>
            </template>
            <Menu :menuList="item.children"></Menu>
        </el-sub-menu>
    </template>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import useLayOutSettingStore from '@/store/modules/setting'

let layOutSettingStore = useLayOutSettingStore()
defineProps(['menuList'])
let router = useRouter()
// function goRoute(routePath: any) {
//     console.log(routePath)
//     // router.push(routePath)
// }
function go(routePath: any) {
    router.push(routePath)
    // console.log(routePath)
}
</script>
<script lang="ts">
export default {
    // eslint-disable-next-line vue/no-reserved-component-names
    name: 'Menu',
}
</script>

<style scoped lang="scss"></style>
