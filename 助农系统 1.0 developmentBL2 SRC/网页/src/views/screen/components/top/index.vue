<template>
    <div class="top">
        <div class="left">
            <span class="ltb" @click="goHome">首页</span>
        </div>
        <div class="center">
            <div class="title">智慧旅游可视化大数据平台</div>
        </div>
        <div class="right">
            <span class="rbtn">统计报告</span>
            <span class="time">当前时间为：{{ time }}</span>
        </div>
    </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import moment from 'moment'
import { onBeforeUnmount, onMounted, ref } from 'vue'

let router = useRouter()

// 存储当前时间
let time = ref(moment().format('YYYY年MM月DD日 HH:mm:ss'))
let timer = ref(0)

const goHome = () => {
    router.push('/home')
}

onMounted(() => {
    timer.value = setInterval(() => {
        time.value = moment().format('YYYY年MM月DD日 HH:mm:ss')
    }, 1000)
})

onBeforeUnmount(() => {
    clearInterval(timer.value)
})
</script>

<style scoped lang="scss">
.top {
    width: 100%;
    height: 40px;
    display: flex;
    .left {
        flex: 1.5;
        background: url('../../images/dataScreen-header-left-bg.png');
        background-size: cover;

        .ltb {
            width: 150px;
            height: 40px;
            float: right;
            background: url('../../images/dataScreen-header-btn-bg-l.png');
            background-size: cover;
            line-height: 40px;
            color: #29fcff;
            text-align: center;
            font-size: 20px;
        }
    }
    .right {
        flex: 1.5;
        background: url('../../images/dataScreen-header-right-bg.png');
        background-size: cover;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .rbtn {
            width: 150px;
            height: 40px;
            float: right;
            background: url('../../images/dataScreen-header-btn-bg-r.png');
            line-height: 40px;
            color: #29fcff;
            text-align: center;
            font-size: 20px;
            background-size: cover;
        }
        .time {
            color: #29fcff;
            margin-right: 5px;
        }
    }
    .center {
        flex: 2;

        .title {
            width: 100%;
            height: 74px;
            background: url('../../images/dataScreen-header-center-bg.png');
            background-size: 100% 100%;
            text-align: center;
            line-height: 74px;
            color: #29fcff;
            font-size: 35px;
        }
    }
}
</style>
