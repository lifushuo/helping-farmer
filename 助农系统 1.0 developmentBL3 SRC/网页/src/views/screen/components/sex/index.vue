<template>
    <div class="box1">
        <div class="title">
            <p>男女比例</p>
            <img src="../../images/dataScreen-title.png" />
        </div>
        <div class="sex">
            <div class="man">
                <img src="../../images/man.png" />
            </div>
            <div class="women">
                <img src="../../images/woman.png" />
            </div>
        </div>
        <div class="rate">
            <p>男士58%</p>
            <p>女士42%</p>
        </div>
        <div class="charts" ref="charts"></div>
    </div>
</template>
<script setup lang="ts">
import * as echarts from 'echarts'
import { onMounted, ref } from 'vue'
//

let charts = ref()

onMounted(() => {
    let myChart = echarts.init(charts.value)
    myChart.setOption({
        title: {
            text: '男女比例',
            textStyle: {
                color: 'skyblue',
            },
            left: '40%',
        },
        xAxis: {
            show: false,
            min: 0,
            max: 100,
        },
        yAxis: {
            show: false,
            type: 'category',
        },
        series: [
            {
                type: 'bar',
                data: [60],
                barWidth: 20,
                z: 100,
                itemStyle: {
                    color: 'skyblue',
                    borderRadius: 20,
                },
            },
            {
                type: 'bar',
                data: [100],
                barWidth: 20,
                barGap: '-100%',
                itemStyle: {
                    color: 'pink',
                    borderRadius: 20,
                },
            },
        ],
    })
})
</script>

<style scoped lang="scss">
.box1 {
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
    .sex {
        display: flex;
        justify-content: center;
        .man {
            background: url('../../images/man-bg.png');
            width: 111px;
            height: 115px;
            margin: 20px;
            display: flex;
            justify-content: center;
            img {
                margin-top: 20px;
                height: 95px;
            }
        }
        .women {
            width: 111px;
            height: 115px;
            background: url('../../images/woman-bg.png');
            margin: 20px;
            display: flex;
            justify-content: center;
            img {
                margin-top: 20px;
                height: 95px;
            }
        }
    }
    .rate {
        display: flex;
        justify-content: space-between;
        color: white;
    }
    .charts {
        height: 100px;
    }
}
</style>
