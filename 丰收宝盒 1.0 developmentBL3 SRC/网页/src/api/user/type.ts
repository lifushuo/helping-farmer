// 定义用户相关数据的ts类型
// 用户登录接口携带参数的ts类型
export interface LoginFormData {
    username: string
    password: string
}

// 定义全部接口返回数据都拥有的数据类型
export interface ResponseData {
    code: number
    message: string
    ok: boolean
}
//用户登录返回的信息
export interface LoginResponseData extends ResponseData {
    data: string
}

// 定义获取用户信息返回的数据格式
export interface UserInfoResponseData extends ResponseData {
    data: {
        routes: string[]
        buttons: string[]
        roles: string[]
        username: string
        avatar: string
    }
}
