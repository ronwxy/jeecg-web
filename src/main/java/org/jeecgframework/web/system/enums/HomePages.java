package org.jeecgframework.web.system.enums;

/**
 * 首页常量
 */
public enum HomePages {
    kindergarten("kindergarten", "kindergartenHomePageController.do?goHomePage");

    /**
     * 名称编码
     */
    private String code;
    /**
     * 首页链接
     */
    private String url;

    HomePages(String code, String url) {
        this.code = code;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
