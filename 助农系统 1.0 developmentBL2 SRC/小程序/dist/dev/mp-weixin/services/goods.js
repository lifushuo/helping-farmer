"use strict";
const utils_http = require("../utils/http.js");
const getGoodsByIdAPI = (id) => {
  return utils_http.http2({
    method: "GET",
    url: "/user/goods/detail",
    data: { id }
  });
};
exports.getGoodsByIdAPI = getGoodsByIdAPI;
//# sourceMappingURL=goods.js.map
