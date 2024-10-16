<template>
    <el-card>
        <el-form :inline="true" style="width: 100%">
            <el-form-item label="一级分类" style="width: 25%">
                <el-select
                    v-model="spuStore.c1Id as number"
                    @change="changeC1"
                    :disabled="scene == 1 ? false : true"
                >
                    <el-option
                        v-for="(item, index) in spuStore.c1Data.fcategorylist"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="二级分类" style="width: 25%">
                <el-select
                    v-model="spuStore.c2Id as number"
                    :disabled="
                        spuStore.c1Id == ''
                            ? true
                            : false || scene == 1
                              ? false
                              : true
                    "
                    @change="emits('search')"
                >
                    <el-option
                        v-for="(item, index) in spuStore.c2Data.records"
                        :key="index"
                        :label="item.name as string"
                        :value="item.id as number"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="名字" style="width: 25%">
                <el-input
                    v-model="spuStore.name"
                    :disabled="scene == 1 ? false : true"
                    @change="emits('search')"
                ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="searchEvent">搜索</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import useSpuStore from '@/store/modules/spu.ts'
import { onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const spuStore = useSpuStore()
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
        await spuStore.getC1()
    } catch (error: any) {
        ElMessage.error(error.message)
    }
}
const getC2 = async () => {
    try {
        await spuStore.getC2()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}

// C1的选项进行修改后
const changeC1 = () => {
    spuStore.c2Id = ''
    spuStore.c2Data = []
    getC2()
}

onMounted(() => {
    getC1()
})
</script>

<style scoped></style>
