package com.jeecg.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;

/**
 * @author onlineGenerator
 * @version V1.0
 * @Title: Entity
 * @Description: excel导入导出测试表
 * @date 2018-06-15 15:46:09
 */
@Entity
@Table(name = "jeecg_demo_excel", schema = "")
@SuppressWarnings("serial")
public class JeecgDemoExcelEntity implements java.io.Serializable {
    /**
     * 主键
     */

    private String id;
    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 15)
    private String name;

    /**
     * 生日
     */
    @Excel(name = "生日", format = "yyyy-MM-dd")
    private java.util.Date birthday;

    /**
     * 性别
     */
    @Excel(name = "性别", width = 15, dicCode = "sex")
    private String sex;

    /**
     * 关联部门
     */
    @Excel(name = "部门", dictTable = "t_s_depart", dicCode = "id", dicText = "departname")
    private String depart;

    /**
     * 测试替换
     */
    @Excel(name = "测试替换", width = 15, replace = {"男_1", "女_0"})
    private String fdReplace;


    /**
     * 测试转换
     */
    @Excel(name = "测试转换", width = 15, exportConvert = true, importConvert = true)
    private String fdConvert;

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  主键
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
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
     * @return: java.lang.String  性别
     */
    @Column(name = "SEX", nullable = true, length = 3)
    public String getSex() {
        return this.sex;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birthday", nullable = true, length = 20)
    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  关联部门
     */
    @Column(name = "DEPART", nullable = true, length = 36)
    public String getDepart() {
        return this.depart;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  关联部门
     */
    public void setDepart(String depart) {
        this.depart = depart;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  测试替换
     */
    @Column(name = "FD_REPLACE", nullable = true, length = 255)
    public String getFdReplace() {
        return this.fdReplace;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  测试替换
     */
    public void setFdReplace(String fdReplace) {
        this.fdReplace = fdReplace;
    }

    /**
     * 方法: 取得java.lang.String
     *
     * @return: java.lang.String  测试转换
     */
    @Column(name = "FD_CONVERT", nullable = true, length = 255)
    public String getFdConvert() {
        return this.fdConvert;
    }

    /**
     * 方法: 设置java.lang.String
     *
     * @param: java.lang.String  测试转换
     */
    public void setFdConvert(String fdConvert) {
        this.fdConvert = fdConvert;
    }

    /**
     * 转换值示例： 在该字段值的后面加上元
     *
     * @return
     */
    public String convertgetFdConvert() {
        return this.fdConvert + "元";
    }

    /**
     * 转换值示例： 替换掉excel单元格中的"元"
     *
     * @return
     */
    public void convertsetFdConvert(String fdConvert) {
        this.fdConvert = fdConvert.replace("元", "");
    }
}