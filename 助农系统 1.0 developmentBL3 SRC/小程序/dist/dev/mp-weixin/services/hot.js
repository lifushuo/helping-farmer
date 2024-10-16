"use strict";
const utils_http = require("../utils/http.js");
const getHotPage = (data) => {
  return utils_http.http2({
    method: "GET",
    url: "/user/goods/page",
    data
  });
};
exports.getHotPage = getHotPage;
//# sourceMappingURL=hot.js.map
