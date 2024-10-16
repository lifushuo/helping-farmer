<template>
    <div class="logo">
        <img src="../../../public/vite.svg" />
        <p v-show="!layOutSettingStore.fold">大帅哥平台</p>
    </div>
</template>

<script setup lang="ts">
import useLayOutSettingStore from '@/store/modules/setting.ts'
let layOutSettingStore = useLayOutSettingStore()
</script>
<style scoped lang="scss">
.logo {
    width: 100%;
    height: $base-menu-logo-height;
    color: white;
    display: flex;
    align-items: center;
    img {
        width: 40px;
        height: 40px;
    }
}
</style>
