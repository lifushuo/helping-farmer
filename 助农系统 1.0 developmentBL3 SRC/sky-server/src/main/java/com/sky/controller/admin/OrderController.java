package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.CancelOrderDTO;
import com.sky.dto.EmployeeEditPasswordDto;
import com.sky.dto.RejectOrderDTO;
import com.sky.entity.Employee;
import com.sky.entity.OrderSearch;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.service.OrderService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import com.sky.vo.OrderAmountVO;
import com.sky.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */
@RestController
@RequestMapping("/admin/order")

@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 各个状态的订单数量统计
     */
    @GetMapping("/statistics")
    Result getOrderAmount() {

        OrderAmountVO o = orderService.getOrderAmount();

        return Result.success(o);
    }


    /**
     * 查询订单详情
     */
    @GetMapping("/details/{id}")
    Result getOrderDetails(@PathVariable Integer id) {

        OrderVO o = orderService.getOrder(id);

        return Result.success(o);
    }

    /**
     * 订单搜索
     */
    @GetMapping("/conditionSearch")
    public Result searchOrders(
            @RequestParam(required = false, name = "beginTime") String beginTime,
            @RequestParam(required = false, name = "end") String endTime,
            @RequestParam(required = false) String number,
            @RequestParam(required = true) Integer page,
            @RequestParam(required = true) Integer pageSize,
            @RequestParam(required = false) String phone,
            @RequestParam(required = true) Integer status) {

//        // 转换时间参数
//        Date beginDate = null;
//        Date endDate = null;
//        try {
//            if (beginTime != null && !beginTime.isEmpty()) {
//                beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
//            }
//            if (endTime != null && !endTime.isEmpty()) {
//                endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
//            }
//        } catch (ParseException e) {
//            return Result.error("日期格式错误");
//        }
        Date beginDate = null;
        if (beginTime != null) {
            //beginDate =new Date(beginTime);
            beginDate = getDate(beginTime);

        }

        Date endDate = null;
        if (endTime != null) {
            endDate = getDate(endTime);
        }

        // 构建查询参数对象
        OrderSearch orderSearch = new OrderSearch(null, null, number, phone, status);

        // 执行查询
        PageResult pageResult = orderService.searchOrders(orderSearch, page, pageSize);

        return Result.success(pageResult);
    }

    @PutMapping("/cancel")
    Result cancelOrder(@RequestBody CancelOrderDTO cancelOrderDTO) {
        Result result = orderService.cancelOrder(cancelOrderDTO);
        return result;
    }

    @PutMapping("/complete/{id}")
    public Result completeOrder(@PathVariable("id") Integer id) {
        Result result = orderService.setOrdercomplete(id);
        return result;
    }

    @PutMapping("/rejection")
    Result rejectOrder(@RequestBody RejectOrderDTO RejectOrderDTO) {
        Result result = orderService.rejectOrder(RejectOrderDTO);
        return result;
    }

    @PutMapping("/confirm/{id}")
    public Result confirmOrder(@PathVariable("id") Integer id) {
        Result result = orderService.setOrderConfirm(id);
        return result;
    }

    @PutMapping("/delivery/{id}")
    public Result deliverOrder(@PathVariable("id") Integer id) {
        Result result = orderService.deliverOrder(id);
        return result;
    }


    Date getDate(String dateTime) {
        // 输入字符串形式的日期时间
        String dateStr = dateTime;

        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 将字符串转换为 Date 对象
            Date beginDate = dateFormat.parse(dateStr);
            // 输出 Date 对象
            System.out.println("转换后的 Date 对象: " + beginDate);
            return beginDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}

