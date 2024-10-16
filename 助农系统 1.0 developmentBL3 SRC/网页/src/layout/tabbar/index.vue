<template>
    <div class="tabbar">
        <div class="tabbar_left">
            <Left_tabbar></Left_tabbar>
        </div>
        <!-- 顶部右侧静态 -->
        <div class="tabbar_right">
            <Right_tabbar></Right_tabbar>
        </div>
    </div>
</template>
<script setup lang="ts">
// let layOutSettingStore = useLayOutSettingStore()
import Left_tabbar from './left_tabbar/index.vue'
import Right_tabbar from './right_tabbar/index.vue'
// import useLayOutSettingStore from '@/store/modules/setting.ts'
</script>

<script lang="ts">
export default {
    name: 'Tabbar',
}
</script>
<style scoped lang="scss">
.tabbar {
    width: 100%;
    height: 100%;
    // background-color: red;
    display: flex;
    justify-content: space-between;
    background-image: linear-gradient(
        to right,
        rgb(226, 207, 207),
        rgb(221, 190, 190),
        rgb(224, 181, 181),
        rgb(225, 179, 179)
    );
    .tabbar_left {
        display: flex;
        align-items: center;
        // background-color: aqua;
        margin: 20px;
    }
    .tabbar_right {
        display: flex;
        align-items: center;
        // background-color: aqua;
        margin: 20px;
    }
}
</style>
