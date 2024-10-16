<template>
    <div>
        <AttrCategory :scene="scene" @search="search()"></AttrCategory>
        <el-card style="margin: 10px 0px" v-show="scene == 1">
            <el-button
                type="primary"
                icon="Plus"
                @click="addAttr"
                :disabled="!attrStore.spuId ? true : false"
            >
                添加平台属性
            </el-button>
            <el-table
                border
                style="margin: 10px 0px; width: 100%"
                :data="attrDatas"
            >
                <el-table-column
                    label="序号"
                    type="index"
                    align="center"
                    width="100"
                    prop="index"
                ></el-table-column>
                <el-table-column label="属性名称" align="center" width="180">
                    <template #default="{ row }">
                        <pre style="color: aqua">{{
                            (row as AttrPageDataType).name
                        }}</pre>
                    </template>
                </el-table-column>
                <el-table-column label="属性值名称" align="center">
                    <template #default="{ row }">
                        <div class="flex gap-2">
                            <el-tag
                                style="margin: 5px"
                                v-for="(item, index) in (
                                    row as AttrPageDataType
                                ).values"
                                :key="index"
                                :type="tagColor[index % 5].type"
                                round
                            >
                                {{ item.value }}
                            </el-tag>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="操作" align="center" width="180">
                    <template #default="{ row, $index }">
                        <el-button
                            type="warning"
                            icon="Edit"
                            @click="editAttrInfo($index)"
                        ></el-button>
                        <el-popconfirm
                            title="Are you sure to delete this?"
                            @confirm="
                                deleteAttr(
                                    (row as AttrPageDataType).spuId,
                                    (row as AttrPageDataType).name,
                                )
                            "
                        >
                            <template #reference>
                                <el-button
                                    type="danger"
                                    icon="Delete"
                                ></el-button>
                            </template>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-card style="margin: 10px 0px" v-show="scene == 2">
            <el-form>
                <el-form-item label="属性名称">
                    <el-input
                        placeholder="请您输入属性的名字"
                        v-model="inputAttrName"
                        style="width: 30%"
                    ></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0px">
                    <el-button
                        type="primary"
                        icon="Plus"
                        :disabled="inputAttrName ? false : true"
                        @click="addAttrValue"
                    >
                        添加属性值
                    </el-button>
                    <el-button type="info" @click="cancelAdd">取消</el-button>
                    <el-table
                        border
                        :data="attrParams.values"
                        style="margin-top: 10px"
                    >
                        <el-table-column
                            label="序号"
                            type="index"
                            width="100"
                            align="center"
                        ></el-table-column>
                        <el-table-column label="属性值" align="center">
                            <template #default="{ row, $index }">
                                <el-input
                                    :ref="
                                        (vc: any) => {
                                            inputAtrr[$index] = vc
                                        }
                                    "
                                    v-if="row.flag"
                                    placeholder="请输入属性值"
                                    v-model="(row as Value).value"
                                    @blur="tolook(row, $index)"
                                ></el-input>
                                <div
                                    v-if="!row.flag"
                                    @click="toedit(row, $index)"
                                >
                                    {{ (row as Value).value }}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column
                            label="操作"
                            width="180"
                            align="center"
                        >
                            <template #default="{ $index }">
                                <el-button
                                    type="primary"
                                    @click="deleteAttrValue($index)"
                                >
                                    删除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary"
                        @click="save"
                        :disabled="
                            !(
                                attrParams.values.length > 0 &&
                                attrParams.values[0].value != ''
                            )
                        "
                    >
                        保存
                    </el-button>
                    <el-button type="primary" @click="cancelSave">
                        取消
                    </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>

    <!--<el-card style="margin: 10px 0px" v-show="scene == 2">&ndash;&gt;-->
    <!--        <el-form>-->
    <!--            <el-form-item label="属性名称">-->
    <!--                <el-input-->
    <!--                    placeholder="请您输入属性的名字"-->
    <!--                    v-model="attrParams.attrName"-->
    <!--                ></el-input>-->
    <!--                &lt;!&ndash;                    v-model="AttrInfoList.attrName"&ndash;&gt;-->
    <!--            </el-form-item>-->
    <!--            <el-form-item style="margin: 10px 0px">-->
    <!--                <el-button-->
    <!--                    type="primary"-->
    <!--                    icon="Plus"-->
    <!--                    :disabled="attrParams.attrName ? false : true"-->
    <!--                    @click="addAttrValue"-->
    <!--                >-->
    <!--                    添加属性值-->
    <!--                </el-button>-->
    <!--                <el-button type="info" @click="cancelAdd">取消</el-button>-->
    <!--                <el-table border :data="attrParams.attrValueList">-->
    <!--                    <el-table-column-->
    <!--                        label="序号"-->
    <!--                        type="index"-->
    <!--                        width="100"-->
    <!--                        align="center"-->
    <!--                    ></el-table-column>-->
    <!--                    <el-table-column label="属性值" align="center">-->
    <!--                        <template #default="{ row, $index }">-->
    <!--                            <el-input-->
    <!--                                :ref="-->
    <!--                                    (vc: any) => {-->
    <!--                                        inputAtrr[$index] = vc-->
    <!--                                    }-->
    <!--                                "-->
    <!--                                v-if="row.flag"-->
    <!--                                placeholder="请输入属性值"-->
    <!--                                v-model="(row as AttrValue).valueName"-->
    <!--                                @blur="tolook(row, $index)"-->
    <!--                            ></el-input>-->
    <!--                            <div v-if="!row.flag" @click="toedit(row, $index)">-->
    <!--                                {{ (row as AttrValue).valueName }}-->
    <!--                            </div>-->
    <!--                        </template>-->
    <!--                    </el-table-column>-->
    <!--                    <el-table-column label="操作" width="180" align="center">-->
    <!--                        <template #default="{ $index }">-->
    <!--                            <el-button-->
    <!--                                type="primary"-->
    <!--                                @click="deleteAttrValue($index)"-->
    <!--                            >-->
    <!--                                删除-->
    <!--                            </el-button>-->
    <!--                        </template>-->
    <!--                    </el-table-column>-->
    <!--                </el-table>-->
    <!--            </el-form-item>-->
    <!--            <el-form-item>-->
    <!--                <el-button-->
    <!--                    type="primary"-->
    <!--                    @click="save"-->
    <!--                    :disabled="-->
    <!--                        !(-->
    <!--                            attrParams.attrValueList.length > 0 &&-->
    <!--                            attrParams.attrValueList[0].valueName != ''-->
    <!--                        )-->
    <!--                    "-->
    <!--                >-->
    <!--                    保存-->
    <!--                </el-button>-->
    <!--                <el-button type="primary" @click="cancelSave">取消</el-button>-->
    <!--            </el-form-item>-->
    <!--        </el-form>-->
    <!--    </el-card>-->
</template>

<script setup lang="ts">
import { AttrValue } from '@/api/product/attr/type.ts'
import { nextTick, onUnmounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import AttrCategory from '@/views/product/attr/attrCategory.vue'
import { useAttrStore } from '@/store/modules/attr.ts'
import {
    delAttributeEdit,
    delAttrValueDelete,
    getAttributePageData,
    postAttributeEdit,
    postAttrValueAdd,
    putAttrName,
} from '@/api/production/attr'
import {
    AttrAddType,
    AttrPageDataType,
    Value,
} from '@/api/production/attr/type.ts'

let attrStore = useAttrStore()
let scene = ref<number>(1)
const tagColor = ref<
    { type: 'primary' | 'success' | 'info' | 'warning' | 'danger' }[]
>([
    { type: 'primary' },
    { type: 'success' },
    { type: 'info' },
    { type: 'warning' },
    { type: 'danger' },
])

let inputAttrName = ref<string>('')
let inputAtrr = ref<any>([])
let is_Edit = ref<boolean>(false)
// 添加一个属性 需要的属性数据
let attrParams = reactive<AttrPageDataType>({
    name: '',
    spuId: 0,
    values: [],
})

let attrDatas = ref<AttrPageDataType[]>()

const getAttrPageInfo = async () => {
    let data = await getAttributePageData(
        attrStore.c1Id,
        attrStore.c2Id,
        attrStore.spuId,
    )
    if (data.code == 200) {
        attrDatas.value = data.data.records
    } else {
        ElMessage.error('获取属性数据失败')
    }
}

const search = () => {
    getAttrPageInfo()
}

const tolook = async (row: Value, $index: any) => {
    if (row.value?.trim() == '') {
        attrParams.values.splice($index, 1)
        ElMessage.error('属性值不能为空')
        return
    }
    // 非法情况二
    let repeat = attrParams.values.find((item) => {
        if (item != row) {
            return item.value === row.value
        }
    })
    if (repeat) {
        ElMessage.error('属性值不能重复')
        return
    }
    if (is_Edit.value == true) {
        console.log(row.value)
        // 提交数据
        let data = await postAttrValueAdd(
            attrParams.spuId,
            attrParams.name,
            row.value,
        )
        if (data.code == 200) {
            ElMessage.success('添加成功')
        } else {
            ElMessage.error('添加失败')
            return
        }
    }
    row.flag = false
}
const toedit = (row: AttrValue, $index: number) => {
    row.flag = true
    nextTick(() => {
        inputAtrr.value[$index].focus()
    })
}

const deleteAttrValue = async (index: number) => {
    if (is_Edit.value == true) {
        console.log(index)
        console.log(attrParams.values[index].value)
        let data = await delAttrValueDelete(attrParams.values[index].id)
        if (data.code == 200) {
            ElMessage.success('删除成功')
        } else {
            ElMessage.error('删除失败' + data.msg)
            return
        }
    }

    // 将input框删除
    inputAtrr.value.splice(index, 1)
    attrParams.values.splice(index, 1)
}
const addAttrValue = () => {
    attrParams.values.push({
        value: '',
        flag: true,
    })
    nextTick(() => {
        inputAtrr.value[inputAtrr.value.length - 1].focus()
        // console.log(typeof inputAtrr.value[0])
    })
}

const addAttr = () => {
    is_Edit.value = false
    Object.assign(attrParams, {
        name: '',
        spuId: 0,
        values: [],
    })
    scene.value = 2
    attrParams.spuId = attrStore.spuId as number
}
const cancelAdd = () => {
    scene.value = 1
}
const cancelSave = () => {
    scene.value = 1
}

//保存按钮的回调
const save = async () => {
    if (!inputAttrName.value || inputAttrName.value == '') {
        ElMessage.error('属性名不能为空')
        return
    }
    if (!attrParams.name) {
        // 添加一个属性
        attrParams.name = inputAttrName.value

        let params: AttrAddType = { name: '', spuId: 0, values: [] }
        params.name = attrParams.name
        params.spuId = attrParams.spuId
        params.values = attrParams.values.map((item) => {
            return item.value
        })

        // console.log(params)
        let data = await postAttributeEdit(params)
        if (data.code == 200) {
            ElMessage.success('添加成功')
        } else {
            ElMessage.error('添加失败' + data.msg)
        }
        // console.log(attrParams)
    } else {
        // 编辑'
        if (inputAttrName.value != attrParams.name) {
            ElMessage.success('name改变了')
            let data = await putAttrName(
                attrParams.spuId,
                attrParams.name,
                inputAttrName.value,
            )
            if (data.code != 200) {
                ElMessage.error('修改名字失败')
                return
            } else {
                attrParams.name == inputAttrName.value
            }
        }
    }

    scene.value = 1
    // // 发送请求
    // let result = await reqAddOrUpdateAtrr(attrParams)
    // // console.log(result)
    // if (result.code == 200) {
    //     // 切换场景
    //     scene.value = 1
    //     ElMessage.success({
    //         message: attrParams.id ? '修改成功' : '添加成功',
    //     })
    //     attrStore.getAttrInfoList()
    // } else {
    //     ElMessage.error({
    //         message: attrParams.id ? '修改失败' : '添加失败',
    //     })
    // }
}

// 修改指定的属性的值
const editAttrInfo = (index: number) => {
    is_Edit.value = true
    scene.value = 2
    let valueElement = attrDatas.value[index]
    Object.assign(attrParams, valueElement)
    attrParams.values.forEach((item) => {
        item.flag = false
    })
    inputAttrName.value = attrParams.name
}
// 删除一个已有属性的方法回调
const deleteAttr = async (spuId: number, name: string) => {
    // 删除已有属性的请求
    let result = await delAttributeEdit(spuId, name)
    if (result.code == 200) {
        ElMessage.success('删除成功')
    } else {
        ElMessage.error('删除失败')
        // 获取一份新的数据
    }
    getAttrPageInfo()
}

onUnmounted(() => {
    attrStore.$reset()
})
</script>
<style scoped lang="scss"></style>
