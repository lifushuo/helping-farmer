<template>
    <div>
        <el-card>
            <el-badge
                v-for="(item, index) in items"
                :key="index"
                :value="item.count"
                :hidden="
                    !(
                        item.type == 2 ||
                        item.type == 3 ||
                        item.type == 4 ||
                        item.count == 0
                    )
                "
                style="margin: 0px 10px"
            >
                <el-button
                    size="large"
                    :type="current === item.type ? 'primary' : 'default'"
                    @click="setCurrent(item.type)"
                >
                    {{ item.label }}
                </el-button>
            </el-badge>
        </el-card>

        <el-card style="margin-top: 15px">
            <!-- 搜索项 -->
            <div class="tableBar">
                <label style="margin-right: 10px">订单号：</label>
                <el-input
                    v-model="orderNumberInput"
                    placeholder="请填写订单号"
                    style="width: 15%"
                    clearable
                />
                <label style="margin-left: 20px">手机号：</label>
                <el-input
                    v-model="phoneInput"
                    placeholder="请填写手机号"
                    style="width: 15%"
                    clearable
                />
                <label style="margin-left: 20px">下单时间：</label>
                <el-date-picker
                    v-model="timeValue"
                    format="YYYY-MM-DD HH:mm:ss"
                    value-format="YYYY-MM-DD HH:mm:ss"
                    range-separator="至"
                    type="datetimerange"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    style="width: 25%; margin-left: 10px"
                />

                <el-button
                    type="primary"
                    style="margin-left: 10px"
                    @click="getOrderSearch"
                >
                    查询
                </el-button>
            </div>
            <el-table
                style="margin-top: 10px"
                border
                stripe
                class="tableBox"
                :data="tableInfoData"
            >
                <el-table-column key="number" prop="number" label="订单号" />
                <el-table-column
                    v-if="[1, 2, 3, 4].includes(current)"
                    key="orderDishes"
                    label="订单"
                >
                    <template #default="{ row }">
                        <div style="white-space: pre-wrap">
                            {{ getOrdersDetailString(row) }}
                        </div>
                    </template>
                </el-table-column>
                >
                <el-table-column v-if="[0].includes(current)" label="订单状态">
                    <template #default="scope">
                        {{
                            getOrderType(
                                (scope.row as OrdersInfoDataType).status,
                            )
                        }}
                    </template>
                </el-table-column>
                <el-table-column
                    v-if="[0, 1, 5, 6].includes(current)"
                    key="consignee"
                    label="用户名"
                    prop="userName"
                    show-overflow-tooltip
                />
                <el-table-column
                    v-if="[0, 1, 5, 6].includes(current)"
                    key="phone"
                    prop="phone"
                    label="手机号"
                />
                <el-table-column
                    show-overflow-tooltip
                    v-if="[0, 2, 3, 4, 5, 6].includes(current)"
                    key="address"
                    prop="address"
                    label="地址"
                    :class-name="current === 6 ? 'address' : ''"
                />
                <el-table-column
                    sortable
                    v-if="[0, 1, 6].includes(current)"
                    key="createTime"
                    label="下单时间"
                    prop="createTime"
                    class-name="orderTime"
                    min-width="110"
                />
                <el-table-column
                    sortable
                    v-if="[6].includes(current)"
                    key="cancelTime"
                    prop="cancelTime"
                    class-name="cancelTime"
                    label="取消时间"
                    min-width="110"
                />
                <el-table-column
                    v-if="[6].includes(current)"
                    key="cancelReason"
                    prop="cancelReason"
                    label="取消原因"
                    class-name="cancelReason"
                    :min-width="[6].includes(current) ? 80 : 'auto'"
                />
                <el-table-column
                    v-if="[5].includes(current)"
                    key="deliveryTime"
                    prop="deliveryTime"
                    label="送达时间"
                />
                <el-table-column
                    v-if="[2, 3, 4].includes(current)"
                    key="estimatedDeliveryTime"
                    prop="estimatedDeliveryTime"
                    label="预计送达时间"
                    min-width="110"
                />
                <el-table-column
                    v-if="[0, 2, 5].includes(current)"
                    key="payMoney"
                    prop="payMoney"
                    label="实收金额"
                    align="center"
                >
                    <!--                <template #default="{ row }">-->
                    <!--                    <span>￥{{ (row.amount.toFixed(2) * 100) / 100 }}</span>-->
                    <!--                </template>-->
                </el-table-column>
                <el-table-column
                    v-if="[1, 2, 3, 4, 5].includes(current)"
                    key="remark"
                    prop="remark"
                    label="备注"
                    align="center"
                />
                <el-table-column
                    v-if="[2, 3, 4].includes(current)"
                    key="tablewareNumber"
                    label="餐具数量"
                    align="center"
                    min-width="80"
                />
                <el-table-column
                    label="操作"
                    align="center"
                    :class-name="current === 0 ? 'operate' : 'otherOperate'"
                    min-width="100"
                >
                    <template #default="{ row }">
                        <!-- <el-divider direction="vertical" /> -->

                        <el-row class="row-bg" justify="center">
                            <el-col :span="6">
                                <div>
                                    <el-button
                                        v-if="row.status === 2"
                                        type="primary"
                                        size="small"
                                        link
                                        @click="orderController(row)"
                                    >
                                        接单
                                    </el-button>
                                    <el-button
                                        v-else-if="row.status === 3"
                                        link
                                        type="primary"
                                        size="small"
                                        @click="orderController(row)"
                                    >
                                        派送
                                    </el-button>
                                    <el-button
                                        v-else-if="row.status === 4"
                                        link
                                        type="info"
                                        size="small"
                                        @click="orderController(row)"
                                    >
                                        完成
                                    </el-button>
                                </div>
                            </el-col>
                            <el-col :span="6">
                                <div class="middle">
                                    <el-button
                                        v-if="row.status === 2"
                                        link
                                        type="danger"
                                        size="small"
                                        @click="
                                            (cancelDialogVisible = true),
                                                (cancelDialogTitle = '拒接'),
                                                (dialogId = row.id)
                                        "
                                    >
                                        拒单
                                    </el-button>
                                    <el-button
                                        v-else-if="
                                            [1, 3, 4, 5].includes(row.status)
                                        "
                                        link
                                        class="delBut"
                                        type="danger"
                                        size="small"
                                        @click="
                                            (cancelDialogVisible = true),
                                                (cancelDialogTitle = '取消'),
                                                (dialogId = row.id)
                                        "
                                    >
                                        取消
                                    </el-button>
                                </div>
                            </el-col>
                            <el-col :span="6">
                                <div class="after">
                                    <el-button
                                        link
                                        type="primary"
                                        class="blueBug non"
                                        size="small"
                                        @click="goDetail(row)"
                                    >
                                        查看
                                    </el-button>
                                </div>
                            </el-col>
                        </el-row>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                style="margin-top: 10px"
                v-if="total > 10"
                v-model:current-page="page"
                :page-sizes="[10, 20, 30, 40]"
                v-model:page-size="pageSize"
                layout="total, prev, pager, next, jumper ,-> ,sizes"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </el-card>

        <!--	    显示具体信息-->
        <!-- 查看弹框部分 -->
        <el-dialog
            title="订单信息"
            v-model="dialogVisible"
            width="53%"
            class="order-dialog"
        >
            <el-scrollbar style="height: 100%">
                <div class="order-top">
                    <div>
                        <div style="display: inline-block">
                            <label style="font-size: 16px">订单号：</label>
                            <div class="order-num">
                                {{ diaForm.number }}
                            </div>
                        </div>
                        <div style="display: inline-block" class="order-status">
                            {{
                                items.filter(
                                    (item) => item.type == diaForm.status,
                                )[0].label
                            }}
                        </div>
                    </div>
                    <p>
                        <label>下单时间：</label>
                        {{ diaForm.createTime }}
                    </p>
                </div>

                <div class="order-middle">
                    <div class="user-info">
                        <div class="user-info-box">
                            <div class="user-name">
                                <label>用户名：</label>
                                <span>{{ diaForm.consignee }}</span>
                            </div>
                            <div class="user-phone">
                                <label>手机号：</label>
                                <span>{{ diaForm.phone }}</span>
                            </div>
                            <div
                                v-if="[2, 3, 4, 5].includes(current)"
                                class="user-getTime"
                            >
                                <label>
                                    {{
                                        current === 5
                                            ? '送达时间：'
                                            : '预计送达时间：'
                                    }}
                                </label>
                                <span>
                                    {{
                                        current === 5
                                            ? diaForm.deliveryTime
                                            : diaForm.estimatedDeliveryTime
                                    }}
                                </span>
                            </div>
                            <div class="user-address">
                                <label>地址：</label>
                                <span>{{ diaForm.address }}</span>
                            </div>
                        </div>
                        <div
                            class="user-remark"
                            :class="{ orderCancel: diaForm.status === 6 }"
                        >
                            <div>
                                {{ diaForm.status === 6 ? '取消原因' : '备注' }}
                            </div>
                            <span>
                                {{
                                    diaForm.status === 6
                                        ? diaForm.cancelReason ||
                                          diaForm.rejectionReason
                                        : diaForm.remark
                                }}
                            </span>
                        </div>
                    </div>

                    <div class="dish-info">
                        <div class="dish-label">菜品</div>
                        <div class="dish-list">
                            <div
                                v-for="(item, index) in diaForm.orderDetails"
                                :key="index"
                                class="dish-item"
                            >
                                <div class="dish-item-box">
                                    <span class="dish-name">
                                        {{ getSkuFullName(item) }}
                                    </span>
                                    <br />
                                    <span class="dish-num"></span>
                                    <el-text size="large">x 1</el-text>
                                </div>
                                <span class="dish-price">
                                    ￥{{
                                        item.skuPrice
                                            ? item.skuPrice.toFixed(2)
                                            : ''
                                    }}
                                </span>
                            </div>
                        </div>
                        <div class="dish-all-amount">
                            <label>菜品小计</label>
                            <span>
                                ￥{{
                                    (
                                        diaForm.payMoney -
                                        6 -
                                        diaForm.payAmount
                                    ).toFixed(2)
                                }}
                            </span>
                        </div>
                    </div>
                </div>

                <div class="order-bottom">
                    <div class="amount-info">
                        <div class="amount-label">费用</div>
                        <div class="amount-list">
                            <div class="dish-amount">
                                <span class="amount-name">商品小计：</span>
                                <span class="amount-price">
                                    ￥{{
                                        (
                                            diaForm.payMoney -
                                            6 -
                                            diaForm.payAmount
                                        ).toFixed(2)
                                    }}
                                </span>
                            </div>
                            <div class="send-amount">
                                <span class="amount-name">派送费：</span>
                                <span class="amount-price">￥{{ 6 }}</span>
                            </div>
                            <div class="package-amount">
                                <span class="amount-name">打包费：</span>
                                <span class="amount-price">
                                    ￥{{
                                        diaForm.payAmount
                                            ? (Number(
                                                  diaForm.payAmount.toFixed(2),
                                              ) *
                                                  100) /
                                              100
                                            : ''
                                    }}
                                </span>
                            </div>
                            <div class="all-amount">
                                <span class="amount-name">合计：</span>
                                <span class="amount-price">
                                    ￥{{
                                        diaForm.payMoney
                                            ? (Number(
                                                  diaForm.payMoney.toFixed(2),
                                              ) *
                                                  100) /
                                              100
                                            : ''
                                    }}
                                </span>
                            </div>
                            <div class="pay-type">
                                <span class="pay-name">支付渠道：</span>
                                <span class="pay-value">
                                    {{
                                        diaForm.payMethod === 1
                                            ? '微信支付'
                                            : '支付宝支付'
                                    }}
                                </span>
                            </div>
                            <div class="pay-time">
                                <span class="pay-name">支付时间：</span>
                                <span class="pay-value">
                                    {{ diaForm.checkoutTime }}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </el-scrollbar>
            <span v-if="diaForm.status !== 6" class="dialog-footer">
                <el-button
                    v-if="diaForm.status == 2"
                    @click="
                        () => {
                            cancelDialogVisible = true
                            dialogId = diaForm.id
                            dialogVisible = false
                        }
                    "
                >
                    拒 单
                </el-button>
                <el-button
                    v-if="diaForm.status == 2"
                    type="primary"
                    @click="dialogFormDetailController"
                >
                    接 单
                </el-button>

                <el-button
                    v-if="[1, 3, 4, 5].includes(diaForm.status)"
                    @click="dialogVisible = false"
                >
                    返 回
                </el-button>
                <el-button
                    v-if="diaForm.status === 3"
                    type="primary"
                    @click="dialogFormDetailController"
                >
                    派 送
                </el-button>
                <el-button
                    v-if="diaForm.status === 4"
                    type="primary"
                    @click="dialogFormDetailController()"
                >
                    完 成
                </el-button>
                <el-button
                    v-if="[1].includes(diaForm.status)"
                    type="primary"
                    @click="
                        () => {
                            dialogVisible = false
                            cancelDialogVisible = true
                        }
                    "
                >
                    取消订单
                </el-button>
            </span>
        </el-dialog>

        <!-- 拒单，取消弹窗 -->
        <el-dialog
            v-model="cancelDialogVisible"
            :title="cancelDialogTitle + '原因'"
            width="42%"
        >
            <el-form label-width="90px">
                <el-form-item :label="cancelDialogTitle + '原因：'">
                    <el-select
                        v-model="cancelReason"
                        :placeholder="'请选择' + cancelDialogTitle + '原因'"
                    >
                        <el-option
                            v-for="(item, index) in cancelDialogTitle === '取消'
                                ? cancelrReasonList
                                : cancelOrderReasonList"
                            :key="index"
                            :label="item.label"
                            :value="item.label"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item
                    v-if="cancelReason == '自定义原因'"
                    label="原因："
                >
                    <el-input
                        v-model.trim="dialogReason"
                        type="textarea"
                        :placeholder="
                            '请填写您' +
                            cancelDialogTitle +
                            '的原因（限20字内）'
                        "
                        maxlength="20"
                    />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button
                        @click="
                            (cancelDialogVisible = false), (cancelReason = '')
                        "
                    >
                        取 消
                    </el-button>
                    <el-button type="primary" @click="confirmCancel">
                        确 定
                    </el-button>
                </span>
            </template>
        </el-dialog>

        <el-dialog>nihao</el-dialog>
    </div>
</template>

<script lang="ts" setup>
import { nextTick, onMounted, ref } from 'vue'
import {
    getOrdersConditionSearch,
    getOrderStatistics,
    putOrderCancel,
    putOrderComplete,
    putOrderConfirm,
    putOrderDelivery,
    putOrderRejection,
} from '@/api/orders'
import { ElMessage } from 'element-plus'
import {
    OrderDetail,
    OrdersInfoDataType,
    OrderStatisticsType,
} from '@/api/orders/type.ts'

let dialogVisible = ref(false)

let phoneInput = ref<string>('')
let orderNumberInput = ref<string>('')
let timeValue = ref('')
let page = ref<number>(1)
let pageSize = ref<number>(10)
let total = ref<number>(5)
let current = ref<number>(0)
let status = ref<number>(0)
let items = ref([
    { label: '全部订单', type: 0, count: 10 },
    { label: '待接单', type: 2, count: 5 },
    { label: '带派送', type: 3, count: 3 },
    { label: '已接单', type: 4, count: 3 },
    { label: '派送中', type: 5, count: 3 },
    { label: '已完成', type: 6, count: 3 },
    { label: '已取消', type: 7, count: 3 },
    { label: '已退款', type: 8, count: 3 },
    { label: '已收货', type: 9, count: 3 },
])

let cancelDialogVisible = ref(false)
let cancelReason = ref<string>('')
let cancelDialogTitle = '取消'
let dialogReason = ref()
let dialogId = ref<number>()

let orderStatistics = ref<OrderStatisticsType>({
    confirmed: 0,
    deliveryInProgress: 0,
    toBeConfirmed: 0,
})

const getOrderCount = async () => {
    let data = await getOrderStatistics()
    if (data.code == 200) {
        orderStatistics.value = data.data
        // 待接单
        items.value[1].count = orderStatistics.value.toBeConfirmed
        // 待派送
        items.value[2].count = orderStatistics.value.confirmed
        // 待接单
        items.value[3].count = orderStatistics.value.deliveryInProgress
    } else {
        ElMessage.error('获取订单数量失败' + data.msg)
    }
}

const dialogFormDetailController = async () => {
    let res = await orderController(diaForm.value)
    console.log(res)
}

const cancelOrderReasonList = [
    {
        value: 1,
        label: '订单量较多，暂时无法接单',
    },
    {
        value: 2,
        label: '菜品已销售完，暂时无法接单',
    },
    {
        value: 3,
        label: '餐厅已打烊，暂时无法接单',
    },
    {
        value: 0,
        label: '自定义原因',
    },
]

const cancelrReasonList = [
    {
        value: 1,
        label: '订单量较多，暂时无法接单',
    },
    {
        value: 2,
        label: '菜品已销售完，暂时无法接单',
    },
    {
        value: 3,
        label: '骑手不足无法配送',
    },
    {
        value: 4,
        label: '客户电话取消',
    },
    {
        value: 0,
        label: '自定义原因',
    },
]

let tableInfoData = ref<OrdersInfoDataType[]>([])
let diaForm = ref<OrdersInfoDataType>({
    address: '',
    addressBookId: 0,
    cancelReason: '',
    cancelTime: '',
    checkoutTime: null,
    consignee: '',
    createTime: '',
    deliveryTime: '',
    discountMoney: 0,
    estimatedDeliveryTime: '',
    id: 0,
    number: '',
    orderDetails: [],
    payAmount: 0,
    payId: 0,
    payMethod: 0,
    payMoney: 0,
    payStatus: 0,
    phone: '',
    rejectionReason: '',
    remark: '',
    status: 0,
    userId: 0,
    userName: '',
})

const goDetail = (row: OrdersInfoDataType) => {
    dialogVisible.value = true
    diaForm.value = row
}

const orderController = async (data: OrdersInfoDataType) => {
    if (data.status == 2) {
        //接单
        let res = await putOrderConfirm(data.id)
        ElMessage.success('接单操作成功')
        if (res.code == 200) {
            data.status = 3
            return true
        } else {
            ElMessage.error('接单操作失败' + res.msg)
        }
    } else if (data.status == 3) {
        //派送
        let res = await putOrderDelivery(data.id)
        if (res.code == 200) {
            data.status = 4
            ElMessage.success('派送操作成功')
            return true
        } else {
            ElMessage.error('派送操作失败' + res.msg)
        }
    } else if (data.status == 4) {
        // 完成
        let res = await putOrderComplete(data.id)
        if (res.code == 200) {
            data.status = 5
            ElMessage.success('完成操作成功')
            return true
        } else {
            ElMessage.error('完成操作失败' + res.msg)
        }
    }
    return false
}

const getSkuFullName = (data: OrderDetail) => {
    let k: string = ''
    data.skuAttrs.forEach((item) => {
        k += item.attrValue
    })
    return (k = data.skuName + ': ' + k)
}

const clearDialog = () => {
    cancelReason.value = ''
    dialogReason.value = ''
}

const confirmCancel = async () => {
    let reason = cancelReason.value
    if (cancelReason.value == '自定义原因') {
        reason = dialogReason.value
    }

    if (reason == '') {
        ElMessage.error('原因不能为空')
        return
    }
    if (cancelDialogTitle == '取消') {
        let data = await putOrderCancel(dialogId.value!, reason)
        if (data.code == 200) {
            ElMessage.success('取消成功')
            cancelDialogVisible.value = false
            clearDialog()
            getOrderSearch()
        } else {
            ElMessage.error('取消失败' + data.msg)
        }
    } else if (cancelDialogTitle == '拒接') {
        let data = await putOrderRejection(dialogId.value!, reason)
        if (data.code == 200) {
            ElMessage.success('拒单成功')
            cancelDialogVisible.value = false
            clearDialog()
            getOrderSearch()
        } else {
            ElMessage.error('拒单失败' + data.msg)
        }
    }
}

const handleCurrentChange = async () => {
    await getOrderSearch()
}

const handleSizeChange = async () => {
    await getOrderSearch()
}

const getOrderType = (status: number): string => {
    let da: { label: string; type: number }[] = [
        { label: '待接单', type: 1 },
        { label: '待派送', type: 2 },
        { label: '已接单', type: 3 },
        { label: '派送中', type: 4 },
        { label: '已完成', type: 5 },
        { label: '已取消', type: 6 },
        { label: '已退款', type: 7 },
        { label: '已收货', type: 8 },
    ]
    return da[status - 1].label
}

const getOrderSearch = async () => {
    console.log('status' + status.value)
    console.log('begin' + timeValue.value[0])
    console.log('end' + timeValue.value[1])
    console.log('number' + orderNumberInput.value)

    let data = await getOrdersConditionSearch(
        page.value,
        pageSize.value,
        status.value,
        phoneInput.value || '',
        orderNumberInput.value ? orderNumberInput.value : '',
        timeValue.value && timeValue.value[0] ? timeValue.value[0] : '',
        timeValue.value && timeValue.value[1] ? timeValue.value[1] : '',
    )
    getOrderCount()
    console.log(data)
    if (data.code == 200) {
        total.value = data.data.total
        tableInfoData.value = data.data.records
    } else {
        ElMessage.error('获取订单信息失败' + data.msg)
    }
}

const setCurrent = (type: number) => {
    current.value = type
    status.value = type
    // 处理点击按钮后的逻辑
    page.value = 1
    getOrderSearch()
}

const getOrdersDetailString = (data: OrdersInfoDataType): string => {
    let s: string = ''
    data.orderDetails.forEach((item) => {
        let k: string = ''
        item.skuAttrs.forEach((item) => {
            k += item.attrValue
        })
        s += item.skuName + ' ' + k + ' * 1\n'
    })
    // console.log(s)
    return s
}

onMounted(async () => {
    await getOrderSearch()
    diaForm.value = tableInfoData.value[0]
    await nextTick()
    console.log(diaForm.value)
})
</script>
<style lang="scss" scoped>
.dashboard {
    &-container {
        margin: 30px;
        // height: 100%;
        min-height: 700px;

        .container {
            background: #fff;
            position: relative;
            z-index: 1;
            padding: 30px 28px;
            border-radius: 4px;
            // min-height: 650px;
            height: calc(100% - 55px);

            .tableBar {
                // display: flex;
                margin-bottom: 20px;
                justify-content: space-between;

                .tableLab {
                    span {
                        cursor: pointer;
                        display: inline-block;
                        font-size: 14px;
                        padding: 0 20px;
                        color: darken(grey, 2%);
                        border-right: solid 1px darken(grey, 4%);
                    }
                }
            }

            .tableBox {
                width: 100%;
                border: 1px solid darken(grey, 5%);
                border-bottom: 0;
            }

            .pageList {
                text-align: center;
                margin-top: 30px;
            }

            //查询黑色按钮样式
            .normal-btn {
                background: #333333;
                color: white;
                margin-left: 20px;
            }
        }

        .hContainer {
            height: auto !important;
        }
    }
}

.search-btn {
    margin-left: 20px;
}

.info-box {
    margin: -15px -44px 20px;

    p {
        display: flex;
        height: 20px;
        line-height: 20px;
        font-size: 14px;
        font-weight: 400;
        color: #666666;
        text-align: left;
        margin-bottom: 14px;

        &:last-child {
            margin-bottom: 0;
        }

        label {
            width: 100px;
            display: inline-block;
            color: #666;
        }

        .des {
            flex: 1;
            color: #333333;
        }
    }
}

.order-top {
    // height: 80px;
    border-bottom: 1px solid #e7e6e6;
    padding-bottom: 26px;
    padding-left: 22px;
    padding-right: 22px;
    // margin: 0 30px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .order-status {
        width: 57.25px;
        height: 27px;
        background: #333333;
        border-radius: 13.5px;
        color: white;
        margin-left: 19px;
        text-align: center;
        line-height: 27px;
    }

    .status3 {
        background: #f56c6c;
    }

    p {
        color: #333;

        label {
            color: #666;
        }
    }

    .order-num {
        font-size: 16px;
        color: #2a2929;
        font-weight: bold;
        display: inline-block;
    }
}

.order-middle {
    .user-info {
        min-height: 140px;
        background: #fbfbfa;
        margin-top: 23px;

        padding: 20px 43px;
        color: #333;

        .user-info-box {
            min-height: 55px;
            display: flex;
            flex-wrap: wrap;

            .user-name {
                flex: 67%;
            }

            .user-phone {
                flex: 33%;
            }

            .user-getTime {
                margin-top: 14px;
                flex: 80%;

                label {
                    margin-right: 3px;
                }
            }

            label {
                margin-right: 17px;
                color: #666;
            }

            .user-address {
                margin-top: 14px;
                flex: 80%;

                label {
                    margin-right: 30px;
                }
            }
        }

        .user-remark {
            min-height: 43px;
            line-height: 43px;
            background: #fffbf0;
            border: 1px solid #fbe396;
            border-radius: 4px;
            margin-top: 10px;
            padding: 6px;
            display: flex;
            align-items: center;

            div {
                display: inline-block;
                min-width: 53px;
                height: 32px;
                background: #fbe396;
                border-radius: 4px;
                text-align: center;
                line-height: 32px;
                color: #333;
                margin-right: 30px;
                // padding: 12px 6px;
            }

            span {
                color: #f2a402;
                line-height: 1.15;
            }
        }

        .orderCancel {
            background: #ffffff;
            border: 1px solid #b6b6b6;

            div {
                padding: 0 10px;
                background-color: #e5e4e4;
            }

            span {
                color: #f56c6c;
            }
        }
    }

    .dish-info {
        // min-height: 180px;
        display: flex;
        flex-wrap: wrap;
        padding: 20px 40px;
        border-bottom: 1px solid #e7e6e6;

        .dish-label {
            color: #666;
        }

        .dish-list {
            flex: 80%;
            display: flex;
            flex-wrap: wrap;

            .dish-item {
                flex: 50%;
                margin-bottom: 14px;
                color: #333;

                .dish-num {
                }

                .dish-item-box {
                    display: inline-block;
                    width: 120px;
                }
            }
        }

        .dish-label {
            margin-right: 65px;
        }

        .dish-all-amount {
            flex: 1;
            padding-left: 92px;
            margin-top: 10px;

            label {
                color: #333333;
                font-weight: bold;
                margin-right: 5px;
            }

            span {
                color: #f56c6c;
            }
        }
    }
}

.order-bottom {
    .amount-info {
        // min-height: 180px;
        display: flex;
        flex-wrap: wrap;
        padding: 20px 40px;
        padding-bottom: 0px;

        .amount-label {
            color: #666;
            margin-right: 65px;
        }

        .amount-list {
            flex: 80%;
            display: flex;
            flex-wrap: wrap;
            color: #333;
            // height: 65px;
            .dish-amount,
            .package-amount,
            .pay-type {
                display: inline-block;
                width: 300px;
                margin-bottom: 14px;
                flex: 50%;
            }

            .send-amount,
            .all-amount,
            .pay-time {
                display: inline-block;
                flex: 50%;
                padding-left: 10%;
            }

            .package-amount {
                .amount-name {
                    margin-right: 14px;
                }
            }

            .all-amount {
                .amount-name {
                    margin-right: 24px;
                }

                .amount-price {
                    color: #f56c6c;
                }
            }

            .send-amount {
                .amount-name {
                    margin-right: 10px;
                }
            }
        }
    }
}
</style>

<style scoped>
.custom-style .el-segmented {
    --el-segmented-item-selected-color: var(--el-text-color-primary);
    --el-segmented-item-selected-bg-color: #ffd100;
    --el-border-radius-base: 16px;
}
</style>
