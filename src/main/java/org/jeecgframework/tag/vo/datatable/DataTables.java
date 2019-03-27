package org.jeecgframework.tag.vo.datatable;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

public class DataTables {
    public int iSortingCols;
    private HttpServletRequest request; // 内部使用的 Request 对象
    private String sEchoParameter = "sEcho";
    // 起始索引和长度
    private String iDisplayStartParameter = "iDisplayStart";
    private String iDisplayLengthParameter = "iDisplayLength";
    // 列数
    private String iColumnsParameter = "iColumns";
    private String sColumnsParameter = "sColumns";
    private String sColumns;
    // 参与排序列数
    private String iSortingColsParameter = "iSortingCols";
    private String iSortColPrefixParameter = "iSortCol_"; // 排序列的索引
    // asc, desc
    private String sSortDirPrefixParameter = "sSortDir_"; // 排序的方向
    // 每一列的可排序性
    private String bSortablePrefixParameter = "bSortable_";
    // 全局搜索
    private String sSearchParameter = "sSearch";
    private String bRegexParameter = "bRegex";
    // 每一列的搜索
    private String bSearchablePrefixParameter = "bSearchable_";
    private String sSearchPrefixParameter = "sSearch_";
    private String bEscapeRegexPrefixParameter = "bRegex_";
    private Integer echo;
    private int displayStart;
    private int displayLength;
    // 参与排序的列
    private int sortingCols;
    // 排序列
    private SortInfo[] sortColumns;
    private int columnCount;
    private ColumnInfo[] columns;
    private String search;
    private Boolean regex;

    public DataTables(HttpServletRequest request) // 用于 MVC 模式下的构造函数
    {
        this.request = request;

        this.echo = this.parseIntParameter(sEchoParameter);
        this.displayStart = this.parseIntParameter(iDisplayStartParameter);
        this.displayLength = this.parseIntParameter(iDisplayLengthParameter);
        this.sortingCols = this.parseIntParameter(iSortingColsParameter);

        this.search = this.parseStringParameter(sSearchParameter);
        this.regex = this.parseStringParameter(bRegexParameter) == "true";

        // 排序的列
        int count = sortingCols;
        this.sortColumns = new SortInfo[count];
        MessageFormat formatter = new MessageFormat("");
        for (int i = 0; i < count; i++) {
            SortInfo sortInfo = new SortInfo();
            sortInfo.setColumnId(this.parseIntParameter(MessageFormat.format("iSortCol_{0}", i)));
            String aString = this.parseStringParameter(MessageFormat.format("sSortDir_{0}", i));
            if ("desc".equals(this.parseStringParameter(MessageFormat.format("sSortDir_{0}", i)))) {
                sortInfo.setSortOrder(SortDirection.asc);
            } else {
                sortInfo.setSortOrder(SortDirection.desc);
            }
            this.sortColumns[i] = sortInfo;
        }

        this.columnCount = this.parseIntParameter(iColumnsParameter);

        count = this.columnCount;
        this.columns = new ColumnInfo[count];

        String[] names = this.parseStringParameter(sColumnsParameter).split(",");
        this.sColumns = this.parseStringParameter(sColumnsParameter);

        for (int i = 0; i < names.length; i++) {
            ColumnInfo col = new ColumnInfo();
            col.setName(names[i]);
            col.setSortable(this.parseBooleanParameter(MessageFormat.format("bSortable_{0}", i)));
            col.setSearchable(this.parseBooleanParameter(MessageFormat.format("bSearchable_{0}", i)));
            col.setSearch(this.parseStringParameter(MessageFormat.format("sSearch_{0}", i)));
            col.setRegex(this.parseStringParameter(MessageFormat.format("bRegex_{0}", i)) == "true");
            columns[i] = col;
        }
    }

    public SortInfo[] getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(SortInfo[] sortColumns) {
        this.sortColumns = sortColumns;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public ColumnInfo[] getColumns() {
        return columns;
    }

    public void setColumns(ColumnInfo[] columns) {
        this.columns = columns;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(Boolean regex) {
        this.regex = regex;
    }

    public Integer getEcho() {
        return echo;
    }

    public int getDisplayStart() {
        return displayStart;
    }

    public int getDisplayLength() {
        return displayLength;
    }

    public int getSortingCols() {
        return sortingCols;
    }

    public void dataTablePram(HttpServletRequest httpRequest) {
        this.request = httpRequest;
    }

    public String getsColumns() {
        return sColumns;
    }

    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }

    private int parseIntParameter(String name) // 解析为整数
    {
        int result = 0;
        String parameter = this.request.getParameter(name);
        if (parameter != null) {
            result = Integer.parseInt(parameter);
        }
        return result;
    }

    private String parseStringParameter(String name) // 解析为字符串
    {
        return this.request.getParameter(name);
    }

    private Boolean parseBooleanParameter(String name) // 解析为布尔类型
    {
        Boolean result = false;
        String parameter = this.request.getParameter(name);
        if (parameter != null) {
            result = Boolean.parseBoolean(parameter);
        }
        return result;
    }

}
