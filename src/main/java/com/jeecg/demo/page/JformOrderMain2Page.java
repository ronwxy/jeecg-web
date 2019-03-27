package com.jeecg.demo.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeecg.demo.entity.JformOrderCustomer2Entity;
import com.jeecg.demo.entity.JformOrderMain2Entity;
import com.jeecg.demo.entity.JformOrderTicket2Entity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: 订单主信息
 * @date 2018-03-27 16:21:58
 */
public class JformOrderMain2Page implements java.io.Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderCode;
    /**
     * 订单日期
     */
    @Excel(name = "订单日期", format = "yyyy-MM-dd")
    private java.util.Date orderDate;
    /**
     * 订单金额
     */
    @Excel(name = "订单金额")
    private Double orderMoney;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String content;
    /**
     * 保存-订单机票信息
     */
    @ExcelCollection(name = "订单机票信息")
    private List<JformOrderTicket2Entity> jformOrderTicket2List = new ArrayList<JformOrderTicket2Entity>();
    /**
     * 保存-订单客户信息
     */
    @ExcelCollection(name = "订单客户信息")
    private List<JformOrderCustomer2Entity> jformOrderCustomer2List = new ArrayList<JformOrderCustomer2Entity>();
    @JsonIgnore
    private List<JformOrderMain2Entity> jformOrderMain2List = new ArrayList<JformOrderMain2Entity>();

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    public String getId() {
        return this.id;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  订单号
     */
    public String getOrderCode() {
        return this.orderCode;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  订单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 方法: 取得java.util.Date
     *
     * @return: java.util.Date  订单日期
     */
    public java.util.Date getOrderDate() {
        return this.orderDate;
    }

    /**
     * 方法: 设置java.util.Date
     *
     * @param: java.util.Date  订单日期
     */
    public void setOrderDate(java.util.Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 方法: 取得java.lang.Double
     *
     * @return: java.lang.Double  订单金额
     */
    public Double getOrderMoney() {
        return this.orderMoney;
    }

    /**
     * 方法: 设置java.lang.Double
     *
     * @param: java.lang.Double  订单金额
     */
    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  备注
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  备注
     */
    public void setContent(String content) {
        this.content = content;
    }

    public List<JformOrderTicket2Entity> getJformOrderTicket2List() {
        return jformOrderTicket2List;
    }

    public void setJformOrderTicket2List(List<JformOrderTicket2Entity> jformOrderTicket2List) {
        this.jformOrderTicket2List = jformOrderTicket2List;
    }

    public List<JformOrderCustomer2Entity> getJformOrderCustomer2List() {
        return jformOrderCustomer2List;
    }

    public void setJformOrderCustomer2List(List<JformOrderCustomer2Entity> jformOrderCustomer2List) {
        this.jformOrderCustomer2List = jformOrderCustomer2List;
    }

    public List<JformOrderMain2Entity> getJformOrderMain2List() {
        return jformOrderMain2List;
    }

    public void setJformOrderMain2List(List<JformOrderMain2Entity> jformOrderMain2List) {
        this.jformOrderMain2List = jformOrderMain2List;
    }
}