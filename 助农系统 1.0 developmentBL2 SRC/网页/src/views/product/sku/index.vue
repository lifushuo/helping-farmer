<template>
    <div>
        <SkuCategory :scene="scene" @search="search" />

        <el-card v-show="scene == 1" style="margin-top: 10px">
            <el-button
                type="primary"
                :disabled="!skuStore.spuId"
                :icon="Plus"
                @click="addSku"
            >
                添加
            </el-button>
            <el-table
                style="margin: 10px 0"
                border
                :data="skuTabelData!.records"
            >
                <el-table-column
                    label="序号"
                    type="index"
                    width="100px"
                    align="center"
                ></el-table-column>
                <el-table-column
                    label="名称"
                    width="150px"
                    align="center"
                    prop="skuName"
                    show-overflow-tooltip
                ></el-table-column>
                <el-table-column
                    label="属性"
                    show-overflow-tooltip
                    align="center"
                >
                    <template #default="{ row }">
                        <div class="flex gap-2">
                            <el-tag
                                style="margin: 5px"
                                v-for="(item, index) in (row as SkuInfo).values"
                                :key="index"
                                :type="tagColor[index % 5].type"
                                round
                            >
                                {{ item }}
                            </el-tag>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="图片" align="center" width="150px">
                    <template #default="{ row }">
                        <el-image
                            style="width: 100px; height: 100px"
                            alt="图片"
                            :src="(row as SkuInfo).picture"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column
                    label="原价"
                    align="center"
                    prop="oldPrice"
                    width="100px"
                ></el-table-column>
                <el-table-column
                    label="现价"
                    align="center"
                    prop="price"
                    width="100px"
                ></el-table-column>
                <el-table-column
                    label="操作"
                    fixed="right"
                    align="center"
                    width="250px"
                >
                    <template #default="{ row }">
                        <el-button
                            :type="
                                (row as SkuInfo).isEffective == 1
                                    ? 'info'
                                    : 'success'
                            "
                            size="small"
                            :icon="
                                (row as SkuInfo).isEffective == 1
                                    ? 'Bottom'
                                    : 'Top'
                            "
                            @click="updateSaleState(row as SkuInfo)"
                        ></el-button>
                        <el-button
                            type="primary"
                            size="small"
                            icon="Edit"
                            @click="editSku((row as SkuInfo).id)"
                        ></el-button>
                        <el-button
                            type="info"
                            size="small"
                            icon="InfoFilled"
                            @click="getSkuInfo((row as SkuInfo).id as number)"
                        ></el-button>

                        <el-popconfirm
                            :title="`你确定删除 ${row.skuName}?`"
                            @confirm="deleteSku((row as SkuInfo).id as number)"
                        >
                            <template #reference>
                                <el-button
                                    type="danger"
                                    size="small"
                                    icon="Delete"
                                ></el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
            <el-drawer v-model="drawer_isshow" direction="rtl">
                <template #header>
                    <h1>查看商品详情</h1>
                </template>
                <template #default>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">名称</el-col>
                        <el-col :span="18">{{ skuInfoDetail.skuName }}</el-col>
                    </el-row>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">描述</el-col>
                        <el-col :span="18">
                            <el-input
                                type="textarea"
                                v-model="skuInfoDetail.spuDescription"
                                disabled
                            ></el-input>
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">原价</el-col>
                        <el-col :span="18">
                            {{ skuInfoDetail.skuOldPrice }}
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">价格</el-col>
                        <el-col :span="18">{{ skuInfoDetail.skuPrice }}</el-col>
                    </el-row>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">spu名称</el-col>
                        <el-col :span="18">
                            {{ skuInfoDetail.spuName }}
                        </el-col>
                    </el-row>
                    <el-row style="margin: 10px 0">
                        <el-col :span="6">spu折扣</el-col>
                        <el-col :span="18">
                            {{ skuInfoDetail.spuDiscount }}
                        </el-col>
                    </el-row>

                    <el-row style="margin: 10px 0">
                        <el-col :span="6">库存</el-col>
                        <el-col :span="18">
                            {{ skuInfoDetail.skuStoreCount }}
                        </el-col>
                    </el-row>

                    <el-row style="margin: 10px 0">
                        <el-col :span="6">属性</el-col>
                        <el-col :span="18">
                            <div
                                v-for="(item, index) in skuInfoDetail.skuValues"
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
                        <el-col :span="6">规格</el-col>
                        <el-col :span="18">
                            <div
                                v-for="(
                                    item, index
                                ) in skuInfoDetail.spuSpecification"
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
                            <el-image
                                :src="skuInfoDetail.skuPicture"
                                style="height: 100%; width: 100%"
                            ></el-image>
                        </el-col>
                    </el-row>
                </template>
            </el-drawer>
        </el-card>

        <SkuForm
            ref="skuFormRef"
            v-show="scene == 2"
            @changeScene="changeScene"
        ></SkuForm>
    </div>
</template>

<script setup lang="ts">
import SkuCategory from '@/views/product/sku/skuCategory.vue'
import SkuForm from '@/views/product/sku/skuForm.vue'
import { ElMessage } from 'element-plus'
import { ref } from 'vue'
import {
    deleteSkuById,
    getSkuDetailById,
    getSKUInfoList,
    postSkuStatus,
} from '@/api/production/sku'
import { useSkuStore } from '@/store/modules/sku.ts'
import { PageData } from '@/api'
import type { SkuInfoDetail, SkuInfo } from '@/api/production/sku/type.ts'
import { Plus } from '@element-plus/icons-vue'

let scene = ref<number>(1)
let drawer_isshow = ref<boolean>(false)
let skuInfoDetail = ref<SkuInfoDetail>({
    id: 0,
    skuName: '',
    skuOldPrice: 0,
    skuPicture: '',
    skuPrice: 0,
    skuStoreCount: 0,
    skuValues: [],
    spuDescription: '',
    spuDiscount: 0,
    spuId: 0,
    spuName: '',
    spuSpecification: [],
})
const skuStore = useSkuStore()
let skuFormRef = ref()
let skuTabelData = ref<PageData<SkuInfo>>({ records: [], total: 0 })
const tagColor = ref<
    { type: 'primary' | 'success' | 'info' | 'warning' | 'danger' }[]
>([
    { type: 'primary' },
    { type: 'success' },
    { type: 'info' },
    { type: 'warning' },
    { type: 'danger' },
])

const addSku = () => {
    scene.value = 2
    skuFormRef.value.initSkuData()
}
const editSku = (skuId: number) => {
    scene.value = 2
    skuFormRef.value.initSkuData(skuId)
}

const changeScene = (s: number) => {
    scene.value = s
}

const search = async () => {
    if (skuStore.spuId) {
        let result = await getSKUInfoList(skuStore.spuId as number)
        console.log(result.data)
        skuTabelData.value = result.data
    } else {
        ElMessage.error('请先选择SPU')
    }
}

const getSKUPageData = async () => {
    if (skuStore.spuId) {
        let result = await getSKUInfoList(skuStore.spuId as number)
        console.log(result.data)
        skuTabelData.value = result.data
    } else {
        ElMessage.error('请先选择SPU')
    }
}

const deleteSku = async (skuId: number) => {
    let result = await deleteSkuById(skuId)
    if (result.code == 200) {
        ElMessage.success('删除成功')
    } else {
        ElMessage.error('删除失败')
    }
}

const updateSaleState = async (sku: SkuInfo) => {
	console.log(sku)
    if (sku.isEffective == 1) {
        // 当前是上架
        let result = await postSkuStatus(sku.id as number, 0)
        if (result.code == 200) {
            ElMessage.success('下架成功')
        } else {
            ElMessage.error('下架失败')
        }
    } else {
        let result = await postSkuStatus(sku.id as number, 1)
        if (result.code == 200) {
            ElMessage.success('上架成功')
        } else {
            ElMessage.error('上架失败')
        }
    }

    await getSKUPageData()
}

const getSkuInfo = async (skuId: number) => {
    if (skuId) {
        let result = await getSkuDetailById(skuId)
        console.log(result)
        if (result.code == 200) {
            // console.log(result)
            skuInfoDetail.value = result.data
            drawer_isshow.value = true
        }
    }
}
</script>
<style scoped lang="scss">
.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
    text-align: center;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
</style>
