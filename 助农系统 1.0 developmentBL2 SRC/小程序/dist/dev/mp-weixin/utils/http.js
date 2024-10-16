"use strict";
const common_vendor = require("../common/vendor.js");
require("../stores/index.js");
const stores_modules_member = require("../stores/modules/member.js");
const baseURL = "https://pcapi-xiaotuxian-front-devtest.itheima.net";
const httpInterceptor = {
  //拦截前出发
  invoke(options) {
    var _a;
    if (!options.url.startsWith("http")) {
      options.url = baseURL + options.url;
    }
    options.timeout = 1e5;
    options.header = {
      ...options.header,
      "source-client": "miniapp"
    };
    const memberStore = stores_modules_member.useMemberStore();
    const token = (_a = memberStore.profile) == null ? void 0 : _a.token;
    if (token) {
      options.header.Authorization = token;
    }
  }
};
common_vendor.index.addInterceptor("request", httpInterceptor);
common_vendor.index.addInterceptor("uploadFile", httpInterceptor);
const http2 = (options) => {
  var _a;
  if (!options.url.startsWith("http")) {
    options.url = "http://127.0.0.1:8080" + options.url;
  }
  options.timeout = 1e5;
  options.header = {
    ...options.header,
    "source-client": "miniapp"
  };
  const memberStore = stores_modules_member.useMemberStore();
  const token = (_a = memberStore.profile) == null ? void 0 : _a.token;
  if (token) {
    options.header.Authorization = token;
  }
  return new Promise((resolve, reject) => {
    common_vendor.index.request({
      ...options,
      success(result) {
        if (result.statusCode >= 200 && result.statusCode < 300) {
          resolve(result.data);
        } else if (result.statusCode == 401) {
          const memberStore2 = stores_modules_member.useMemberStore();
          memberStore2.clearProfile();
          common_vendor.index.navigateTo({ url: "/pages/login/login" });
          common_vendor.index.showToast({
            icon: "none",
            title: "获取用户信息失败",
            duration: 2e3
          });
          reject(result);
        } else {
          common_vendor.index.showToast({
            icon: "none",
            title: result.data.msg || "请求错误",
            duration: 2e3
          });
          reject(result);
        }
      },
      //响应失败
      fail(result) {
        common_vendor.index.showToast({
          icon: "none",
          title: "网络错误，换个网络试试"
        });
        reject(result);
      }
    });
  });
};
const http = (options) => {
  return new Promise((resolve, reject) => {
    common_vendor.index.request({
      ...options,
      success(result) {
        if (result.statusCode >= 200 && result.statusCode < 300) {
          resolve(result.data);
        } else if (result.statusCode == 401) {
          const memberStore = stores_modules_member.useMemberStore();
          memberStore.clearProfile();
          common_vendor.index.navigateTo({ url: "/pages/login/login" });
          common_vendor.index.showToast({
            icon: "none",
            title: "获取用户信息失败",
            duration: 2e3
          });
          reject(result);
        } else {
          common_vendor.index.showToast({
            icon: "none",
            title: result.data.msg || "请求错误",
            duration: 2e3
          });
          reject(result);
        }
      },
      //响应失败
      fail(result) {
        common_vendor.index.showToast({
          icon: "none",
          title: "网络错误，换个网络试试"
        });
        reject(result);
      }
    });
  });
};
exports.http = http;
exports.http2 = http2;
//# sourceMappingURL=http.js.map
