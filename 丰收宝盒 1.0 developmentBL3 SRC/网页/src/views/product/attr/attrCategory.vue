<template>
    <el-card>
        <el-form :inline="true" style="width: 100%">
            <el-form-item label="一级分类" style="width: 25%">
                <el-select
                    v-model="attrStore.c1Id as number"
                    @change="changeC1"
                    :disabled="scene == 1 ? false : true"
                >
                    <el-option
                        v-for="(item, index) in attrStore.c1Data"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="二级分类" style="width: 25%">
                <el-select
                    v-model="attrStore.c2Id as number"
                    :disabled="
                        !attrStore.c1Id
                            ? true
                            : false || scene == 1
                              ? false
                              : true
                    "
                    @change="changeC2()"
                >
                    <el-option
                        v-for="(item, index) in attrStore.c2Data"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="spu" style="width: 25%">
                <el-select
                    v-model="attrStore.spuId as number"
                    :disabled="
                        !attrStore.c2Id
                            ? true
                            : false || scene == 1
                              ? false
                              : true
                    "
                    @change="emits('search')"
                >
                    <el-option
                        v-for="(item, index) in attrStore.spuData"
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
import { useAttrStore } from '@/store/modules/attr.ts'

const attrStore = useAttrStore()
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
        await attrStore.getC1Data()
    } catch (error: any) {
        ElMessage.error(error.message)
    }
}
const getC2 = async () => {
    try {
        await attrStore.getC2Data()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}

const getSpuData = async () => {
    try {
        await attrStore.getSpuData()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}

// C1的选项进行修改后
const changeC1 = () => {
    attrStore.c2Id = ''
    attrStore.c2Data = []
    getC2()
}

const changeC2 = () => {
    attrStore.spuId = ''
    attrStore.spuData = []
    getSpuData()
}

onMounted(() => {
    getC1()
})
</script>

<style scoped></style>
