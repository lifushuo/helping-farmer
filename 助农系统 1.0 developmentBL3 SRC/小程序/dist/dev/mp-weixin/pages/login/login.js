"use strict";
const common_vendor = require("../../common/vendor.js");
const services_login = require("../../services/login.js");
require("../../stores/index.js");
const stores_modules_member = require("../../stores/modules/member.js");
require("../../utils/http.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "login",
  setup(__props) {
    let code = "";
    common_vendor.onLoad(async () => {
      const res = await common_vendor.wx$1.login();
      code = res.code;
    });
    const onGetphonenumber = async (ev) => {
      const encryptedData = ev.detail.encryptedData;
      const iv = ev.detail.iv;
      const res = await services_login.postLoginWxMinAPI({
        code,
        encryptedData,
        iv
      });
      console.log(res);
    };
    const loginSucess = (res) => {
      common_vendor.index.showToast({
        title: "登录成功"
      });
      const memberStore = stores_modules_member.useMemberStore();
      memberStore.setProfile(res);
      setTimeout(() => {
        common_vendor.index.navigateBack();
      }, 500);
    };
    const onGetphonenumberSimple = async () => {
      const res = await services_login.postLoginWxMinSimpleAPI("18929796265");
      loginSucess(res.result);
    };
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(onGetphonenumber),
        b: common_vendor.o(onGetphonenumberSimple)
      };
    };
  }
});
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/login/login.vue"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=login.js.map
