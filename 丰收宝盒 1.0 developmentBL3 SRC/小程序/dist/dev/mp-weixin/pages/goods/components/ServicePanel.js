"use strict";
const common_vendor = require("../../../common/vendor.js");
const _sfc_main = /* @__PURE__ */ common_vendor.defineComponent({
  __name: "ServicePanel",
  emits: ["close"],
  setup(__props, { emit }) {
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(($event) => emit("close"))
      };
    };
  }
});
const Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/OneDrive/桌面/uniapp小程序小兔/erabbit-uni-app-vue3-ts-master/src/pages/goods/components/ServicePanel.vue"]]);
wx.createComponent(Component);
//# sourceMappingURL=ServicePanel.js.map
