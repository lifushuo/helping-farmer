"use strict";
const utils_http = require("../utils/http.js");
const getCategoryTopAPI = () => {
  return utils_http.http2({
    method: "GET",
    url: "/user/category/list"
  });
};
exports.getCategoryTopAPI = getCategoryTopAPI;
//# sourceMappingURL=category.js.map
