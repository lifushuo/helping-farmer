<template>
    <div>
        <el-card style="height: 150px; margin: 10px 0px">
            <el-row>
                <span>今日数据</span>
                <el-icon><ArrowRight /></el-icon>
                <el-link @click="router.push('/statistic/total')">
                    查看详情
                </el-link>
            </el-row>
            <el-row style="margin: 10px 0" :justify="'space-between'">
                <el-col :span="4">
                    <el-card style="background-color: #f2f3f5">
                        <el-statistic :value="98500" prefix="￥">
                            <template #title>
                                <div
                                    style="
                                        display: inline-flex;
                                        align-items: center;
                                    "
                                >
                                    营业额
                                    <el-tooltip
                                        effect="dark"
                                        content="今日营业额"
                                        placement="top"
                                    >
                                        <el-icon
                                            style="margin-left: 4px"
                                            :size="12"
                                        >
                                            <Warning />
                                        </el-icon>
                                    </el-tooltip>
                                </div>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card style="background-color: #f5f7fa">
                        <el-statistic :value="98500" title="订单数">
                            <template #prefix>
                                <el-icon><ShoppingCart /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <el-card style="background-color: #f5f7fa">
                        <el-statistic :value="98500">
                            <template #title>
                                <div
                                    style="
                                        display: inline-flex;
                                        align-items: center;
                                    "
                                >
                                    用户数
                                    <el-tooltip
                                        effect="dark"
                                        content="今日营业额"
                                        placement="top"
                                    >
                                        <el-icon
                                            style="margin-left: 4px"
                                            :size="12"
                                        >
                                            <Warning />
                                        </el-icon>
                                    </el-tooltip>
                                </div>
                            </template>
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="4">
                    <span>快给老子去干活</span>
                    <div style="margin: 10px">{{ currentTime }}</div>
                </el-col>
            </el-row>
        </el-card>
        <el-card style="height: 150px; margin: 10px 0px">
            <el-row>
                <span>订单管理</span>
                <el-icon><ArrowRight /></el-icon>
                <el-link @click="gotoOrder">查看详情</el-link>
            </el-row>
            <el-row justify="space-between" style="margin: 10px 0">
                <el-col :span="5">
                    <el-card>
                        <el-statistic
                            title="待接单"
                            :value="100"
                            value-style="color: #F56C6C"
                        >
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic
                            title="待配送"
                            :value="100"
                            value-style="color: #F56C6C"
                        >
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="配送中" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="已完成" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
            </el-row>
        </el-card>
        <el-card style="height: 150px; margin: 10px 0px">
            <el-row>
                <span>商品管理</span>
                <el-icon><ArrowRight /></el-icon>
                <el-link @click="router.push('/product')">查看详情</el-link>
            </el-row>
            <el-row justify="space-between" style="margin: 10px 0">
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="一级分类" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="二级分类" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="SPU" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
                <el-col :span="5">
                    <el-card>
                        <el-statistic title="SKU" :value="100">
                            <template #prefix>
                                <el-icon><User /></el-icon>
                            </template>
                        </el-statistic>
                    </el-card>
                </el-col>
            </el-row>
        </el-card>
        <el-card style="margin: 10px 0px">
            <el-row>
                <span>待处理订单</span>
                <el-icon><ArrowRight /></el-icon>
                <el-link @click="router.push('/orders')">查看详情</el-link>
            </el-row>
            <el-table stripe style="margin-top: 10px" :data="data">
                <el-table-column label="订单号" prop="number"></el-table-column>
                <el-table-column
                    label="用户名"
                    prop="userName"
                ></el-table-column>
                <el-table-column label="状态">
                    <template #default="{ row }">
                        <el-tag :type="row.status == 1 ? 'warning' : 'primary'">
                            {{ row.status == 1 ? '待接单' : '待送货' }}
                        </el-tag>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

let data: { number: number; userName: string; status: number } = [
    {
        number: 123,
        userName: 'John',
        status: 1,
    },
    {
        number: 789,
        userName: 'John232',
        status: 2,
    },
    {
        number: 123,
        userName: '2rdfsdf',
        status: 1,
    },
    {
        number: 156541613,
        userName: 'sdfsdfsdf',
        status: 1,
    },
    {
        number: 123,
        userName: 'John',
        status: 2,
    },
]

let router = useRouter()
let currentTime = ref<string>('')

const gotoOrder = () => {
    router.push('/orders')
}
// 更新当前时间
const updateTime = () => {
    const now = new Date()
    const hours = now.getHours().toString().padStart(2, '0')
    const minutes = now.getMinutes().toString().padStart(2, '0')
    const seconds = now.getSeconds().toString().padStart(2, '0')
    currentTime.value = `${hours}:${minutes}:${seconds}`
}

// 使用定时器每秒更新一次时间
onMounted(() => {
    updateTime() // 立即更新一次时间
    setInterval(updateTime, 1000) // 每秒更新一次时间
})
</script>
<style scoped>
.el-col {
    text-align: center;
}

.countdown-footer {
    margin-top: 8px;
}
</style>
<style scoped></style>
