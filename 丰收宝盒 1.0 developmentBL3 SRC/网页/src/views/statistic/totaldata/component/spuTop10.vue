<template>
    <div class="container">
        <div class="charBox">
            <div id="spuTop10" style="width: 100%; height: 300px"></div>
        </div>
    </div>
</template>

<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, watch } from 'vue'
import { SalesTop10ReportVO } from '@/api/statistics/type.ts'
onMounted(() => {
    init()
})
const props = defineProps(['data'])
const init = () => {
    let option = {
        title: {
            text: 'SPU TOP10',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
            },
        },
        legend: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true,
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01],
        },
        yAxis: {
            type: 'category',
            // data: (props.data as SalesTop10ReportVO).nameList
            //     .split(',')
            //     .reverse(),
            data: ['手机', '华为', 'OPPO', '苹果', '红米'].reverse(),
        },
        series: [
            {
                name: '2012',
                type: 'bar',
                // data: (props.data as SalesTop10ReportVO).numberList
                //     .split(',')
                //     .reverse(),
                data: [500, 400, 300, 200, 100].reverse(),
            },
        ],
    }
    let chartDom = document.getElementById('spuTop10')!
    let myChart = echarts.init(chartDom)
    myChart.setOption(option)
}
watch(
    () => props.data,
    () => {
        console.log(
            (props.data as SalesTop10ReportVO).nameList.split(',').reverse(),
        )
        console.log(
            (props.data as SalesTop10ReportVO).numberList.split(',').reverse(),
        )
        init()
    },
)
</script>
<style scoped></style>
