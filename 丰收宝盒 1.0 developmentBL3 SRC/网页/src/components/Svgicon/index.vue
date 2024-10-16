<template>
    <!-- svg 是图标外层容器节点，内部需要与use标签结合使用 -->
    <svg :style="{ width, height }">
        <!-- xlink-href执行用哪一个图标 属性值 -->
        <use :href="prefix + name" :fill="color"></use>
    </svg>
</template>

<script setup lang="ts">
// import { ref, reactive, toRefs, onMounted } from 'vue'

//接收父组件传递过来的参数
defineProps({
    // xlink:href属性值的前缀
    prefix: {
        type: String,
        default: '#icon-',
    },
    name: String,
    color: {
        type: String,
        default: '',
    },
    width: {
        type: String,
        default: '16px',
    },
    height: {
        type: String,
        default: '16px',
    },
})
</script>

<style scoped lang="less"></style>
