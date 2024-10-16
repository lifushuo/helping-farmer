<template>
    <div class="box5">
        <div class="title">
            <p>未来七天游客的数量趋势图</p>
            <img src="../../images/dataScreen-title.png" />
        </div>
        <div class="charts" ref="line"></div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue'

let line = ref()

onMounted(() => {
    let mychart = echarts.init(line.value)
    mychart.setOption({
        title: {
            text: '访问量',
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            splitLine: {
                show: false,
            },
        },
        yAxis: {
            splitLine: {
                show: false,
            },
            axisLine: {
                show: true,
            },
            axisTick: {
                show: true,
            },
        },
        grid: {
            left: 40,
            top: 50,
            right: 10,
            bottom: 40,
        },
        series: [
            {
                type: 'line',
                data: [120, 240, 230, 54, 32, 433, 50],
                smooth: true,
                areaStyle: {
                    color: {
                        type: 'linear',
                        x: 0,
                        y: 0,
                        x2: 0,
                        y2: 1,
                        colorStops: [
                            {
                                offset: 0,
                                color: 'red', // 0% 处的颜色
                            },
                            {
                                offset: 1,
                                color: 'blue', // 100% 处的颜色
                            },
                        ],
                        global: false, // 缺省为 false
                    },
                },
                // legendHoverLink: true,
            },
        ],
    })
})
</script>

<style scoped lang="scss">
.box5 {
    width: 95%;
    height: 100%;
    background: url('../../images/dataScreen-main-cb.png');
    background-size: 100% 100%;
    margin: 0px 20px;

    .title {
        margin-left: 20px;
        margin-top: 15px;
        p {
            color: white;
            font-size: 20px;
            margin-bottom: 10px;
        }
    }
    .charts {
        height: 280px;
        //background: red;
    }
}
</style>
