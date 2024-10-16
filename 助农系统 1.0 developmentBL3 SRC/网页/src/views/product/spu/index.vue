<template>
    <div>
        <Category :scene="scene" @search="SearchEvent()" />
        <div v-show="scene == 1">
            <el-card style="margin: 10px 0">
                <el-button
                    type="primary"
                    icon="Plus"
                    @click="addSPU"
                    :disabled="!spuStore.c2Id"
                >
                    添加SPU
                </el-button>
                <el-table border style="margin: 10px 0" :data="SPUPageInfo">
                    <el-table-column
                        type="index"
                        align="center"
                        label="序号"
                        width="100"
                    ></el-table-column>
                    <el-table-column
                        align="center"
                        label="SPU名称"
                        width="180"
                        prop="spuName"
                    >
                        <template #default="{ row }">
                            <div>
                                {{ (row as SpuPageType).name }}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column
                        align="center"
                        label="SPU描述"
                        prop="description"
                        :show-overflow-tooltip="true"
                    >
                        <template #default="{ row }">
                            <div>
                                {{ (row as SpuPageType).description }}
                            </div>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="操作" width="220">
                        <template #default="{ row }">
                            <el-button
                                type="warning"
                                size="small"
                                icon="Edit"
                                title="修改SPU "
                                @click="editSPU(row)"
                            ></el-button>
                            <el-button
                                type="info"
                                size="small"
                                icon="View"
                                title="查看SPU详情"
                                @click="
                                    showSpuInfo((row as SPUInfo).spuId as number)
                                "
                            ></el-button>
                            <el-popconfirm
                                :title="`你确定删除 ${(row as SpuPageType).name}`"
                                @confirm="
                                    deleteSPU(
                                        (row as SpuPageType).spuId as number,
                                    )
                                "
                            >
                                <template #reference>
                                    <el-button
                                        type="danger"
                                        size="small"
                                        icon="Delete"
                                        title="删除SPU"
                                    ></el-button>
                                </template>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
            </el-card>
            <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="[3, 5, 7, 10]"
                :pager-count="5"
                :background="true"
                layout=" prev, pager, next, jumper,->,total, sizes,"
                :total="total"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </div>

        <SPUFORM
            ref="spu"
            v-show="scene == 2"
            @changeScene="changeScene"
        ></SPUFORM>
        <SKUFORM
            ref="sku"
            v-show="scene == 3"
            @changeScene="changeScene"
        ></SKUFORM>

        <el-dialog v-model="isShowSKU" title="SKU属性" style="width: 70vw">
            <template #default>
                <el-row style="margin: 10px 0">
                    <el-col :span="6">名称</el-col>
                    <el-col :span="18">{{ spuDetail.spuName }}</el-col>
                </el-row>
                <el-row style="margin: 10px 0">
                    <el-col :span="6">描述</el-col>
                    <el-col :span="18">
                        <el-input
                            type="textarea"
                            v-model="spuDetail.description"
                            disabled
                        ></el-input>
                    </el-col>
                </el-row>

                <el-row style="margin: 10px 0">
                    <el-col :span="6">价格</el-col>
                    <el-col :span="18">{{ spuDetail.price }}</el-col>
                </el-row>

                <el-row style="margin: 10px 0">
                    <el-col :span="6">折扣</el-col>
                    <el-col :span="18">
                        {{ spuDetail.discount }}
                    </el-col>
                </el-row>

                <el-row style="margin: 10px 0">
                    <el-col :span="6">库存</el-col>
                    <el-col :span="18">
                        {{ spuDetail.storeCount }}
                    </el-col>
                </el-row>

                <el-row style="margin: 10px 0">
                    <el-col :span="6">属性</el-col>
                    <el-col :span="18">
                        <div
                            v-for="(item, index) in spuDetail.attributes"
                            :key="index"
                        >
                            <el-text type="info">{{ item.name }}</el-text>
                            <el-tag
                                style="margin: 0px 5px"
                                v-for="(item1, index) in item.values"
                                :key="index"
                                v-bind:type="tagColor[index % 5].type"
                            >
                                {{ item1 }}
                            </el-tag>
                        </div>
                    </el-col>
                </el-row>
                <el-row style="margin: 10px 0">
                    <el-col :span="6">规格</el-col>
                    <el-col :span="18">
                        <div
                            v-for="(item, index) in spuDetail.specifications"
                            :key="index"
                        >
                            <el-text type="info">{{ item.name }}</el-text>
                            <el-tag style="margin: 0px 5px">
                                {{ item.value }}
                            </el-tag>
                        </div>
                    </el-col>
                </el-row>
                <el-row style="margin: 10px 0">
                    <el-col :span="6">商品图片</el-col>
                    <el-col :span="18">
                        <el-carousel
                            :interval="4000"
                            type="card"
                            height="200px"
                        >
                            <el-carousel-item
                                v-for="item in spuDetail.picture"
                                :key="item"
                            >
                                <el-image
                                    :src="item"
                                    style="height: 100%; width: 100%"
                                ></el-image>
                            </el-carousel-item>
                        </el-carousel>
                    </el-col>
                </el-row>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import Category from './spuCategory.vue'
import SKUFORM from '@/views/product/spu/skuForm.vue'
import SPUFORM from '@/views/product/spu/spuForm.vue'
import { onBeforeUnmount, onMounted, onUnmounted, ref, watch } from 'vue'
import { reqGetSkuInfoBySpuId } from '@/api/product/spu'

import { ElMessage } from 'element-plus'
import type { SPUInfo } from '@/api/product/spu/type.ts'
import useSpuStore from '@/store/modules/spu.ts'
import {
    deleteSpuById,
    getSpuDetailById,
    getSpuPageData,
} from '@/api/production/spu'
import { SPUInfoDetailType, SpuPageType } from '@/api/production/spu/type.ts'

// true 显示数据 false 显示数据修改界面
let scene = ref<number>(1)
let currentPage = ref<number>(1)
let pageSize = ref<number>(3)
let total = ref<number>(0)
let spuStore = useSpuStore()
// SPU分页数据
let SPUPageInfo = ref<SpuPageType[]>([])
// 获取spu组件实例
let spu = ref<any>()
let sku = ref<any>()
// 控制sku列表的显示
let isShowSKU = ref<boolean>(false)
const tagColor = ref<
    { type: 'primary' | 'success' | 'info' | 'warning' | 'danger' }[]
>([
    { type: 'primary' },
    { type: 'success' },
    { type: 'info' },
    { type: 'warning' },
    { type: 'danger' },
])
let spuDetail = ref<SPUInfoDetailType>({
    attributes: [],
    description: 0,
    discount: 0,
    id: 0,
    picture: [],
    price: 0,
    specifications: [],
    spuName: '',
    storeCount: 0,
})

const showSpuInfo = (spuId: number) => {
    getSpuDetail(spuId)
    isShowSKU.value = true
}

const getSpuDetail = async (spuId) => {
    let data = await getSpuDetailById(spuId)
    console.log(data)
    if (data.code == 200) {
        spuDetail.value = data.data
    } else {
        ElMessage.error('获取spu详情失败')
    }
}

const SearchEvent = () => {
    console.log('wohenhao')
    ElMessage.success('search123')
    getSPUPageInfo()
}

const getSPUPageInfo = async () => {
    let result = await getSpuPageData(
        currentPage.value,
        pageSize.value,
        spuStore.c1Id,
        spuStore.c2Id,
        spuStore.name,
    )
    if (result.code == 200) {
        total.value = result.data.total
        SPUPageInfo.value = result.data.records
    } else {
        ElMessage.error('获取SPU分页数据失败')
    }
}

//当前页面发生改变
const handleCurrentChange = () => {
    getSPUPageInfo()
}

// 分页大小发生改变
const handleSizeChange = () => {
    getSPUPageInfo()
}

// 添加SKU属性
const addSKU = (row: SPUInfo) => {
    // 点击添加sku时切换场景
    scene.value = 3
    sku.value.initSkuData(
        spuStore.c1Id as number,
        spuStore.c2Id as number,
        spuStore.c3Id as number,
        row,
    )
}

//转换到 添加SPU页面
const addSPU = () => {
    scene.value = 2
    spu.value.initAddSPU(spuStore.c3Id as number)
}

// 转换到修改SPU页面
const editSPU = (row: SPUInfo) => {
    scene.value = 2
    spu.value.initHasSupData(row.spuId)
}

// 根据spuid获取SKU信息
const getSKUInfoBySpuId = async (spuId: number) => {
    let result = await reqGetSkuInfoBySpuId(spuId)
    if (result.code == 200) {
        skuInfoList.value = result.data
    } else {
        ElMessage.error('获取sku信息失败')
    }
}

const showSKUInfo = async (spuId: number) => {
    await getSKUInfoBySpuId(spuId)
    isShowSKU.value = true
}

// 删除一个SPU属性
const deleteSPU = async (spuId: number) => {
    let result = await deleteSpuById(spuId)
    if (result.code == 200) {
        ElMessage.success('删除成功')
    } else {
        ElMessage.error('删除失败')
    }
    if (currentPage.value != 1) {
        if (total.value % pageSize.value == 1) {
            currentPage.value -= 1
        }
    }
    await getSPUPageInfo()
}

watch(
    () => spuStore.c3Id,
    () => {
        console.log(spuStore.c3Id)
        if (spuStore.c3Id != '' || spuStore.c3Id != null) {
            getSPUPageInfo()
        }
    },
)

// 子组件通知切换场景
const changeScene = (num: number) => {
    scene.value = num
    // 获取全部的 SPU数据
    getSPUPageInfo()
}

onMounted(() => {
    getSPUPageInfo()
})

onUnmounted(() => {
    spuStore.$reset()
})

onBeforeUnmount(() => {
    // 退出界面的时候清空数据
    spuStore.$reset()
})
</script>
<style scoped lang="scss"></style>
