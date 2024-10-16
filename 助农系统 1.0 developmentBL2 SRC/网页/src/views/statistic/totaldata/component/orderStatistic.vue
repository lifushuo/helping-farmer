<template>
    <div class="container">
        <div class="charBox">
            <div id="orderStatistic" style="width: 100%; height: 300px"></div>
        </div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted } from 'vue'

type EChartsOption = echarts.EChartsOption

const option: EChartsOption = {
    title: {
        text: '订单成交量',
    },
    tooltip: {
        trigger: 'axis',
    },
    legend: {
        data: ['接单量', '成交量', '取消量'],
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
            name: '接单量',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [120, 132, 101, 134, 90, 230, 210],
        },
        {
            name: '成交量',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [220, 182, 191, 234, 290, 330, 310],
        },
        {
            name: '取消量',
            type: 'line',
            stack: 'Total',
            smooth: true,
            data: [150, 232, 201, 154, 190, 330, 410],
        },
    ],
}

const init = () => {
    let chartDom = document.getElementById('orderStatistic')!
    let myChart = echarts.init(chartDom)
    myChart.setOption(option)
}

onMounted(() => {
    init()
})
</script>

<style scoped></style>
