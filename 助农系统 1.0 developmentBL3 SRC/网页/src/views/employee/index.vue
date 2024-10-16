<template>
    <!--	<HeadLable v-if="false"></HeadLable>-->
    <div class="dashboard-container">
        <div class="container">
            <div class="tableBar">
                <label style="margin-right: 5px">员工姓名：</label>
                <el-input
                    v-model="input"
                    placeholder="请输入员工姓名"
                    style="width: 15%"
                    clearable
                    @keyup.enter.native="initFun"
                />
                <el-button
                    type="info"
                    style="margin-left: 10px"
                    class="normal-btn continue"
                    @click="init(true)"
                >
                    查询
                </el-button>
                <el-button
                    type="primary"
                    style="float: right"
                    @click="addEmployeeHandle('add')"
                >
                    + 添加员工
                </el-button>
            </div>
            <el-table
                :data="tableData"
                stripe
                v-if="tableData.length"
                class="tableBox"
                style="margin-top: 10px"
            >
                <el-table-column prop="name" label="员工姓名" />
                <el-table-column prop="username" label="账号" />
                <el-table-column prop="phone" label="手机号" />
                <el-table-column label="账号状态">
                    <template #default="{ row }">
                        <el-tag
                            :type="
                                (row as TableDataType).status == '0'
                                    ? 'info'
                                    : 'success'
                            "
                        >
                            {{
                                String((row as TableDataType).status) === '0'
                                    ? '禁用'
                                    : '启用'
                            }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="updateTime" label="最后操作时间" />
                <el-table-column label="操作" width="160" align="center">
                    <template #default="{ row }">
                        <el-button
                            link
                            size="small"
                            class="blueBug"
                            :class="{
                                'disabled-text':
                                    (row as TableDataType).username == 'admin',
                            }"
                            :disabled="
                                (row as TableDataType).username == 'admin'
                            "
                            @click="
                                addEmployeeHandle(
                                    (row as TableDataType).id,
                                    (row as TableDataType).username,
                                )
                            "
                        >
                            修改
                        </el-button>
                        <el-button
                            link
                            :type="
                                (row as TableDataType).status == '0'
                                    ? 'primary'
                                    : 'danger'
                            "
                            :disabled="
                                (row as TableDataType).username == 'admin'
                            "
                            size="small"
                            class="non"
                            :class="{
                                'disabled-text':
                                    (row as TableDataType).username == 'admin',
                                blueBug: (row as TableDataType).status == '0',
                                delBut: (row as TableDataType).status != '0',
                            }"
                            @click="statusHandle(row as TableDataType)"
                        >
                            {{
                                (row as TableDataType).status == '1'
                                    ? '禁用'
                                    : '启用'
                            }}
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                class="pageList"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="counts"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
        </div>
    </div>
    <!--    <AddEmployee />-->
</template>

<script lang="ts" setup>
import { ref, onMounted, nextTick } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getAllEmployeePage, postEmployeeStatus } from '@/api/employee'
import { ElMessage } from 'element-plus'

type TableDataType = {
    id?: number
    name: string
    username: string
    phone: string
    status: string //"0" 表示启用 “1” 表示禁用
    updateTime?: string
}
// 声明响应式变量
const input = ref('')
const counts = ref(0)
const page = ref(1)
const pageSize = ref(5)
let tableData = ref<TableDataType[]>([
    {
        id: 123,
        name: 'zhangsan',
        username: 'admin1',
        phone: '189299999',
        status: '0',
        updateTime: '2022-10-10',
    },
    {
        id: 123,
        name: 'zhangsan',
        username: 'admin2',
        phone: '189299999',
        status: '1',
        updateTime: '2022-10-10',
    },
])
const isSearch = ref(false)

// 获取路由实例和路由对象
const route = useRoute()
const router = useRouter()

const init = async (isFlesh?: boolean) => {
    // console.log("init");

    let data = await getAllEmployeePage(page.value, pageSize.value, input.value)
    if (data.code == 200) {
        tableData.value = data.data.records
        counts.value = data.data.total
        input.value = ''
        ElMessage.success('获取成功')
    } else {
        ElMessage.error('错误: ' + data.msg)
    }
}
const initFun = () => {
    console.log('initFun')
}

// 添加员工
const addEmployeeHandle = (st: string | number, username?: string) => {
    if (st == 'add') {
        //新增员工
        router.push('/people/manage/add')
    } else {
        router.push('/people/manage/add?id=' + st)
    }
}

// 状态修改
const statusHandle = async (row: any) => {
    if (row.username === 'admin') {
        return
    }
    console.log(row.status)
    let data = await postEmployeeStatus(row.status == '0' ? 1 : 0)
    if (data.code == 200) {
        ElMessage.success('状态更新成功')
        row.status = row.status == '0' ? '1' : '0'

        nextTick()
    } else {
        ElMessage.error('状态更新失败')
    }
}

// 处理页码变化
const handleSizeChange = (val: any) => {
    pageSize.value = val
    init()
}

const handleCurrentChange = (val: any) => {
    page.value = val
    init()
}

// 生命周期钩子函数
onMounted(() => {
    init()
})
</script>

<style lang="scss" scoped>
.disabled-text {
    color: #bac0cd !important;
}
</style>
