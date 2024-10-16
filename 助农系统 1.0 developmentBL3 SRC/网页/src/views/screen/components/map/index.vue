<template>
    <div class="box4" ref="map"></div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'
import chinaJSON from './china.json'
let map = ref()

echarts.registerMap('china', chinaJSON)

onMounted(() => {
    let mychart = echarts.init(map.value)
    mychart.setOption({
        geo: {
            map: 'china',
            roam: true, //鼠标的缩放的效果
            left: 40,
            top: 130,
            right: 40,
            bottom: 0,
            label: {
                show: true,
                color: 'white',
                fontSize: '10px',
            },

            itemStyle: {
                // 线性渐变，前四个参数分别是 x0, y0, x2, y2, 范围从 0 - 1，相当于在图形包围盒中的百分比，如果 globalCoord 为 `true`，则该四个值是绝对的像素位置
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
                opacity: 0.8,
            },
            //地图高亮的效果
            emphasis: {
                itemStyle: {
                    color: 'red',
                },
            },
        },
        grid: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
        },
        series: [
            {
                type: 'lines', //航线的数组
                data: [
                    {
                        coords: [
                            [113.280637, 23.125178], // 起点
                            [125.3245, 43.886841], // 终点
                            // 如果 polyline 为 true 还可以设置更多的点
                        ],
                        // 统一的样式设置
                        lineStyle: {
                            color: 'white',
                            width: 10,
                        },
                    },
                ],
                effect: {
                    show: true,
                    symbol: 'path://M721.60256 576.77824l-115.3024-115.33312L921.6 375.4496l-102.4-102.4H417.90464L277.22752 132.38272c-39.99744-39.99744-104.8576-39.99744-144.83456 0-39.99744 39.99744-39.99744 104.79616 0 144.76288l140.66688 140.6976v401.3056L375.5008 921.62048l85.97504-315.37152 115.33312 115.33312-41.40032 151.73632 48.30208 48.30208L704.4096 704.41984 921.6 583.61856l-48.19968-48.19968-151.79776 41.35936z m-96.57344 96.57344L426.52672 474.81856l-85.1968 312.46336-0.03072-397.70112-160.62464-160.69632c-13.30176-13.23008-13.27104-34.93888 0.06144-48.30208a34.16064 34.16064 0 0 1 48.24064 0.06144l160.60416 160.6656h397.73184l-312.4736 85.1968L673.3312 625.0496l-48.30208 48.30208z',
                    color: 'red',
                    symbolSize: '30',
                },
            },
        ],
    })
})
</script>

<style scoped lang="scss"></style>
