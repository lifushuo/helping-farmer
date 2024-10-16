<template>
    <div class="layout_container">
        <!-- 左侧菜单 -->
        <div
            class="layout_slider"
            :class="{ fold: layOutSettingStore.fold ? true : false }"
        >
            <!-- 页面的Logo -->
            <Logo></Logo>
            <!-- 展示菜单 -->
            <el-scrollbar class="scrollbar">
                <el-menu
                    :collapse="layOutSettingStore.fold"
                    background-color="#001529"
                    text-color="white"
                    :default-active="route.path"
                >
                    <!-- 根据路由动态生成菜单 -->
                    <Menu :menuList="userStore.menuRoutes"></Menu>
                </el-menu>
            </el-scrollbar>
        </div>
        <!-- 顶部导航 -->
        <div
            class="layout_tabbar"
            :class="{ fold: layOutSettingStore.fold ? true : false }"
        >
            <Tabbar></Tabbar>
        </div>
        <!-- 内容展示区域 -->
        <div
            class="layout_main"
            :class="{ fold: layOutSettingStore.fold ? true : false }"
        >
            <Main></Main>
        </div>
    </div>
</template>

<script setup lang="ts">
import Logo from './logo/index.vue'
import Menu from './menu/index.vue'
// 引入顶部tabbar组件
import Tabbar from './tabbar/index.vue'
import Main from './main/index.vue'
// 获取父组件传过来的菜单
import useUserStore from '@/store/modules/user'
import useLayOutSettingStore from '@/store/modules/setting'
import { useRoute } from 'vue-router'

let layOutSettingStore = useLayOutSettingStore()
let userStore = useUserStore()
let route = useRoute()
</script>

<script lang="ts">
export default {
    name: 'Layout',
}
</script>

<style scoped lang="scss">
.layout_container {
    width: 100%;
    height: 100vh;
    // background-color: red;

    .layout_slider {
        width: $base-menu-width;
        height: 100vh;
        background-color: $base-menu-color;
        color: white;
        .scrollbar {
            width: 100%;
            height: calc(100vh - $base-menu-logo-height);
            color: aliceblue;

            .el-menu {
                border-right: none;
            }
        }

        &.fold {
            width: $base-menu-min-width;
            transition: all, 0.3s;
        }
    }
    .layout_tabbar {
        position: fixed;
        width: calc(100% - $base-menu-width);
        height: $base-tabbar-height;
        // background-color: aqua;
        top: 0px;
        left: $base-menu-width;

        &.fold {
            width: calc(100% - $base-menu-min-width);
            left: $base-menu-min-width;
            transition: all, 0.1s;
        }
    }
    .layout_main {
        position: fixed;
        width: calc(100% - $base-menu-width);
        height: calc(100vh - $base-tabbar-height);
        //background-color: yellowgreen;
        //background-color: white;
        left: $base-menu-width;
        top: $base-tabbar-height;
        padding: 20px;
        overflow: auto;
        &.fold {
            width: calc(100% - $base-menu-min-width);
            left: $base-menu-min-width;
            transition: all, 0.3s;
        }
    }
}
</style>
