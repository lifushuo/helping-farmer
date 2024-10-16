<template>
    <el-card style="margin: 10px 0">
        <el-form label-width="100px">
            <el-form-item label="sku名称">
                <el-input
                    v-model="skuData.skuName"
                    placeholder="SKU名称"
                ></el-input>
            </el-form-item>
            <el-form-item label="价格(元)">
                <el-input
                    v-model="skuData.price"
                    placeholder="价格(元)"
                    type="number"
                ></el-input>
            </el-form-item>
            <el-form-item label="重量(克)">
                <el-input
                    v-model="skuData.weight"
                    placeholder="重量(克)"
                    type="number"
                ></el-input>
            </el-form-item>
            <el-form-item label="sku描述">
                <el-input
                    v-model="skuData.skuDesc"
                    placeholder="sku描述"
                    type="textarea"
                ></el-input>
            </el-form-item>
            <el-form-item label="平台属性">
                <el-form :inline="true" label-width="100px">
                    <el-form-item
                        v-for="item in attrInfoList"
                        :key="item.id as number"
                        :label="item.attrName as string"
                        style="width: 200px"
                    >
                        <el-select v-model="item.idandValue">
                            <el-option
                                v-for="item1 in item.attrValueList"
                                :key="item1.id"
                                :label="item1.valueName"
                                :value="`${item1.id}:${item1.valueName}`"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </el-form-item>
            <el-form-item label="销售属性">
                <el-form :inline="true">
                    <el-form-item
                        label-width="100px"
                        v-for="item2 in spuSaleAttrValueList"
                        :key="item2.id as number"
                        :label="item2.saleAttrName as string"
                        style="width: 200px"
                    >
                        <el-select v-model="item2.idandValue">
                            <el-option
                                v-for="item3 in item2.spuSaleAttrValueList"
                                :key="item3.id as number"
                                :label="item3.saleAttrValueName as string"
                                :value="`${item3.baseSaleAttrId}:${item3.id}`"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </el-form-item>
            <el-form-item label="照片名称">
                <el-table ref="table" border :data="spuImage">
                    <el-table-column
                        width="80px"
                        label="序号"
                        type="selection"
                        align="center"
                    ></el-table-column>
                    <el-table-column label="图片" align="center">
                        <template #default="{ row }">
                            <el-image
                                style="height: 100px"
                                :src="(row as SPUImage).imgUrl as string"
                                :alt="(row as SPUImage).imgName"
                            ></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template #default="{ row }">
                            {{ (row as SPUImage).imgName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" align="center">
                        <template #default="{ row }">
                            <el-button
                                type="warning"
                                size="default"
                                @click="handler(row)"
                            >
                                设置默认
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="save">保存</el-button>
                <el-button type="primary" @click="cancel">取消</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import { reqAttrInfoList } from '@/api/product/attr'
import { ElMessage } from 'element-plus'
import {
    SKUData,
    SPUImage,
    SPUInfo,
    SPUSaleValueList,
} from '@/api/product/spu/type.ts'
import { AttrInfo } from '@/api/product/attr/type.ts'
import { reactive, ref } from 'vue'
import {
    reqAddSku,
    reqSPUIMAGES,
    reqSPUSaleAttrValueList,
} from '@/api/product/spu'

let $emit = defineEmits(['changeScene'])
// 分类的属性
let attrInfoList = ref<AttrInfo[]>([])
// spu的照片
let spuImage = ref<SPUImage[]>([])
// spu销售属性的价值的列表
let spuSaleAttrValueList = ref<SPUSaleValueList[]>([])

let skuData = reactive<SKUData>({})

let table = ref<any>()

const handler = (row: SPUImage) => {
    spuImage.value.forEach((item) =>
        table.value.toggleRowSelection(item, false),
    )
    table.value.toggleRowSelection(row, true)
    skuData.skuDefaultImg = row.imgUrl as string
}

const cancel = () => {
    $emit('changeScene', 1)
}

const save = async () => {
    // 	整理参数
    // 	销售属性
    skuData.skuSaleAttrValueList = spuSaleAttrValueList.value.reduce(
        (prev: any, next: any) => {
            if (next.idandValue) {
                let [saleAttrId, saleAttrValueId] = next.idandValue.split(':')
                console.log(saleAttrId, saleAttrValueId)
                prev.push({
                    saleAttrId,
                    saleAttrValueId,
                })
            }
            return prev
        },
        [],
    )

    // 平台属性列表
    skuData.skuAttrValueList = attrInfoList.value.reduce(
        (prev: any, next: any) => {
            if (next.idandValue) {
                let [attrId, valueId] = next.idandValue.split(':')
                prev.push({
                    attrId,
                    valueId,
                })
            }
            return prev
        },
        [],
    )
    let result = await reqAddSku(skuData)
    if (result.code == 200) {
        ElMessage.success('添加sku成功')
        $emit('changeScene', 1)
        skuData = {}
        spuImage.value = []
        spuSaleAttrValueList.value = []
        attrInfoList.value = []
    } else {
        ElMessage.error('添加sku失败')
        $emit('changeScene', 1)
    }
}

// 获取对应分类下的已有属性和属性接口
const getAttrInfoList = async (c1Id: number, c2Id: number, c3Id: number) => {
    let result = await reqAttrInfoList(c1Id, c2Id, c3Id)
    if (result.code == 200) {
        // console.log(result)
        attrInfoList.value = result.data
    } else {
        ElMessage.error('请求分类的属性出错')
    }
}

const getImageList = async (spuId: number) => {
    let result = await reqSPUIMAGES(spuId)
    if (result.code == 200) {
        // console.log(result)
        spuImage.value = result.data
    } else {
        ElMessage.error('获取照片墙失败')
    }
}

const getSPUSaleAttrList = async (spuId: number) => {
    let result = await reqSPUSaleAttrValueList(spuId)
    if (result.code == 200) {
        // console.log(result)
        spuSaleAttrValueList.value = result.data
    } else {
        ElMessage.error('获取spu属性数据失败')
    }
}

const initSkuData = (
    c1Id: number,
    c2Id: number,
    c3Id: number,
    row: SPUInfo,
) => {
    skuData.category3Id = row.category3Id as number
    skuData.spuId = row.spuId as number
    skuData.tmId = row.tmId as number
    // console.log('123')
    getAttrInfoList(c1Id, c2Id, c3Id)
    getImageList(row.spuId as number)
    getSPUSaleAttrList(row.spuId as number)
}

defineExpose({ initSkuData })
</script>

<style scoped lang="scss"></style>
