package com.demoone.sys.enums;

/**
 * <pre>
 * 文件类型
 * </pre>
 *
 * @author hailiang.xu
 * @version 1.0
 * @since 2018/6/21 16:41
 */
public enum FileType {

    TXT("txt"),
    DOC("doc"),
    XLS("xls"),
    PDF("pdf"),
    HTML("html"),
    MD("md"),
    FTL("ftl");

    private String value;

    FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean valueEquals(String str) {
        return value.equals(str);
    }
}