<template>
    <div>
        <el-card>
            <span>一级分类</span>
            <el-select
                v-model="slectedFCategoryID"
                size="default"
                style="width: 200px; margin-left: 10px"
            >
                <el-option
                    v-for="value in fCategoryList.fcategorylist"
                    :key="value.id"
                    :label="value.name"
                    :value="value.id"
                ></el-option>
            </el-select>
            <span style="margin-left: 10px">二级分类名字</span>
            <el-input
                size="default"
                style="width: 200px; margin-left: 10px"
                v-model="sCategoryInput"
            ></el-input>
            <el-button
                type="info"
                icon="Search"
                style="margin-left: 10px"
                @click="getSCategoryPage()"
            >
                搜索
            </el-button>
            <el-button
                type="primary"
                style="float: right"
                @click="addSCategory()"
            >
                + 添加分类
            </el-button>
        </el-card>
        <div style="margin-top: 10px">
            <el-card class="box-card" height="250">
                <el-button
                    type="primary"
                    size="default"
                    icon="Plus"
                    v-has="`btn.trademark.add`"
                >
                    添加品牌
                </el-button>
                <el-table
                    border
                    style="margin: 10px 0px"
                    :data="sCategoryPageData.records"
                >
                    <el-table-column
                        label="序号"
                        width="80"
                        align="center"
                        type="index"
                    ></el-table-column>
                    <el-table-column label="名称" align="center">
                        <template #default="{ row }">
                            <pre style="color: aqua">{{
                                (row as SCategoryPageType).name
                            }}</pre>
                        </template>
                    </el-table-column>
                    <el-table-column label="一级分类名称" align="center">
                        <template #default="{ row }">
                            {{ (row as SCategoryPageType).parentName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="最后操作时间" align="center">
                        <template #default="{ row }">
                            {{ (row as SCategoryPageType).updateTime }}
                        </template>
                    </el-table-column>
                    <el-table-column label="状态" align="center">
                        <template #default="{ row }">
                            <el-tag
                                v-bind:type="
                                    (row as SCategoryPageType).isEffective == 1
                                        ? 'primary'
                                        : 'info'
                                "
                            >
                                {{
                                    (row as SCategoryPageType).isEffective == 1
                                        ? '上架'
                                        : '下架'
                                }}
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="分类操作" align="center">
                        <template #default="{ row, $index }">
                            <el-button
                                size="small"
                                @click="changeSCategoryStatus(row.id, $index)"
                                v-bind:type="
                                    (row as SCategoryPageType).isEffective == 1
                                        ? 'info'
                                        : 'primary'
                                "
                            >
	                            {{
		                            (row as SCategoryPageType).isEffective == 1
			                            ? '下架'
			                            : '上架'
	                            }}
                            </el-button>
                            <el-button
                                size="small"
                                type="primary"
                                icon="Edit"
                                @click="editSCategory(row.id, $index)"
                            ></el-button>
                            <el-popconfirm
                                :title="`你确定删除${row.name}`"
                                icon="Delete"
                                @confirm="removeSCategory(row.id)"
                            >
                                <template #reference>
                                    <el-button
                                        size="small"
                                        type="primary"
                                        icon="Delete"
                                    ></el-button>
                                </template>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                    v-model:current-page="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[3, 5, 7, 9]"
                    :small="false"
                    :background="true"
                    layout=" prev, pager, next, jumper,->,total, sizes,"
                    :total="total"
                    :pager-count="9"
                    @current-change="getSCategoryPage"
                    @size-change="getSCategoryPage"
                />
            </el-card>

            <!--            对话框组件：在添加品牌与已有品牌的业务时候使用的结构 v-model-->
            <!--            属性用户控制对话框-->
            <el-dialog
                v-model="dialogFormVisible"
                :title="dialogFormId ? '编辑分类' : '添加分类'"
                width="500"
                @close="clearDialog"
            >
                <el-form
                    style="width: 80%; margin-top: 10px"
                    :model="dialogFCategoryParam"
                    :rules="rules"
                    status-icon
                    ref="refForm"
                >
                    <el-form-item
                        label="分类名称"
                        label-width="80px"
                        prop="name"
                    >
                        <el-input
                            placeholder="请您输入分类名称"
                            v-model="dialogFCategoryParam.name"
                        ></el-input>
                    </el-form-item>

                    <el-form-item
                        label="父级分类"
                        label-width="80px"
                        style="margin-top: 10px"
                        prop="category2Id"
                    >
                        <el-select
                            v-model="dialogFCategoryParam.category2Id"
                            size="default"
                            style="width: 200px; margin-left: 10px"
                        >
                            <el-option
                                v-for="value in fCategoryList.fcategorylist"
                                :key="value.id"
                                :label="value.name"
                                :value="value.id"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <!-- 具名插槽 -->
                <template #footer>
                    <el-button
                        type="primary"
                        size="default"
                        @click="cancelDialog()"
                    >
                        取消
                    </el-button>
                    <el-button
                        type="primary"
                        size="default"
                        @click="confirmDialog()"
                    >
                        确定
                    </el-button>
                </template>
            </el-dialog>
            <!--         添加按钮的时候的对话框 -->
        </div>
    </div>
</template>

<script setup lang="ts">
import { FCategoryList } from '@/api/fcategory/type.ts'
import { getFCategoryList } from '@/api/fcategory'
import { nextTick, onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { SCategoryPageType } from '@/api/scategory/type.ts'
import { PageData } from '@/api'
import {
    deleteSCategoryById,
    getSCategoryPageData,
    postSCategoryAdd,
    putSCategoryEdit,
    putSCategoryStatus,
} from '@/api/scategory'

let dialogFormVisible = ref(false)
let dialogFormId = ref<number | undefined>(undefined)
let total = ref(10)
let currentPage = ref(1)
let pageSize = ref(10)
let fCategoryList = ref<FCategoryList>({ fcategorylist: [] })
let slectedFCategoryID = ref<number | string>('')
let refForm = ref()

let sCategoryInput = ref<string>('')
let dialogFCategoryParam = ref<{
    id?: number
    name?: string
    category2Id?: number
}>({ name: '' })

let sCategoryPageData = ref<PageData<SCategoryPageType>>({
    records: [],
    total: 0,
})

const changeSCategoryStatus = async (id: number, index) => {
    // ElMessage.success('hsdlfhjsld')
    // console.log(id, index)
    let status = sCategoryPageData.value.records[index].isEffective
    let data = await putSCategoryStatus(id, status == 1 ? 0 : 1)
    if (data.code == 200) {
        ElMessage.success('状态修改成功')
        sCategoryPageData.value.records[index].isEffective =
            status == 1 ? 0 : 1
    } else {
        ElMessage.error('修改状态失败')
    }
}

const confirmDialog = async () => {
    await refForm.value.validate()
    // console.log(dialogFCategoryParam.value)
    if (dialogFormId.value) {
        // 编辑
        let data = await putSCategoryEdit(
            dialogFormId.value,
            dialogFCategoryParam.value.category2Id,
            dialogFCategoryParam.value.name,
        )
        if (data.code == 200) {
            ElMessage.success('成功修改')
            dialogFormVisible.value = false
            clearDialog()
        } else {
            ElMessage.error('修改失败')
        }
    } else {
        // 添加
        let data = await postSCategoryAdd(
            dialogFCategoryParam.value.category2Id,
            dialogFCategoryParam.value.name,
        )
        if (data.code == 200) {
            ElMessage.success('成功添加')
            dialogFormVisible.value = false
            clearDialog()
        } else {
            ElMessage.error('添加失败')
        }
    }
}

const addSCategory = () => {
    dialogFormVisible.value = true
    nextTick()
    refForm.value.clearValidate()

    console.log(dialogFormVisible.value)
}
const editSCategory = async (id: number, index: number) => {
    dialogFormVisible.value = true
    nextTick()
    refForm.value.clearValidate()
    dialogFormId.value = id
    dialogFCategoryParam.value.id = id
    dialogFCategoryParam.value.name =
        sCategoryPageData.value.records[index].name
    dialogFCategoryParam.value.category2Id =
        sCategoryPageData.value.records[index].parentId
}
const clearDialog = async () => {
    dialogFormId.value = undefined
    dialogFCategoryParam.value = { name: '' }
}

const cancelDialog = () => {
    dialogFormVisible.value = false
    clearDialog()
}

const removeSCategory = async (id: number) => {
    let data = await deleteSCategoryById(id)
    if (data.code == 200) {
        ElMessage.success('删除成功')
        getSCategoryPage()
    } else {
        ElMessage.error('删除失败')
    }
}

const getSCategoryPage = async () => {
    let data = await getSCategoryPageData(
        currentPage.value,
        pageSize.value,
        sCategoryInput.value,
        slectedFCategoryID.value,
    )
    if (data.code == 200) {
        sCategoryPageData.value = data.data
    } else {
        ElMessage.error('请求二级分类失败' + data.msg)
    }
}

const getFCategoryListData = async () => {
    let data = await getFCategoryList()
    if (data.code == 200) {
        fCategoryList.value = data.data
    } else {
        ElMessage.error('获取一级分类失败')
    }
}

const validatorTmName = (rule: any, value: string, callback: any) => {
    if (value.trim().length >= 2) {
        callback()
    } else {
        callback(new Error('品牌名字要大于两位'))
    }
}

// 品牌自定义
// 表单校验规则对象
const rules = {
    name: {
        // required:这个字段必须校验 表单初心
        // trrigger: 校验的规则时机【blur change】
        required: true,
        trrigger: 'blur',
        validator: validatorTmName,
    },
    category2Id: {
        required: true,
    },
}
onMounted(async () => {
    await getFCategoryListData()
    await getSCategoryPage()
})
</script>

<style scoped></style>
