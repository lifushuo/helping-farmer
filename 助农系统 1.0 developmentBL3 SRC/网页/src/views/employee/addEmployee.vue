<template>
    <div class="addBrand-container">
        <HeadLable :title="title" :goback="true" />
        <el-card class="container">
            <el-form
                ref="ruleForm"
                :model="ruleFormData"
                :rules="rules"
                :inline="false"
                label-width="180px"
                class="demo-ruleForm"
            >
                <!--          <el-form-item label="员工职级" prop="region">-->
                <!--            <el-select v-model="ruleForm.region" placeholder="请选择品牌名称">-->
                <!--              <el-option label="区域一" value="shanghai"></el-option>-->
                <!--              <el-option label="区域二" value="beijing"></el-option>-->
                <!--            </el-select>-->
                <!--            <el-button @click="submitForm('ruleForm')" type="primary" class="continue" style="margin-left: 10px;" >+新增职级</el-button>-->
                <!--          </el-form-item>-->
                <el-form-item label="账号:" prop="username">
                    <el-input
                        placeholder="请输入账号"
                        maxlength="20"
                        v-model="ruleFormData.username"
                        :disabled="actionType == 'edit' ? true : false"
                    />
                </el-form-item>
                <el-form-item label="员工姓名:" prop="name">
                    <el-input
                        v-model="ruleFormData.name"
                        placeholder="请输入员工姓名"
                        maxlength="12"
                    />
                </el-form-item>
                <el-form-item
                    v-if="actionType != 'edit'"
                    label="密码:"
                    prop="password"
                >
                    <el-input
                        v-model="ruleFormData.password"
                        type="password"
                        autocomplete="off"
                        placeholder="请输入密码"
                    />
                </el-form-item>
                <el-form-item
                    v-if="actionType != 'edit'"
                    label="确认密码:"
                    prop="rePassword"
                >
                    <el-input
                        v-model="ruleFormData.rePassword"
                        type="password"
                        autocomplete="off"
                        placeholder="请输入确认密码"
                    />
                </el-form-item>
                <el-form-item label="手机号:" prop="phone">
                    <el-input
                        v-model="ruleFormData.phone"
                        placeholder="请输入手机号"
                        maxlength="11"
                    />
                </el-form-item>
                <el-form-item label="性别:" prop="sex">
                    <el-radio-group v-model="ruleFormData.sex">
                        <el-radio value="男" label="男" />
                        <el-radio value="女" label="女" />
                    </el-radio-group>
                </el-form-item>
                <el-form-item
                    label="身份证号:"
                    prop="idNumber"
                    class="idNumber"
                >
                    <el-input
                        v-model="ruleFormData.idNumber"
                        placeholder="请输入身份证号"
                        maxlength="20"
                    />
                </el-form-item>
                <div class="subBox address">
                    <!-- <el-form-item> -->
                    <el-button @click="() => router.push('/people/manage')">
                        取消
                    </el-button>
                    <el-button
                        type="primary"
                        :class="{ continue: actionType === 'add' }"
                        @click="submitForm('ruleForm', false)"
                    >
                        保存
                    </el-button>
                    <el-button
                        v-if="actionType == 'add'"
                        type="primary"
                        @click="submitForm('ruleForm', true)"
                    >
                        保存并继续添加
                    </el-button>
                    <!-- </el-form-item> -->
                </div>
            </el-form>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import HeadLable from '@/components/HeadLable/index.vue'
import { useRoute, useRouter } from 'vue-router'
import {
    getEmployeeDetailById,
    postEmployeeAdd,
    putEmployeeEdit,
} from '@/api/employee'
import { ElMessage } from 'element-plus'
// import { SuccessFilled } from "@element-plus/icons-vue";

let title = ref('添加员工')
const actionType = ref('add')
const ruleForm = ref()
type RuleFormType = {
    name: string
    phone: string
    sex: string
    idNumber: string
    username: string
    password: string
    rePassword: string
}

type EditDataType = {
    id: number
    idNumber: string
    name: string
    phone: string
    sex: string
    username: string
    [property: string]: any
}

let ruleFormData = reactive<RuleFormType>({
    name: '鸟',
    phone: '18929796265',
    sex: '男',
    password: '5456',
    rePassword: '',
    idNumber: '440981200212265912',
    username: '1231',
})

let route = useRoute()
const router = useRouter()
const isCellPhone = (val) => {
    if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false
    } else {
        return true
    }
}

const checkPhone = (rule: any, value: any, callback: any) => {
    if (value == '') {
        callback(new Error('请输入手机号'))
    } else if (!isCellPhone(value)) {
        callback(new Error('请输入正确的手机号!'))
    } else {
        callback()
    }
}

const validID = (rule: any, value: any, callback: any) => {
    // let reg = /(^\d{10}$)|(^\d{10}$)|(^\d{10}(\d|X|x)$)/
    if (value == '') {
        callback(new Error('请输入身份证号码'))
    } else {
        callback()
    }
}

const rules = {
    name: [
        {
            required: true,
            validator: (rule, value: any, callback: any) => {
                if (!value) {
                    callback(new Error('请输入员工姓名'))
                } else {
                    callback()
                }
            },
            trigger: 'blur',
        },
    ],
    username: [
        {
            required: true,
            validator: (rule, value: any, callback: any) => {
                if (!value) {
                    callback(new Error('请输入账号'))
                } else {
                    const reg = /^([a-z]|[0-9]|[A-Z]){3,20}$/
                    if (!reg.test(value)) {
                        callback(new Error('账号输入不符，请输入3-20个字符'))
                    } else {
                        callback()
                    }
                }
            },
            trigger: 'blur',
        },
    ],
    phone: [{ required: true, validator: checkPhone, trigger: 'blur' }],
    idNumber: [{ required: true, validator: validID, trigger: 'blur' }],
    password: [{ required: actionType.value == 'add', trigger: 'blur' }],
    rePassword: [
        {
            required: actionType.value == 'add',
            validator: (rule, value: any, callback: any) => {
                // console.log(value)
                if (value == ruleFormData.password) {
                    callback()
                } else {
                    callback(new Error('两次密码不匹配'))
                }
            },
            trigger: 'blur',
        },
    ],
}

const init = async () => {
    const id = route.query.id
    console.log(id)
    let data = await getEmployeeDetailById(id)
    if (data.code == 200) {
        console.log(data.data)
        Object.assign(ruleFormData, data.data)
        ruleFormData.sex = ruleFormData.sex == '0' ? '女' : '男'
        // ruleFormData = data.data
    } else {
        ElMessage.error('获取用户信息失败' + data.msg)
    }
}
const submitForm = async (formName: any, st: any) => {
    ruleForm.value
        .validate()
        .then(async () => {
            if (actionType.value === 'add') {
                const params = {
                    ...ruleFormData,
                    sex: ruleFormData.sex === '女' ? '0' : '1',
                }
                // console.log(params)

                let data = await postEmployeeAdd(params)
                if (data.code == 200) {
                    ElMessage.success('添加成功')
                    // 继续添加
                    if (st == true) {
                        ruleForm.value.refresh()
                        //清空数据
                        ruleFormData.username = ''
                        ruleFormData.sex = ''
                        ruleFormData.name = ''
                        ruleFormData.idNumber = ''
                        ruleFormData.password = ''
                        ruleFormData.rePassword = ''
                        ruleFormData.phone = ''
                    } else {
                        router.back()
                    }
                } else {
                    ElMessage.error('失败：' + data.msg)
                }
            } else {
                let editParm: EditDataType = {}
                editParm.id = route.query.id
                editParm.sex = ruleFormData.sex == '女' ? 0 : 1
                editParm.name = ruleFormData.name
                editParm.phone = ruleFormData.phone
                editParm.username = ruleFormData.username
                editParm.idNumber = ruleFormData.idNumber
                const data = await putEmployeeEdit(editParm)
                if (data.code == 200) {
                    ElMessage.success('更新成功')
                    router.push('/people/manage')
                } else {
                    ElMessage.error('更新失败')
                }
            }
        })
        .catch(() => {
            ElMessage.error('表单有错误')
        })
}

onMounted(() => {
    if (route.query.id && route.query.id != '') {
        actionType.value = 'edit'
        title.value = '修改员工信息'
        init()
    }
})
</script>

<style lang="scss" scoped>
.addBrand {
    &-container {
        margin: 30px;
        margin-top: 0px;

        .HeadLable {
            background-color: transparent;
            margin-bottom: 0px;
            padding-left: 0px;
        }

        .container {
            position: relative;
            z-index: 1;
            //background: #fff;
            padding: 30px;
            border-radius: 4px;
            // min-height: 500px;
            .subBox {
                padding-top: 30px;
                text-align: center;
                border-top: solid 1px grey;
            }
        }

        .idNumber {
            margin-bottom: 39px;
        }

        .el-form-item {
            margin-bottom: 29px;
        }

        .el-input {
            width: 293px;
        }
    }
}
</style>
