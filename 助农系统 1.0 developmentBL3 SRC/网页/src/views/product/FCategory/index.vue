<template>
    <el-card>
        <span>一级分类</span>
        <el-input
            label="分类名字"
            placeholder="请输入一级分类"
            style="width: 200px; margin-left: 10px"
            v-model:model-value="nameInput"
        ></el-input>
        <el-button
            type="info"
            icon="Search"
            style="margin-left: 10px"
            @click="getPageData"
        >
            搜索
        </el-button>
        <el-button type="primary" style="float: right" @click="addFCategory()">
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
                :data="fCategoryData?.records"
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
                            (row as FCategoryPage).name
                        }}</pre>
                    </template>
                </el-table-column>
                <el-table-column label="图片" align="center">
                    <template #default="{ row }">
                        <img
                            :src="(row as FCategoryPage).icon"
                            style="width: 100px; height: 100px"
                            alt="图片没有"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="状态" align="center">
                    <template #default="{ row }">
                        <el-tag
                            v-bind:type="
                                (row as FCategoryPage).isEffective == 1
                                    ? 'primary'
                                    : 'info'
                            "
                        >
                            {{
                                (row as FCategoryPage).isEffective == 1
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
                            v-bind:type="
                                (row as FCategoryPage).isEffective == 1
                                    ? 'info'
                                    : 'primary'
                            "
                            @click="
                                changeCategoryStatus(
                                    row.id,
                                    row.isEffective,
                                    $index,
                                )
                            "
                        >
                            {{
                                (row as FCategoryPage).isEffective == 1
                                    ? '下架'
                                    : '上架'
                            }}
                        </el-button>

                        <el-button
                            size="small"
                            type="primary"
                            icon="Edit"
                            @click="editFCategory(row.id)"
                        ></el-button>
                        <el-popconfirm
                            :title="`你确定删除${row.name}`"
                            icon="Delete"
                            @confirm="removeFCategory(row.id)"
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
                @current-change="getPageData"
                @size-change="getPageData"
            />
        </el-card>
        <!-- 对话框组件：在添加品牌与已有品牌的业务时候使用的结构 -->
        <!--        v-model 属性用户控制对话框-->
        <el-dialog
            v-model="dialogFormVisible"
            :title="dialogFormId ? '编辑分类' : '添加分类'"
            width="500"
        >
            <el-form
                style="width: 80%"
                :model="dialogFCategoryParam"
                :rules="rules"
                status-icon
                ref="refForm"
            >
                <el-form-item label="分类名称" label-width="80px" prop="name">
                    <el-input
                        placeholder="请您输入分类名称"
                        v-model="dialogFCategoryParam!.name"
                    ></el-input>
                </el-form-item>
                <el-form-item label="分类logo" label-width="80px" prop="icon">
                    <el-upload
                        class="avatar-uploader"
                        action="http://127.0.0.1:8080/admin/common/upload"
                        :before-upload="beforeAvatarUpload"
                        :on-success="handleAvatarSuccess"
                        :show-file-list="false"
                    >
                        <img
                            v-if="dialogFCategoryParam.icon"
                            :src="dialogFCategoryParam.icon"
                            class="avatar"
                        />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </el-form-item>
            </el-form>
            <!-- 具名插槽 -->
            <template #footer>
                <el-button type="primary" size="default" @click="cancelDialog">
                    取消
                </el-button>
                <el-button type="primary" size="default" @click="confirmDialog">
                    确定
                </el-button>
            </template>
        </el-dialog>
        <!--         添加按钮的时候的对话框 -->
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import {
    delFCategoryDelete,
    getFCategotyDetailById,
    getFCategotyPage,
    postFCategoryStatus,
    postFCategotyAdd,
    putFCategoryEdit,
} from '@/api/fcategory'
import { FCategoryAddDTO, FCategoryPage } from '@/api/fcategory/type.ts'
import { PageData } from '@/api'
import { ElMessage, UploadProps } from 'element-plus'

let currentPage = ref(1)
let pageSize = ref(5)
let total = ref(1)
let nameInput = ref<string>('')
let fCategoryData = ref<PageData<FCategoryPage>>()
let dialogFormVisible = ref<boolean>(false)
let dialogFormId = ref<number | undefined>()
let refForm = ref()

// const getDetailsById = () => {}

let dialogFCategoryParam = ref<FCategoryAddDTO>({ icon: '', name: '' })

const changeCategoryStatus = async (
    id: number,
    status: number,
    index: number,
) => {
    let data = await postFCategoryStatus(id, status == 1 ? 0 : 1)
    if (data.code == 200) {
        fCategoryData.value.records[index].is_effective =
            fCategoryData.value.records[index].is_effective == 1 ? 0 : 1
        ElMessage.success('更新状态成功')
    } else {
        ElMessage.error('更新状态失败')
    }
}

const clearDialog = () => {
    dialogFormId.value = undefined
    dialogFCategoryParam.value = { icon: '', name: '' }
    refForm.value.clearValidate()
}

const removeFCategory = async (id: number) => {
    let data = await delFCategoryDelete(id)
    if (data.code == 200) {
        ElMessage.success('删除成功')
        getPageData()
    } else {
        ElMessage.error('删除失败')
    }
}

const cancelDialog = () => {
    dialogFormVisible.value = false
    clearDialog()
}
const confirmDialog = async () => {
    await refForm.value.validate()

    if (dialogFormId.value) {
        // 编辑
        // ElMessage.success("编辑")
        let data = await putFCategoryEdit(dialogFCategoryParam.value)
        if (data.code == 200) {
            ElMessage.success('修改成功')
            dialogFormVisible.value = false
            clearDialog()
        } else {
            ElMessage.error('修改失败' + data.msg)
        }
    } else {
        // ElMessage.success("添加")
        let data = await postFCategotyAdd(dialogFCategoryParam.value)
        if (data.code == 200) {
            ElMessage.success('添加成功')
            dialogFormVisible.value = false
            clearDialog()
        } else {
            ElMessage.error('添加失败' + data.msg)
        }
    }
}

const addFCategory = () => {
    dialogFormVisible.value = true
    dialogFormId.value = undefined
}

const editFCategory = async (id: number) => {
    dialogFormVisible.value = true
    dialogFormId.value = id
    dialogFCategoryParam.value.id = id

    let data = await getFCategotyDetailById(id)
    if (data.code == 200) {
        dialogFCategoryParam.value.name = data.data.name
        dialogFCategoryParam.value.icon = data.data.icon
    } else {
        ElMessage.error('获取用户信息失败')
    }
}

const getPageData = async () => {
    let data = await getFCategotyPage(
        currentPage.value,
        pageSize.value,
        nameInput.value,
    )
    console.log(data.data)
    if (data.code == 200) {
        ElMessage.success('获取数据成功')
        fCategoryData.value = data.data
        total.value = data.data.total
    } else {
        ElMessage.error('请求错误' + data.msg)
    }
}

const validatorTmName = (rule: any, value: string, callback: any) => {
    if (value.trim().length >= 2) {
        callback()
    } else {
        callback(new Error('品牌名字要大于两位'))
    }
}

const validatorLogoUrl = (rule: any, value: string, callback: any) => {
    // console.log(123)
    if (value) {
        callback()
    } else {
        callback(new Error('品牌logo一定要有'))
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
    icon: {
        required: true,
        validator: validatorLogoUrl,
    },
}
// 上传图片组件中的 提前的
const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
    // 钩子在图片上传之前触发，上传文件之前可以约束文件类型
    // 要求上传文件格式 png|jpg|gif 4M
    console.log(rawFile)
    if (
        rawFile.type !== 'image/jpeg' &&
        rawFile.type !== 'image/png' &&
        rawFile.type !== 'image/gif'
    ) {
        ElMessage.error('Avatar picture must be JPG|PNG|GIF format!')
        return false
    } else if (rawFile.size / 1024 / 1024 > 4) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
    }
    return true
}
// 图片上传成功的钩子
const handleAvatarSuccess: UploadProps['onSuccess'] = (response) => {
    // response
    // console.log(response)
    // console.log(uploadFile)
    // trademarkParams.logoUrl = URL.createObjectURL(uploadFile.raw!)
    dialogFCategoryParam.value.icon = response.data
    refForm.value.clearValidate('icon')
}

onMounted(() => {
    getPageData()
    dialogFormId.value = undefined
})
</script>

<style scoped></style>
