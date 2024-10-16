<template>
    <div class="box8">
        <div class="title">
            <p>数据统计</p>
            <img src="../../images/dataScreen-title.png" />
        </div>
        <div class="charts" ref="charts"></div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue'

let charts = ref()

onMounted(() => {
    let option = {
        color: ['#F5B01D', '#2DC1B2'],
        legend: {
            //图例形状
            icon: 'squareRatio',
            top: 'center',
            right: '10%',
            show: true,
            orient: 'vertical',
            itemWidth: 18,
            itemHeight: 18,
            itemGap: 30,
            data: ['22级1班', '22级2班'],
            textStyle: {
                fontSize: 20,
                color: 'white',
            },
        },
        radar: {
            // 外圆的位置
            center: ['35%', '50%'],
            // 大小
            radius: '50%',

            textStyle: {
                color: 'white',
                fontSize: 20,
            },

            indicator: [
                { name: '消费', max: 200 },
                { name: '好感', max: 200 },
                { name: '出行', max: 200 },
                { name: '小吃', max: 200 },
                { name: '爱好', max: 200 },
            ],
            axisLine: {
                // 指向外圈文本的分隔线样式
                lineStyle: {
                    color: '#D7E0E9',
                },
            },
        },

        series: [
            {
                type: 'radar',
                data: [
                    {
                        value: [120, 100, 120, 135, 150, 180],
                        name: '22级1班',
                        areaStyle: {
                            color: 'rgba(45,193,178,0.20)',
                        },
                        // 取消拐点
                        symbolSize: 0,
                        // 区域边缘线样式
                        lineStyle: {
                            color: '#2DC1B2',
                            width: 2,
                        },
                    },
                    {
                        value: [142, 130, 100, 135, 150, 180],
                        name: '22级2班',
                        areaStyle: {
                            color: 'rgba(245,176,29,0.40)',
                        },
                        // 取消拐点
                        symbolSize: 0,
                        // 区域边缘线样式
                        lineStyle: {
                            color: '#F5B01D',
                            width: 2,
                        },
                    },
                ],
            },
        ],
    }

    let mychart = echarts.init(charts.value)
    mychart.setOption(option)
})
</script>

<style scoped lang="scss">
.box8 {
    width: 100%;
    height: 100%;
    background: url('../../images/dataScreen-main-cb.png');
    background-size: 100% 100%;

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
        width: 100%;
        height: 100%;
    }
}
</style>
