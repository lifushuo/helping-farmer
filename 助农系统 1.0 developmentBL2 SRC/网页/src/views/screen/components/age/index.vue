<template>
    <div class="box2">
        <div class="title">
            <p>年龄比例</p>
            <img src="../../images/dataScreen-title.png" />
        </div>
        <div class="charts" ref="charts"></div>
    </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
let charts = ref()

onMounted(() => {
    let mychart = echarts.init(charts.value)
    mychart.setOption({
        tooltip: {
            trigger: 'item',
        },
        legend: {
            top: '40px',
            right: '30px',
            orient: 'vertical',
            textStyle: {
                color: 'white',
                fontSize: '14px',
            },
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2,
                },
                label: {
                    show: true,
                    position: 'inside',
                    color: 'white',
                },
                // emphasis: {
                //     label: {
                //         show: true,
                //         fontSize: 40,
                //         fontWeight: 'bold',
                //     },
                // },
                labelLine: {
                    show: false,
                },
                data: [
                    { value: 1048, name: '军事' },
                    { value: 735, name: '新闻' },
                    { value: 580, name: '直播' },
                    { value: 484, name: '娱乐' },
                    { value: 300, name: '财经' },
                ],
            },
        ],
        grid: {
            left: 0,
            top: 0,
            right: 0,
            bottom: 0,
        },
    })
})
</script>

<style scoped lang="scss">
.box2 {
    margin-top: 10px;
    width: 100%;
    height: 100%;
    background: url('../../images/dataScreen-main-cb.png') no-repeat;
    background-size: cover;

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
        height: 260px;
        //background: red;
    }
}
</style>
