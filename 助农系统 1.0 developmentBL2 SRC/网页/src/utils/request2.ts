import axios from 'axios'
import { ElMessage } from 'element-plus'

export const request2 = axios.create({
    // baseURL: 'http://127.0.0.1:4523/m1/4456928-4103000-default',
    baseURL: 'http://127.0.0.1:8080',
    timeout: 40000,
})
request2.interceptors.response.use(
    (response) => {
        // 成功回调
        // 简化数据
        return response.data
    },
    (error) => {
        // 失败函数的回调
        // 定义一个变量：存储网络错误信息
        console.log(error)
        let message = ''
        // http状态码
        const status = error.response.status
        switch (status) {
            case 401:
                message = 'TOKEN过期'
                break
            case 403:
                message = '无权访问'
                break
            case 404:
                message = '请求地址错误'
                break
            case 500:
                message = '服务器发生问题'
                break
            default:
                message = '网络出现问题'
                break
        }
        // 提示错误信息
        ElMessage({
            type: 'error',
            message,
        })
        return Promise.reject(error)
    },
)
