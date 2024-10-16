<template>
    <div>
        <el-card class="box-card" height="250">
            <el-button
                type="primary"
                size="default"
                icon="Plus"
                @click="addTrademark"
                v-has="`btn.trademark.add`"
            >
                添加品牌
            </el-button>
            <el-table border style="margin: 10px 0px" :data="trademarkArr">
                <el-table-column
                    label="序号"
                    width="80"
                    align="center"
                    type="index"
                ></el-table-column>
                <el-table-column label="品牌名称" align="center">
                    <template #default="{ row }">
                        <pre style="color: aqua">{{
                            (row as Trademark).tmName
                        }}</pre>
                    </template>
                </el-table-column>
                <el-table-column label="品牌LOGO" align="center">
                    <template #default="{ row }">
                        <img
                            :src="row.logoUrl"
                            style="width: 100px; height: 100px"
                            alt="图片没有"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="品牌操作" align="center">
                    <template #default="{ row }">
                        <el-button
                            size="small"
                            type="primary"
                            icon="Edit"
                            @click="editTrademark(row.id)"
                        ></el-button>
                        <el-popconfirm
                            :title="`你确定删除${row.tmName}`"
                            icon="Delete"
                            @confirm="removeTrademark(row.id)"
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
                v-model:current-page="PageNo"
                v-model:page-size="pageSize"
                :page-sizes="[3, 5, 7, 9]"
                :small="false"
                :background="true"
                layout=" prev, pager, next, jumper,->,total, sizes,"
                :total="total"
                :pager-count="9"
                @current-change="getHasTrademark"
                @size-change="sizeChange"
            />

            <template #footer>Footer content</template>
        </el-card>
        <!-- 对话框组件：在添加品牌与已有品牌的业务时候使用的结构 -->
        <!-- v-model 属性用户控制对话框 -->
        <el-dialog
            v-model="dialogFormVisible"
            :title="trademarkParams.id ? '编辑品牌' : '添加品牌'"
            width="500"
        >
            <el-form
                style="width: 80%"
                :model="trademarkParams"
                :rules="rules"
                status-icon
                ref="refForm"
            >
                <el-form-item label="品牌名称" label-width="80px" prop="tmName">
                    <el-input
                        placeholder="请您输入品牌名称"
                        v-model="trademarkParams.tmName"
                    ></el-input>
                </el-form-item>
                <el-form-item
                    label="品牌logo"
                    label-width="80px"
                    prop="logoUrl"
                >
                    <el-upload
                        class="avatar-uploader"
                        action="/api/admin/product/fileUpload"
                        :before-upload="beforeAvatarUpload"
                        :on-success="handleAvatarSuccess"
                        :show-file-list="false"
                    >
                        <img
                            v-if="trademarkParams.logoUrl"
                            :src="trademarkParams.logoUrl"
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
        <!-- 添加按钮的时候的对话框 -->
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, reactive, nextTick } from 'vue'
import {
    reqHasTrademark,
    reqAddOrEditHasTrademark,
    reqTrademarkById,
    reqDeleteTrademarkById,
} from '@/api/product/trademark/index.ts'
import type {
    BaseTrademarkResponse,
    Trademark,
} from '@/api/product/trademark/type.ts'
import { ElMessage } from 'element-plus'
// 上传文件中
import type { UploadProps } from 'element-plus'

/*

- - - - - - - - - - - - - - - - -
- - - - - - - - - - - - - - - -
这里是数据
*/

let PageNo = ref<number>(1)
let pageSize = ref<number>(3)
// 存储已有数据的总量
let total = ref<number>(0)
let trademarkArr = ref<Trademark[]>()

// 控制对话框的 隐藏与显现
let dialogFormVisible = ref<boolean>(false)
// 存储对话框的 信息对象
let trademarkParams = reactive<Trademark>({
    tmName: '',
    logoUrl: '',
})
// 获取整个表单的实例
let refForm = ref()

/*
----------------------------------------------------------------
这里是 方法

*/

const removeTrademark = async (id: number) => {
    console.log(id)
    let result = await reqDeleteTrademarkById(id)
    if (result.code == 200) {
        ElMessage.success('删除成功')
        getHasTrademark()
    } else {
        ElMessage.error(result.message)
        getHasTrademark()
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
    tmName: {
        // required:这个字段必须校验 表单初心
        // trrigger: 校验的规则时机【blur change】
        required: true,
        trrigger: 'blur',
        validator: validatorTmName,
    },
    logoUrl: {
        required: true,
        validator: validatorLogoUrl,
    },
}

const getHasTrademark = async () => {
    let result: BaseTrademarkResponse = await reqHasTrademark(
        PageNo.value,
        pageSize.value,
    )
    if (result.code == 200) {
        total.value = result.data.total
        trademarkArr.value = result.data.records
    } else {
        ElMessage.error(result.message)
    }
}

// 分页器发生变化的时候会触发这个函数
// 该方法会回传 一个参数 这个参数是
// const changePageNo = async (page: number) => {
//     // console.log(PageNo.value)
//     // 当前页码发生变化的时候再发一次请求
//     let result: BaseTrademarkResponse = await reqHasTrademark(
//         page,
//         pageSize.value,
//     )
//     if (result.code == 200) {
//         total.value = result.data.total
//         trademarkArr.value = result.data.records
//     } else {
//         ElMessage.error(result.message)
//     }
// }

// getHasTrademark()
// 组件挂载完毕 发请求获取数据 获取第一页
// 自定义事件 分页器组件会将下拉菜单中的数据返回
const sizeChange = async () => {
    PageNo.value = 1
    getHasTrademark()
}
// 添加对话框
const addTrademark = () => {
    // 清楚对话框中的内容
    trademarkParams.tmName = ''
    trademarkParams.logoUrl = ''
    trademarkParams.id = null
    dialogFormVisible.value = true
    // console.log(refForm.value)
    // 第二种使用nextTick
    nextTick(() => {
        refForm.value.clearValidate('tmName')
        refForm.value.clearValidate('logoUrl')
    })
}
// 编辑品牌
const editTrademark = async (trademarkID: any) => {
    // 清楚对话框中的内容
    trademarkParams.tmName = ''
    trademarkParams.logoUrl = ''
    // console.log(trademarkID)
    let result = await reqTrademarkById(trademarkID)
    if (result.code == 200) {
        dialogFormVisible.value = true
        // Object.assign(trademarkParams, result.data)
        trademarkParams.id = result.data.id
        trademarkParams.tmName = result.data.tmName
        trademarkParams.logoUrl = result.data.logoUrl
    } else {
        ElMessage.error(result.message)
        getHasTrademark()
    }
    // 第一种方法使用es6中的？可选性语句
    // refForm.value?.clearValidate('tmName')
    // refForm.value?.clearValidate('logoUrl')

    // 第二种使用nextTick
    nextTick(() => {
        refForm.value.clearValidate('tmName')
        refForm.value.clearValidate('logoUrl')
    })
}

// 对话框取消事件
const cancelDialog = () => {
    // 对话框隐藏
    dialogFormVisible.value = false
}

// 对话框确认事件
const confirmDialog = async () => {
    // 调用这个方法 如果await 是失败的Promise则后续的代码不再执行
    await refForm.value.validate()

    let result = await reqAddOrEditHasTrademark(trademarkParams)
    if (result.code == 200) {
        // 添加成功
        // 关闭对话框
        dialogFormVisible.value = false
        ElMessage({
            type: 'success',
            message: trademarkParams.id ? '修改品牌成功' : '提加品牌成功',
        })
        // 更新一下组件
        getHasTrademark()
    } else {
        ElMessage.error(result.message)
    }
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
    trademarkParams.logoUrl = response.data
    refForm.value.clearValidate('logoUrl')
}

onMounted(() => {
    getHasTrademark()
})
</script>
<style scoped lang="scss"></style>
<style scoped>
.avatar-uploader .avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed var(--el-border-color);
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
    border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    text-align: center;
}
</style>
