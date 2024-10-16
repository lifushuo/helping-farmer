<template>
    <el-card style="margin: 10px 0">
        <el-form label-width="100px">
            <el-form-item label="SPU名称">
                <el-input
                    placeholder="请输入SPU名称"
                    v-model="SPUParams.spuName"
                ></el-input>
            </el-form-item>
            <el-form-item label="SPU描述">
                <el-input
                    type="textarea"
                    v-model="SPUParams.description"
                    placeholder="请输入描述"
                ></el-input>
            </el-form-item>
            <el-form-item label="SPU原价">
                <el-input-number
                    v-model="SPUParams.oldPrice"
                    :precision="2"
                    :step="1"
                    :min="0"
                />
            </el-form-item>
            <el-form-item label="SPU现价">
                <el-input-number
                    v-model="SPUParams.price"
                    :precision="2"
                    :step="1"
                    :min="0"
                />
            </el-form-item>
            <el-form-item label="SPU折扣">
                <el-input-number
                    v-model="SPUParams.discount"
                    :precision="2"
                    :step="0.1"
                    :max="1"
                    :min="0"
                />
            </el-form-item>
            <el-form-item label="SPU库存">
                <el-input-number
                    v-model="SPUParams.storeCount"
                    :precision="2"
                    :step="1"
                    :min="0"
                />
            </el-form-item>

            <el-form-item label="SPU照片">
                <el-upload
                    v-model:file-list="SPUImageData as UploadUserFile[]"
                    action="http://127.0.0.1:8080/admin/common/upload"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :before-upload="handleBeforeUpload"
                >
                    <el-icon><Plus /></el-icon>
                </el-upload>

                <el-dialog v-model="dialogVisible">
                    <img w-full :src="dialogImageUrl" alt="Preview Image" />
                </el-dialog>
            </el-form-item>
            <el-form-item label="SPU规格名称" :inline-message="true">
                <el-input
                    v-model="specificationNameInput"
                    style="width: 20%"
                ></el-input>
                <el-button
                    type="primary"
                    icon="Plus"
                    style="margin: 10px 10px"
                    @click="addSpecification"
                    :disabled="!specificationNameInput"
                >
                    添加销售属性
                </el-button>
                <el-table :data="SPUSpecifictionData" border>
                    <el-table-column
                        label="序号"
                        type="index"
                        align="center"
                        width="80"
                    ></el-table-column>
                    <el-table-column label="销售属性" align="center">
                        <template #default="{ row }">
                            {{ (row as SpuSpecificationType).name }}
                        </template>
                    </el-table-column>
                    <el-table-column label="规格属性值" align="center">
                        <template #default="{ row, $index }">
                            <el-input
                                type="textarea"
                                autosize
                                :ref="
                                    (e) => {
                                        InputRefs.push($index, e)
                                    }
                                "
                                v-model="(row as SpuSpecificationType).value"
                            ></el-input>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template #default="{ $index }">
                            <el-button
                                type="danger"
                                size="small"
                                icon="Delete"
                                @click="SPUSpecifictionData.splice($index, 1)"
                            ></el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-form-item>
            <el-form-item v-if="attributesIsVisable">
                <el-table :data="attributesDataList" border>
                    <el-table-column
                        label="序号"
                        type="index"
                        align="center"
                        width="80"
                    ></el-table-column>
                    <el-table-column label="属性" align="center">
                        <template #default="{ row }">
                            {{ (row as SpuAttributeType).name }}
                        </template>
                    </el-table-column>
                    <el-table-column label="销售属性值" align="center">
                        <template #default="{ row }">
                            <el-tag
                                style="margin: 0 5px"
                                v-for="(item, index) in row.value"
                                :key="index"
                                type="primary"
                            >
                                {{ item }}
                            </el-tag>
                        </template>
                    </el-table-column>
                </el-table>
            </el-form-item>
            <!--            <el-form-item label="SPU销售属性" :inline-message="true">-->
            <!--                <el-select-->
            <!--                    style="width: 20%; margin-right: 10px"-->
            <!--                    v-model="selectedSaleAttr as number"-->
            <!--                    :placeholder="-->
            <!--                        unSelectSaleAttr.length-->
            <!--                            ? `还有${unSelectSaleAttr.length}个属性未选择`-->
            <!--                            : '已经全部选择'-->
            <!--                    "-->
            <!--                >-->
            <!--                    <el-option-->
            <!--                        v-for="(item, index) in unSelectSaleAttr"-->
            <!--                        :key="index"-->
            <!--                        :label="item.name as string"-->
            <!--                        :value="item.id as number"-->
            <!--                    ></el-option>-->
            <!--                </el-select>-->
            <!--                <el-button-->
            <!--                    type="primary"-->
            <!--                    icon="Plus"-->
            <!--                    style="margin: 10px 0"-->
            <!--                    @click="addSaleAttr"-->
            <!--                    :disabled="!selectedSaleAttr"-->
            <!--                >-->
            <!--                    添加销售属性-->
            <!--                </el-button>-->
            <!--            <el-table :data="SPUSaleAttrValueData" border>-->
            <!--                <el-table-column-->
            <!--                    label="序号"-->
            <!--                    type="index"-->
            <!--                    align="center"-->
            <!--                    width="80"-->
            <!--                ></el-table-column>-->
            <!--                <el-table-column label="销售属性" align="center">-->
            <!--                    <template #default="{ row }">-->
            <!--                        {{ (row as SPUSaleValueList).saleAttrName }}-->
            <!--                    </template>-->
            <!--                </el-table-column>-->
            <!--                <el-table-column label="销售属性值" align="center">-->
            <!--                    <template #default="{ row, $index }">-->
            <!--                        <el-tag-->
            <!--                            style="margin: 0 5px"-->
            <!--                            v-for="(item, index) in (row as SPUSaleValueList)-->
            <!--                                .spuSaleAttrValueList"-->
            <!--                            :key="index"-->
            <!--                            type="primary"-->
            <!--                            :closable="true"-->
            <!--                            @close="-->
            <!--                                handleOnTagClose(item, row.spuSaleAttrValueList)-->
            <!--                            "-->
            <!--                        >-->
            <!--                            {{ item.saleAttrValueName }}-->
            <!--                        </el-tag>-->
            <!--                        <el-input-->
            <!--                            v-show="inputVisible[$index]"-->
            <!--                            :ref="-->
            <!--                                (vc: any) => {-->
            <!--                                    InputRefs[$index] = vc-->
            <!--                                }-->
            <!--                            "-->
            <!--                            style="width: 15%"-->
            <!--                            v-model="inputValue"-->
            <!--                            class="w-20"-->
            <!--                            size="small"-->
            <!--                            @blur="handleInputConfirm(row, $index)"-->
            <!--                        />-->
            <!--                        <el-button-->
            <!--                            v-show="!inputVisible[$index]"-->
            <!--                            class="button-new-tag"-->
            <!--                            size="small"-->
            <!--                            @click="showInput($index)"-->
            <!--                        >-->
            <!--                            + New Tag-->
            <!--                        </el-button>-->
            <!--                    </template>-->
            <!--                </el-table-column>-->
            <!--                <el-table-column>-->
            <!--                    <template #default="{ $index }">-->
            <!--                        <el-button-->
            <!--                            type="danger"-->
            <!--                            size="small"-->
            <!--                            icon="Delete"-->
            <!--                            @click="SPUSaleAttrValueData.splice($index, 1)"-->
            <!--                        ></el-button>-->
            <!--                    </template>-->
            <!--                </el-table-column>-->
            <!--            </el-table>-->
            <!--            </el-form-item>-->
            <el-form-item>
                <el-button type="primary" @click="save">保存</el-button>
                <el-button @click="cancel">取消</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script setup lang="ts">
import { SPUImage } from '@/api/product/spu/type.ts'

import {
    ElMessage,
    UploadProps,
    UploadRawFile,
    UploadUserFile,
} from 'element-plus'

import { computed, nextTick, ref } from 'vue'
import {
    SpuAttributeType,
    SpuDTOType,
    SpuSpecificationType,
} from '@/api/production/spu/type.ts'

import {
    getSpuDatabyId,
    postSpuDataAdd,
    putSpuDataEdit,
} from '@/api/production/spu'
import useSpuStore from '@/store/modules/spu.ts'

let attributesIsVisable = computed(
    () => SPUParams.value.attributes && SPUParams.value.attributes.length,
)
// 控制上传图片的缩放
let dialogVisible = ref<boolean>(false)
//控制上传图片的 缩放
let dialogImageUrl = ref<string>('')

// tag input输入框的实例
let InputRefs = ref<any>([])

let emit = defineEmits(['changeScene'])

// 将相同的 name 整合成新的数组
const attributesDataList = computed(() => {
    if (!SPUParams.value.attributes || SPUParams.value.attributes.length == 0) {
        return []
    }

    return SPUParams.value.attributes.reduce((acc, item) => {
        // 查找当前 name 是否已经存在于 acc 中
        let existingItem = acc.find((attr) => attr.name === item.name)

        if (existingItem) {
            // 如果存在，将 value 添加到 existingItem 的 value 数组中
            existingItem.value.push(item.value)
        } else {
            // 如果不存在，创建一个新的对象并添加到 acc 中
            acc.push({ name: item.name, value: [item.value] })
        }
        return acc
    }, [])
})
let specificationNameInput = ref<string>('')

// 该品牌的SPU规格价值
let SPUSpecifictionData = ref<SpuSpecificationType[]>([])

// 该品牌的SPU照片
let SPUImageData = ref<SPUImage[]>([])

let SPUParams = ref<SpuDTOType>({
    category2Id: 0,
    description: ' ',
    discount: 1,
    oldPrice: 0,
    picture: [],
    price: 0,
    specifications: [],
    spuName: '',
    storeCount: 0,
})

// 添加spu的规格名字
const addSpecification = () => {
    if (specificationNameInput.value) {
        let findItem = SPUSpecifictionData.value.find((item) => {
            return item.name == specificationNameInput.value
        })
        if (findItem) {
            ElMessage.error('不能添加重复的规格')
        } else {
            SPUSpecifictionData.value.push({
                name: specificationNameInput.value,
                value: ' ',
            })
            specificationNameInput.value = ''
            showInput(SPUSpecifictionData.value.length)
        }
    }
}

// 触发spu属性的输入框
const showInput = (index: number) => {
    // inputVisible.value = true
    nextTick(() => {
        // console.log(InputRefs[index])
        // console.log(InputRefs)
        InputRefs.value[index].focus()
    })
}

// 上传图片前的处理
const handleBeforeUpload = (file: UploadRawFile) => {
    if (
        file.type == 'image/png' ||
        file.type == 'image/jpeg' ||
        file.type == 'image/gif'
    ) {
        if (file.size / 1024 / 1024 < 5) {
            return true
        } else {
            ElMessage.error('只能上传小于5MB的图片')
        }
    } else {
        ElMessage.error('上传文件格式务必为 png jpg gif')
    }
    return false
}

const handlePictureCardPreview: UploadProps['onPreview'] = (uploadFile) => {
    dialogImageUrl.value = uploadFile.url!
    dialogVisible.value = true
}

//点击取消按钮 通知父组件切换到主界面
const cancel = () => {
    emit('changeScene', 1)
    SPUParams.value = {
        category2Id: 0,
        description: ' ',
        discount: 1,
        oldPrice: ' ',
        picture: [],
        price: ' ',
        specifications: [],
        spuName: '',
        storeCount: 0,
    }
}
let spuStore = useSpuStore()

// 点击保存按钮的回调
const save = async () => {
    console.log(SPUImageData.value)
    // 整理好照片墙的数据
    SPUParams.value.picture = SPUImageData.value.map((item: any) => {
        return item.response.data
    })
    // 整理销售属性的数据
    SPUParams.value.specifications = SPUSpecifictionData.value
    SPUParams.value.category2Id = spuStore.c2Id

    console.log(SPUParams.value)
    let result
    if (SPUParams.value.id) {
        result = await putSpuDataEdit(SPUParams.value)
    } else {
        result = await postSpuDataAdd(SPUParams.value)
    }
    if (result.code == 200) {
        ElMessage.success('不容易呀')
        emit('changeScene', 1)
    } else {
        ElMessage.error('出错啦')
    }
}

const initHasSupData = async (spuId: number) => {
    // 清除原来的数据
    clearData()
    let data = await getSpuDatabyId(spuId)
    // console.log(data)
    if (data.code == 200) {
        SPUParams.value = data.data
        SPUSpecifictionData.value = SPUParams.value.specifications
        SPUImageData.value = data.data.picture.map((item) => {
            return { imgUrl: item }
        })
    } else {
        ElMessage.error('获取spu详细信息失败')
    }
}

//添加一个个新的SPU初始化请求方法
const initAddSPU = (c2Id: string | number) => {
    // 清除原来的数据
    clearData()
    SPUParams.value.category2Id = c2Id
}

// 清楚残留数据
const clearData = () => {
    SPUParams.value = {}
    SPUImageData.value = []
    SPUSpecifictionData.value = []
}

defineExpose({ initHasSupData, initAddSPU })
</script>

<style scoped lang="scss"></style>
