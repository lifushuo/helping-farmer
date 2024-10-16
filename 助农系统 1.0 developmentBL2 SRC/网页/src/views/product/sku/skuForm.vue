<template>
    <el-card style="margin: 10px 0">
        <el-form label-width="100px">
            <el-form-item label="sku名称">
                <el-input
                    v-model="skuInfoData.skuName"
                    placeholder="SKU名称"
                ></el-input>
            </el-form-item>
            <el-form-item label="原价(元)">
                <el-input
                    v-model="skuInfoData.oldPrice"
                    placeholder="原价(元)"
                    type="number"
                    min="0"
                ></el-input>
            </el-form-item>
            <el-form-item label="现价(元)">
                <el-input
                    v-model="skuInfoData.price"
                    placeholder="现价(元)"
                    type="number"
                    min="0"
                ></el-input>
            </el-form-item>
            <el-form-item label="库存">
                <el-input
                    v-model="skuInfoData.storeCount"
                    placeholder="库存(整数)"
                    type="number"
                    min="0"
                ></el-input>
            </el-form-item>
            <el-form-item label="平台属性">
                <el-form :inline="true" label-width="100px">
                    <el-form-item
                        v-for="(item, index) in skuAttr"
                        :key="item.name"
                        :label="item.name as string"
                        style="width: 200px"
                    >
                        <el-select v-model="skuAttrValue[index]">
                            <el-option
                                v-for="item1 in item.values"
                                :key="item1.id"
                                :label="item1.value"
                                :value="item1.id"
                            ></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
            </el-form-item>
            <el-form-item label="照片">
                <div style="width: 400px; height: 178px">
                    <el-upload
                        class="avatar-uploader"
                        action="http://127.0.0.1:8080/admin/common/upload"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"
                    >
                        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                        <el-icon v-else class="avatar-uploader-icon">
                            <Plus />
                        </el-icon>
                    </el-upload>
                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="save">保存</el-button>
                <el-button type="primary" @click="cancel">取消</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { UploadProps } from 'element-plus'
import { SkuEditType } from '@/api/production/sku/type.ts'
import { AttrPageDataType } from '@/api/production/attr/type.ts'
import { getAttributePageData } from '@/api/production/attr'
import {
    getSkuDetailById,
    getSKUEditDetail,
    postSKUAdd,
    putSkuEdit,
} from '@/api/production/sku'
import { useSkuStore } from '@/store/modules/sku.ts'

let $emit = defineEmits(['changeScene'])

let skuAttr = ref<AttrPageDataType[]>([])

let skuAttrValue = ref<number[]>([])

let skuInfoData = ref<SkuEditType>({
    attributes: [],
    id: 0,
    oldPrice: '',
    picture: '',
    price: '',
    skuName: '',
    spuId: '',
    storeCount: '',
})
const skuStore = useSkuStore()
let imageUrl = ref('')

const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response: any,
    uploadFile: any,
) => {
    console.log(response)
    console.log(uploadFile)
	
    if (response.code == 200) {
        imageUrl.value = response.data
    } else {
        ElMessage.error('上传失败' + response.msg)
    }
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
    if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
    }
    return true
}

const getAttrInfoData = async (spuId: number) => {
    let data = await getAttributePageData('', '', spuId)
    skuAttr.value = data.data.records
}

const cancel = () => {
    $emit('changeScene', 1)
}

const save = async () => {
    if (skuAttrValue.value.length < skuAttr.value.length) {
        ElMessage.error('属性未选好')
        return
    }
    if (skuInfoData.value.picture == '') {
        ElMessage.error('照片还未选好')
        return
    }
    if (!skuInfoData.value.skuName || skuInfoData.value.skuName == '') {
        ElMessage.error('sku名字还未输入')
        return
    }
    if (!skuInfoData.value.price || skuInfoData.value.price == '') {
        ElMessage.error('sku现价还未输入')
        return
    }
    if (!skuInfoData.value.oldPrice || skuInfoData.value.oldPrice == '') {
        ElMessage.error('sku原价还未输入')
        return
    }
    if (!skuInfoData.value.id) {
        //添加属性
        skuInfoData.value.attributes = skuAttrValue.value
        skuInfoData.value.picture = imageUrl.value
        skuInfoData.value.spuId = skuStore.spuId
        let data = await postSKUAdd(skuInfoData.value)
        if (data.code == 200) {
            ElMessage.success('添加成功')
            $emit('changeScene', 1)
        } else {
            ElMessage.error('添加失败')
        }
    } else {
        skuInfoData.value.attributes = skuAttrValue.value
        skuInfoData.value.picture = imageUrl.value
        skuInfoData.value.spuId = skuStore.spuId

        let data = await putSkuEdit(skuInfoData.value)
        if (data.code == 200) {
            ElMessage.success('添加成功')
            $emit('changeScene', 1)
        } else {
            ElMessage.error('添加失败')
        }
    }
}

const clearData = () => {
    skuInfoData.value = {}
    skuAttrValue.value = []
    imageUrl.value = ''
}

const initSkuData = async (skuId?: number) => {
    clearData()
    getAttrInfoData(skuStore.spuId)
    if (skuId) {
        let data = await getSKUEditDetail(skuId)
        if (data.code == 200) {
            skuInfoData.value = data.data
            imageUrl.value = data.data.picture
            skuAttrValue.value = data.data.attributes
        } else {
            ElMessage.error('获取数据失败')
            $emit('changeScene', 1)
        }
    }
}

defineExpose({ initSkuData })
</script>

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

<style scoped lang="scss"></style>
