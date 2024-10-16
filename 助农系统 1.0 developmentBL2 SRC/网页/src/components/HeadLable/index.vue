<template>
    <div class="HeadLable">
        <span v-if="goback" class="goBack" @click="goBack">
            <ArrowLeftBold style="width: 1em; height: 1em; margin-right: 8px" />
        </span>
        <span v-if="!butList">{{ title }}</span>
        <div v-if="butList">
            <slot />
        </div>
    </div>
</template>

<script lang="ts">
export default {
    name: 'Hamburger',
}
</script>

<script lang="ts" setup>
// import { Component, Prop, Vue } from "vue-property-decorator";
import { useRouter } from 'vue-router'

let router = useRouter()

const props = defineProps({
    goback: {
        type: Boolean,
        default: false,
    },
    butList: {
        type: Boolean,
        default: false,
    },
    title: {
        type: String,
        default: '集团管理',
    },
})

const emits = defineEmits(['toggleClick', 'goBack'])

// const toggleClick = () => {
// 	emits("toggleClick");
// };

const goBack = () => {
    if (props.goback == true) {
        router.back()
    }
}
</script>

<style lang="scss" scoped>
.HeadLable {
    // position: absolute;
    background: #fff;
    //color: #333333;
    height: 64px;
    font-size: 16px;
    // width: 300px;
    padding-left: 22px;
    line-height: 64px;
    font-weight: 700;
    margin-bottom: 15px;
    top: 0px;
    left: 0px;
    opacity: 0;
    animation: opacity 500ms ease-out 800ms forwards;

    .goBack {
        border-right: solid 1px #d8dde3;
        padding-right: 14px;
        margin-right: 14px;
        font-size: 16px;
        //color: #333333;
        cursor: pointer;
        font-weight: 400;

        img {
            position: relative;
            top: 24px;
            margin-right: 5px;
            width: 18px;
            height: 18px;
            float: left;
        }
    }
}

@keyframes opacity {
    0% {
        opacity: 0;
        left: 80px;
    }
    100% {
        opacity: 1;
        left: 0;
    }
}
</style>
