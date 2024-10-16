<template>
    <div class="box">
        <div class="top">
            <p class="title">实时游客统计</p>
            <p class="bg"></p>
            <p class="right">
                可预约的总量
                <span>215908</span>
                人
            </p>
        </div>
        <div class="number">
            <span v-for="(item, index) in people" :key="index">{{ item }}</span>
        </div>
        <div class="charts" ref="charts"></div>
    </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
import 'echarts-liquidfill'

let people = ref('215908人')
let charts = ref()

onMounted(() => {
    let mycharts = echarts.init(charts.value)
    mycharts.setOption({
        title: {
            text: '水球图',
        },
        //系列：决定你展示什么样的图形图标
        series: {
            type: 'liquidFill',
            data: [0.6, 0.4, 0.2],
            waveAnimation: true,
            animationDuration: 3,
            animationDurationUpdate: 0,
            radius: '100%',
            outline: {
                show: true,
                borderDistance: 8,
                itemStyle: {
                    color: 'none',
                    borderColor: '#294D99',
                    // borderColor: 'red',
                    borderWidth: 8,
                    shadowBlur: 20,
                    shadowColor: 'rgba(0, 0, 0, 0.25)',
                },
            },
        },
    })
})
</script>

<style scoped lang="scss">
.box {
    background: url('../../images/dataScreen-main-lb.png') no-repeat;
    background-size: 100% 100%;
    margin-top: 10px;

    .top {
        margin-right: 20px;
        .title {
            color: white;
            font-size: 20px;
            margin-left: 20px;
            margin-top: 18px;
        }
        .bg {
            margin-top: 10px;
            //background: url(' ../../images/total.png');
            background: url('../../images/dataScreen-title.png') no-repeat;
            height: 7px;
            width: 68px;
            background-size: 100% 100%;
        }
        .right {
            float: right;
            color: white;
            font-size: 20px;

            span {
                color: yellowgreen;
            }
        }
    }

    .number {
        padding: 10px;
        margin-top: 30px;
        display: flex;

        span {
            flex: 1;
            height: 40px;
            text-align: center;
            line-height: 40px;
            //background: red;
            background: url('../../images/total.png') no-repeat;
            background-size: 100% 100%;
            color: #29fcff;
            font-size: 35px;
        }
    }
    .charts {
        width: 100%;
        height: 250px;
        //background: red;
    }
}
</style>
