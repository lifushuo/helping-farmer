<template>
    <!-- 顶部左侧静态 -->
    <el-icon style="margin-right: 10px" @click="changeIcon">
        <component
            :is="layOutSettingStore.fold ? 'Fold' : 'Expand'"
        ></component>
    </el-icon>
    <!-- 左侧面包屑 -->
    <el-breadcrumb separator-icon="ArrowRight">
        <!-- 面包动态展示路由名字和 -->
        <el-breadcrumb-item
            v-for="item in route.matched"
            :key="item.path"
            :to="{ path: item.path }"
        >
            <el-icon>
                <component :is="item.meta.icon"></component>
            </el-icon>
            <span>{{ item.meta.title }}</span>
        </el-breadcrumb-item>
        <!-- <el-breadcrumb-item>用户管理</el-breadcrumb-item> -->

        <!-- <button @click="handler">获取路由</button> -->
    </el-breadcrumb>
</template>

<script setup lang="ts" name="Left_Tabbar">
import useLayOutSettingStore from '@/store/modules/setting.ts'
import { useRoute } from 'vue-router'

let route = useRoute()

// 菜单是否折叠
let layOutSettingStore = useLayOutSettingStore()
function changeIcon() {
    layOutSettingStore.fold = !layOutSettingStore.fold
}
</script>
<script lang="ts">
export default {
    name: 'Left_tabbar',
}
</script>
<style scoped lang="scss"></style>
