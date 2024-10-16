<template>
    <div class="login_container">
        <el-row>
            <el-col :span="12" :xs="0">占位的位子</el-col>
            <el-col :span="12" :xs="24">
                <el-form
                    class="login_form"
                    :model="loginForm"
                    :rules="rules"
                    ref="loginFroms"
                >
                    <h1>Hello</h1>
                    <h2>欢迎来到平台</h2>
                    <el-form-item prop="username">
                        <el-input
                            :prefix-icon="User"
                            v-model="loginForm.username"
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input
                            type="password"
                            :prefix-icon="Lock"
                            v-model="loginForm.password"
                            show-password
                        ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button
                            class="login_btn"
                            type="primary"
                            size="default"
                            @click="login"
                            :loading="loading"
                        >
                            登录
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import { User, Lock } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { ElNotification } from 'element-plus'
// 引入用户仓库
import useUserStore from '@/store/modules/user'
// 引入获取当前时间的函数
import { getTime } from '@/utils/time'
let userStore = useUserStore()
let $router = useRouter()
let $route = useRoute()
// 登录加载
let loading = ref(false)

// 收集账号与密码的数据
let loginForm = reactive({
    username: 'admin',
    password: '111111',
})
// 校验用户账号
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
const checkUsername = (rule: any, value: any, callback: any) => {
    if (value.length >= 5) {
        callback()
    } else {
        callback(new Error('密码长度至少为5位'))
    }
}
// 校验用户密码
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
const checkPassword = (rule: any, value: any, callback: any) => {
    if (value.length >= 5) {
        callback()
    } else {
        callback(new Error('账号长度为4-15位'))
    }
}
let loginFroms = ref()
// 定义表单校验需要的配置对象
const rules = reactive({
    username: [
        {
            validator: checkUsername,
            trigger: 'change',
        },
    ],
    password: [
        {
            required: true,
            validator: checkPassword,
            trigger: 'change',
        },
    ],
})

// 登录按钮回调
const login = async () => {
    // 保证所有的表单项都正确才能发送请求
    await loginFroms.value.validate()

    // 加载效果：开始加载
    loading.value = true
    // 点击登录按钮之后都干什么
    // 通知仓库发登录请求
    // 请求成功 ->首页展示数据
    // 请求失败 ->弹出登录失败信息
    try {
        // 保证登录成功，这里可以写 then
        await userStore.userLogin(loginForm)
        // 使用编程式导航
        // 判断登录的时候有没有query参数 有参数就往query哪里跳转
        let redirect: any = $route.query.redirect

        await $router.push({ path: redirect || '/' })
        ElNotification({
            type: 'success',
            title: `HI,${getTime()}好`,
            message: '登录成功',
        })
        // 登录效果消失
        loading.value = false
    } catch (error) {
        loading.value = false
        ElNotification({
            type: 'error',
            message: (error as Error).message,
        })
    }
}
</script>

<style scoped lang="scss">
.login_container {
    width: 100%;
    height: 100vh;
    background: url('@/assets/images/background.jpg') no-repeat;
    background-size: cover;
}

.login_form {
    position: relative;
    width: 80%;
    top: 30vh;
    left: 10%;
    background: url('@/assets/images/login_form.png') no-repeat;
    background-size: cover;
    padding: 40px;

    h1 {
        color: white;
        font-size: 40px;
    }

    h2 {
        font-size: 20px;
        color: white;
        margin: 20px;
    }

    .login_btn {
        width: 100%;
    }
}
</style>
: string: (arg0: Error | undefined) => void
