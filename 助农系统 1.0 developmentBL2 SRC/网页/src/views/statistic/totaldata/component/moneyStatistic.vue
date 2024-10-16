<template>
    <div class="container">
        <div class="charBox">
            <div id="moneyStatistic" style="width: 100%; height: 300px"></div>
        </div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, watch } from 'vue'
import { TurnoverStaReportVO } from '@/api/statistics/type.ts'

const props = defineProps(['data'])

watch(
    () => props.data,
    () => {
        init()
    },
)

onMounted(() => {
    init()
})

const init = () => {
    type EChartsOption = echarts.EChartsOption

    let option: EChartsOption = {
        title: {
            text: '营业额统计',
        },
        tooltip: {
            trigger: 'axis',
        },
        legend: {
            data: ['成交额'],
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
            // data: (props.data as TurnoverStaReportVO).dateList.split(','),
            data: [
                '2024-05-15',
                '2024-05-16',
                '2024-05-17',
                '2024-05-18',
                '2024-05-19',
                '2024-05-20',
                '2024-05-21',
            ],
        },
        yAxis: {
            type: 'value',
        },
        series: [
            {
                name: '成交额',
                type: 'line',
                stack: 'Total',
                smooth: true,
                // data: (props.data as TurnoverStaReportVO).turnoverList.split(
                // ',',
                // ),
                data: [10, 9, 8, 8, 10, 1, 8],
            },
        ],
    }

    let chartDom = document.getElementById('moneyStatistic')!
    let myChart = echarts.init(chartDom)
    myChart.setOption(option)
}
</script>

<style scoped></style>
