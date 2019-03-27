package org.jeecgframework.jwt.util.menu;

/**
 * 接口返回状态码
 *
 * @author scott
 */
public enum ResponseMessageCodeEnum {

    /**
     * 成功
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    SUCCESS("0"),
    /**
     * 错误
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    ERROR("-1"),
    /**
     * 校验失败
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    VALID_ERROR("1000"),
    /**
     * 保存成功
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    SAVE_SUCCESS("r0001"),
    /**
     * 更新成功
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    UPDATE_SUCCESS("r0002"),
    /**
     * 移除成功
     *
     * @author Clintonfang
     * @date 2018-08-15 20:22:18
     */
    REMOVE_SUCCESS("r0003");

    private String code;

    ResponseMessageCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
