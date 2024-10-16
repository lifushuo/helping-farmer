// 统一管理咱们项目用户相关的接口
import request from '@/utils/request'
import type {
    LoginFormData,
    LoginResponseData,
    UserInfoResponseData,
} from './type'

// 统一管理接口
enum API {
    LOGIN_URL = '/admin/acl/index/login',
    USERINFO_URL = '/admin/acl/index/info',
    LOGOUT_URL = '/admin/acl/index/logout',
}

// 暴露请求函数
// 登录接口方法
export const reqLogin = (data: LoginFormData) =>
    request.post<any, LoginResponseData>(API.LOGIN_URL, data)
// 获取用户信息接口方法
export const reqUserInfo = () =>
    request.get<any, UserInfoResponseData>(API.USERINFO_URL)
// 退出登录的接口
export const reqLogout = () => request.post<any, any>(API.LOGOUT_URL)
