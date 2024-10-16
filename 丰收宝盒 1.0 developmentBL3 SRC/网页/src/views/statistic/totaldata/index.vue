<template>
    <div>
        <el-card>
            <el-button
                :type="btnFocus == 1 ? 'primary' : ''"
                @click="
                    () => {
                        btnFocus = 1
                        getOneDayTime()
                        timeValue = ''
                    }
                "
            >
                昨天
            </el-button>
            <el-button
                :type="btnFocus == 2 ? 'primary' : ''"
                @click="
                    () => {
                        btnFocus = 2
                        getWeekTime()
                        timeValue = ''
                    }
                "
            >
                最近一周
            </el-button>
            <el-button
                :type="btnFocus == 3 ? 'primary' : ''"
                @click="
                    () => {
                        btnFocus = 3
                        get30Time()
                        timeValue = ''
                    }
                "
            >
                最近30天
            </el-button>
            <el-date-picker
                @change="
                    () => {
                        ;(btnFocus = 0),
                            (beginTime = timeValue[0]),
                            (endTime = timeValue[1])
                    }
                "
                v-model="timeValue"
                format="YYYY-MM-DD HH:mm:ss"
                value-format="YYYY-MM-DD HH:mm:ss"
                range-separator="至"
                type="datetimerange"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                style="width: 25%; margin-left: 10px"
            />
            <el-text style="margin-left: 20px">
                已选时间:{{ beginTime }} 至 {{ endTime }}
            </el-text>
        </el-card>

        <el-row :gutter="20">
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <MoneyStatistics :data="turnOverData" />
                </el-card>
            </el-col>
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <OrderStatistics :data="ordersData" />
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <UserStatistic :data="userData" />
                </el-card>
            </el-col>
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <Top10 :data="salesLoveData" />
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <SkuTop10 :data="salesSkuData" />
                </el-card>
            </el-col>
            <el-col :span="12" style="margin: 10px 0">
                <el-card style="height: 350px">
                    <SpuTop10 :data="salesSpuData" />
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import MoneyStatistics from './component/moneyStatistic.vue'
import OrderStatistics from './component/orderStatistic.vue'
import UserStatistic from './component/userStatistic.vue'
import Top10 from './component/top10.vue'
import SkuTop10 from '@/views/statistic/totaldata/component/skuTop10.vue'
import SpuTop10 from '@/views/statistic/totaldata/component/spuTop10.vue'
import { onMounted, ref } from 'vue'
import {
    getStaLove,
    getStaOrders,
    getStaSku,
    getStaSpu,
    getStaTurnover,
    getStaUser,
} from '@/api/statistics'
import {
    OrderStaReportVO,
    SalesTop10ReportVO,
    TurnoverStaReportVO,
    UserStaReportVO,
} from '@/api/statistics/type.ts'

let btnFocus = ref(2)
let timeValue = ref()

let ordersData = ref<OrderStaReportVO>({
    dateList: '',
    orderCompletionRate: 0,
    orderCountList: '',
    totalOrderCount: 0,
    validOrderCount: 0,
    validOrderCountList: '',
})

let turnOverData = ref<TurnoverStaReportVO>()

let userData = ref<UserStaReportVO>({
    dateList: '',
    newUserList: '',
    totalUserList: '',
})
let salesSkuData = ref<SalesTop10ReportVO>()
let salesSpuData = ref<SalesTop10ReportVO>()
let salesLoveData = ref<SalesTop10ReportVO>()

const formatDateTime = (date: Date): string => {
    const year = date.getFullYear().toString().padStart(4, '0')
    const month = (date.getMonth() + 1).toString().padStart(2, '0')
    const day = date.getDate().toString().padStart(2, '0')
    const hours = date.getHours().toString().padStart(2, '0')
    const minutes = date.getMinutes().toString().padStart(2, '0')
    const seconds = date.getSeconds().toString().padStart(2, '0')

    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
}

let beginTime = ref('')
let endTime = ref('')

const getWeekTime = () => {
    changeTimeArea(7)
}

const getOneDayTime = () => {
    let todayStart = new Date()
    todayStart.setHours(0, 0, 0, 0)

    let todayEnd = new Date()
    todayEnd.setHours(23, 59, 59, 999)

    // 获取近一周的时间范围
    let lastStart = new Date(todayStart.getTime() - 1 * 24 * 60 * 60 * 1000) // 获取近一周的时间范围
    let lastEnd = new Date(todayEnd.getTime() - 1 * 24 * 60 * 60 * 1000)
    beginTime.value = formatDateTime(lastStart)
    endTime.value = formatDateTime(lastEnd)
}

const changeTimeArea = (day: number) => {
    let todayStart = new Date()
    todayStart.setHours(0, 0, 0, 0)

    let todayEnd = new Date()
    todayEnd.setHours(23, 59, 59, 999)

    console.log('Week Start:', formatDateTime(todayStart))
    console.log('Today End:', formatDateTime(todayEnd))

    // 获取时间范围
    let lastStart = new Date(todayStart.getTime() - day * 24 * 60 * 60 * 1000)
    beginTime.value = formatDateTime(lastStart)
    console.log(beginTime.value)
    endTime.value = formatDateTime(todayEnd)
}

const get30Time = () => {
    changeTimeArea(30)
}

const init = async () => {
    let begin = convertToYYYYMMDD(beginTime.value)
    let end = convertToYYYYMMDD(endTime.value)

    await getOrders(begin, end)
    await getTurnover(begin, end)
    await getUser(begin, end)
    await getLove(begin, end)
    await getSku(begin, end)
    await getSpu(begin, end)
}

const convertToYYYYMMDD = (dateTimeString: string): string => {
    const parts = dateTimeString.split(' ') // 将日期和时间分割成两部分
    const datePart = parts[0] // 提取日期部分
    return datePart
}

const getOrders = async (begin: string, end: string) => {
    let data = await getStaOrders(begin, end)
    if (data.code == 200) {
        ordersData.value = data.data
        return true
    } else {
        return false
    }
}
const getTurnover = async (begin: string, end: string) => {
    let data = await getStaTurnover(begin, end)
    if (data.code == 200) {
        turnOverData.value = data.data
        return true
    } else {
        return false
    }
}
const getUser = async (begin: string, end: string) => {
    let data = await getStaUser(begin, end)
    if (data.code == 200) {
        userData.value = data.data
        return true
    } else {
        return false
    }
}
const getLove = async (begin: string, end: string) => {
    let data = await getStaLove(begin, end)
    if (data.code == 200) {
        salesLoveData.value = data.data
        return true
    } else {
        return false
    }
}
const getSku = async (begin: string, end: string) => {
    let data = await getStaSku(begin, end)
    if (data.code == 200) {
        salesSkuData.value = data.data
        return true
    } else {
        return false
    }
}
const getSpu = async (begin: string, end: string) => {
    let data = await getStaSpu(begin, end)
    if (data.code == 200) {
        salesSpuData.value = data.data
        return true
    } else {
        return false
    }
}

onMounted(() => {
    // 获取今天的时间范围
    getWeekTime()
    init()
})
</script>

<style scoped></style>
