<template>
    <div class="box7">
        <div class="title">
            <p>年度游客对比图</p>
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
    let mychart = echarts.init(charts.value)
    mychart.setOption({
        title: {
            text: '散点图',
            left: '40%',
            textStyle: {
                color: 'white',
            },
        },
        xAxis: {
            type: 'category',
            // show: false,
        },
        yAxis: {
            show: false,
        },
        grid: {
            left: 20,
            top: 30,
            right: 0,
            bottom: 20,
        },
        series: [
            {
                type: 'scatter',
                data: [10, 20, 34, 40],
                symbol: 'circle',
                label: {
                    show: true,
                    position: 'top',
                    color: 'red',
                },
            },
        ],
    })
})
</script>

<style scoped lang="scss">
.box7 {
    width: 100%;
    height: 100%;
    background: url('../../images/dataScreen-main-cb.png');
    background-size: 100% 100%;
    margin: 20px 0px;

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
        height: 85%;
    }
}
</style>
