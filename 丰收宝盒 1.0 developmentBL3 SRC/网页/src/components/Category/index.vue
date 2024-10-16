<template>
    <div>
        <el-card>
            <el-form :inline="true" style="width: 100%">
                <el-form-item label="一级分类" style="width: 25%">
                    <el-select
                        v-model="categoryStore.c1Id as number"
                        @change="changeC1"
                        :disabled="scene == 1 ? false : true"
                    >
                        <el-option
                            v-for="(item, index) in categoryStore.c1Data"
                            :key="index"
                            :label="item.name as string"
                            :value="item.id as number"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="二级分类" style="width: 25%">
                    <el-select
                        v-model="categoryStore.c2Id as number"
                        @change="changeC2"
                        :disabled="
                            categoryStore.c1Id == ''
                                ? true
                                : false || scene == 1
                                  ? false
                                  : true
                        "
                    >
                        <el-option
                            v-for="(item, index) in categoryStore.c2Data"
                            :key="index"
                            :label="item.name as string"
                            :value="item.id as number"
                        ></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="三级分类" style="width: 25%">
                    <el-select
                        v-model="categoryStore.c3Id as string"
                        :disabled="
                            categoryStore.c2Id == ''
                                ? true
                                : false || scene == 1
                                  ? false
                                  : true
                        "
                    >
                        <el-option
                            v-for="(item, index) in categoryStore.c3Data"
                            :key="index"
                            :label="item.name as string"
                            :value="item.id as number"
                        ></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import useCategoryStore from '@/store/modules/category'

defineProps(['scene'])

let categoryStore = useCategoryStore()

const getC1 = async () => {
    try {
        await categoryStore.getC1()
    } catch (error: any) {
        ElMessage.error(error.message)
    }
}
const getC2 = async () => {
    try {
        await categoryStore.getC2()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}
const getC3 = async () => {
    try {
        await categoryStore.getC3()
    } catch (e: any) {
        ElMessage.error(e.message)
    }
}
// C1的选项进行修改后
const changeC1 = () => {
    categoryStore.c3Id = ''
    categoryStore.c2Id = ''
    categoryStore.c2Data = []
    categoryStore.c3Data = []
    categoryStore.AttrInfoList = []
    getC2()
}

const changeC2 = () => {
    categoryStore.c3Id = ''
    categoryStore.c3Data = []
    categoryStore.AttrInfoList = []
    getC3()
}

onMounted(() => {
    getC1()
})
</script>
<style scoped lang="scss"></style>
