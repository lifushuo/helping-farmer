<template>
    <div class="container">
        <div class="charBox">
            <div id="userStatistic" style="width: 100%; height: 300px"></div>
        </div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted } from 'vue'

type EChartsOption = echarts.EChartsOption

const option: EChartsOption = {
    title: {
        text: '用户统计',
    },
    tooltip: {
        trigger: 'axis',
    },
    legend: {
        data: ['用户总数', '新增用户数'],
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
    },
    toolbox: {
        feature: {
            saveAsImage: {},
        },
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
    },
    yAxis: {
        type: 'value',
    },
    series: [
        {
            name: '用户总数',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [120, 132, 101, 134, 90, 230, 210],
        },
        {
            name: '新增用户数',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [220, 182, 191, 234, 290, 330, 310],
        },
    ],
}

const init = () => {
    let chartDom = document.getElementById('userStatistic')!
    let myChart = echarts.init(chartDom)
    myChart.setOption(option)
}

onMounted(() => {
    init()
})
</script>

<style scoped></style>
