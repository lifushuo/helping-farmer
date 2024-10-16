<template>
    <el-card>
        <el-form :inline="true" style="width: 100%">
            <el-form-item label="一级分类" style="width: 25%">
                <el-select
                    v-model="skuStore.c1Id as number"
                    @change="changeC1"
                    :disabled="scene == 1 ? false : true"
                >
                    <el-option
                        v-for="(item, index) in skuStore.c1Data"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="二级分类" style="width: 25%">
                <el-select
                    v-model="skuStore.c2Id as number"
                    :disabled="
                        !skuStore.c1Id
                            ? true
                            : false || scene == 1
                              ? false
                              : true
                    "
                    @change="changeC2()"
                >
                    <el-option
                        v-for="(item, index) in skuStore.c2Data"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="spu" style="width: 25%">
                <el-select
                    v-model="skuStore.spuId as number"
                    :disabled="
                        !skuStore.c2Id
                            ? true
                            : false || scene == 1
                              ? false
                              : true
                    "
                    @change="emits('search')"
                >
                    <el-option
                        v-for="(item, index) in skuStore.spuData"
                        :key="index"
                        :label="item.spuName as string"
                        :value="item.spuId as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="searchEvent">搜索</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useSkuStore } from '@/store/modules/sku.ts'

const skuStore = useSkuStore()
defineProps({
    scene: Number,
})

let emits = defineEmits(['search'])

const searchEvent = () => {
    console.log('nihao')
    emits('search')
}

const getC1 = async () => {
    try {
        await skuStore.getC1Data()
    } catch (error: any) {
        ElMessage.error(error.message)
    }
}
const getC2 = async () => {
    try {
        await skuStore.getC2Data()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}

const getSpuData = async () => {
    try {
        await skuStore.getSpuData()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}

// C1的选项进行修改后
const changeC1 = () => {
    skuStore.c2Id = undefined
    skuStore.c2Data = []
    getC2()
}

const changeC2 = () => {
    skuStore.spuId = undefined
    skuStore.spuData = []
    getSpuData()
}

onMounted(() => {
    getC1()
})
</script>

<style scoped></style>
