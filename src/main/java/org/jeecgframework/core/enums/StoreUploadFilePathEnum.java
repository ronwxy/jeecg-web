package org.jeecgframework.core.enums;

import org.jeecgframework.core.util.oConvertUtils;

import java.io.File;

/**
 * 文件上传设置枚举类
 *
 * @author sundonghui
 */
public enum StoreUploadFilePathEnum {
    PHOTOSUCAI("photosucai", "upload" + File.separator + "img" + File.separator + "photosucai"),
    DEFAULT("default", "upload" + File.separator + "files"),
    FACEIMG("faceimg","upload" + File.separator + "faceimg"),
    PHOTO("photo","upload" + File.separator + "photo"),
    MEDICINE("medicine","upload" + File.separator + "medicine"),
    ELECTROCARDIOGRAM("electrocardiogram","upload" + File.separator + "electrocardiogram"),
    FAMILYPHOTO("familyphoto","upload" + File.separator + "familyphoto");

    private String name;
    private String path;

    // 构造方法  
    private StoreUploadFilePathEnum(String name, String path) {
        this.name = name;
        this.path = path;
    }

    //获取路径
    public static String getPath(String name) {
        if (oConvertUtils.isNotEmpty(name)) {
            for (StoreUploadFilePathEnum storePath : StoreUploadFilePathEnum.values()) {
                if (storePath.getName().equals(name)) {
                    return storePath.path;
                }
            }
        }
        return DEFAULT.path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
