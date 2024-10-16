<template>
    <div class="box6">
        <div class="title">
            <p>热门景区排行</p>
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
            // 主标题
            text: '热门景区排名',
            left: '50%',
            link: 'http://baidu.com',
            textStyle: {
                color: 'yellowgreen',
                fontSize: 20,
            },
            subtext: '各大景区排行',
            subtextStyle: {
                color: 'yellowgreen',
                fontSize: 16,
            },
        },
        xAxis: {
            type: 'category', //图形图标在x轴均匀分布展示
        },
        yAxis: {},
        grid: {
            left: 20,
            bottom: 20,
        },
        series: [
            {
                type: 'bar',
                data: [10, 20, 30],
                //柱状图的文本标签
                label: {
                    show: true,
                    position: 'insideTop',
                    color: 'yellowgreen',
                },
                showBackground: true,
                backgroundStyle: {
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
                itemStyle: {
                    borderRadius: [10, 10, 0, 0],
                    color: function (data: any) {
                        // console.log(data)
                        let color = [
                            'red',
                            'orange',
                            'yellowgreen',
                            'green',
                            'purple',
                            'hotpink',
                            'skyblue',
                        ]
                        return color[data.dataIndex]
                    },
                },
            },
            {
                type: 'line',
            },
        ],
    })
})
</script>

<style scoped lang="scss">
.box6 {
    //margin-top: 10px;
    //width: 100%;
    //height: 100%;
    background: url('../../images/dataScreen-main-cb.png') no-repeat;
    background-size: 90% 100%;
    margin-left: 20px;
    .title {
        margin-left: 15px;
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
        //background: red;
    }
}
</style>
