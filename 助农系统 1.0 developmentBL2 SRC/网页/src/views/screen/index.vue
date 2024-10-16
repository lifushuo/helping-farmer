<template>
    <div class="container">
        <!--	    数据大屏的展示内容-->
        <div class="screen" ref="screen">
            <div class="top"><Top /></div>
            <div class="bottom">
                <div class="left">
                    <Tourist class="tourist" />
                    <Sex class="sex" />
                    <Age class="age" />
                </div>
                <div class="middle">
                    <Map class="map" />
                    <Line class="line" />
                </div>
                <div class="right">
                    <Rank class="rank" />
                    <Year class="year" />
                    <Counter class="counter" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import Top from './components/top/index.vue'
import Tourist from './components/tourist/index.vue'
import Sex from './components/sex/index.vue'
import Age from './components/age/index.vue'
import Map from './components/map/index.vue'
import Line from './components/line/index.vue'
import Rank from './components/rank/index.vue'
import Year from './components/year/index.vue'
import Counter from './components/counter/index.vue'

let screen = ref()

function getScale(w = 1920, h = 1080) {
    const ww = window.innerWidth / w
    const hw = window.innerHeight / h
    return ww < hw ? ww : hw
}

onMounted(() => {
    screen.value.style.transform = `scale(${getScale()}) translate(-50%,-50%)`
})

window.onresize = () => {
    screen.value.style.transform = `scale(${getScale()}) translate(-50%,-50%)`
}
</script>
<style scoped lang="scss">
.container {
    width: 100%;
    height: 100%;
    background: url('./images/bg.png') no-repeat;
    background-size: cover;
}

.screen {
    width: 1920px;
    height: 1080px;
    position: fixed;
    left: 50%;
    top: 50%;
    transform-origin: left top;
    //background: red;
    .top {
        width: 100%;
        height: 40px;
        //background: cyan;
    }
    .bottom {
        display: flex;

        .left {
            flex: 1;
            display: flex;
            height: 1040px;
            flex-direction: column;

            .tourist {
                flex: 1.3;
            }
            .sex {
                flex: 1;
            }
            .age {
                flex: 1;
            }
        }
        .right {
            flex: 1;
        }
        .middle {
            flex: 2;
        }
    }

    .middle {
        display: flex;
        flex-direction: column;

        .map {
            flex: 2;
        }
        .line {
            flex: 1;
            //background: orange;
        }
    }

    .right {
        display: flex;
        flex-direction: column;
        .rank {
            flex: 1;
            //background: red;
        }
        .year {
            flex: 1;
            //background: orange;
        }
        .counter {
            flex: 1;
            //background: #29fcff;
        }
    }
}
</style>
