package org.jeecgframework.web.cgform.service.impl.config;

import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.impl.config.util.ColumnMeta;


/**
 * SQLSERVER的表工具类
 */
public class TableSQLServerHandleImpl implements DbTableHandleI {


    @Override
    public String getAddColumnSql(ColumnMeta columnMeta) {
        return " ADD  " + getAddFieldDesc(columnMeta) + ";";
    }


    @Override
    public String getReNameFieldName(ColumnMeta columnMeta) {
        //sp_rename 'TOA_E_ARTICLE.version','processVersion','COLUMN';
        return "  sp_rename '" + columnMeta.getTableName() + "." + columnMeta.getOldColumnName() + "', '" + columnMeta.getColumnName() + "', 'COLUMN';";
        //return "ALTER COLUMN  "+columnMeta.getOldColumnName() +" "+getRenameFieldDesc(columnMeta)+";";
    }


    @Override
    public String getUpdateColumnSql(ColumnMeta cgformcolumnMeta, ColumnMeta datacolumnMeta) {
        return " ALTER COLUMN  " + getUpdateFieldDesc(cgformcolumnMeta, datacolumnMeta) + ";";
    }


    @Override
    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String result = "";
        if ("varchar".equalsIgnoreCase(dataType)) {
            result = "string";
        } else if ("float".equalsIgnoreCase(dataType)) {
            result = "double";
        } else if ("int".equalsIgnoreCase(dataType)) {
            result = "int";
        } else if ("Date".equalsIgnoreCase(dataType)) {
            result = "date";
        } else if ("Datetime".equalsIgnoreCase(dataType)) {
            result = "date";
        } else if ("numeric".equalsIgnoreCase(dataType)) {
            result = "bigdecimal";
        } else if ("varbinary".equalsIgnoreCase(dataType)) {
            result = "blob";
        }
        return result;
    }


    @Override
    public String dropTableSQL(String tableName) {
        return " DROP TABLE " + tableName + " ;";
    }


    @Override
    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String getUpdateFieldDesc(ColumnMeta cgfromcolumnMeta, ColumnMeta datacolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " text" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " varbinary(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        }
        //result += (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault())?" DEFAULT "+cgfromcolumnMeta.getFieldDefault():" ");
        return result;
    }

    private String getAddFieldDesc(ColumnMeta cgfromcolumnMeta) {
        String result = "";
        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("bigdecimal".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " numeric(" + cgfromcolumnMeta.getColumnSize() + "," + cgfromcolumnMeta.getDecimalDigits() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("text".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " text" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("blob".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " varbinary(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        }//update-end--Author:liuht  Date:20131223
        //result += (StringUtils.isNotEmpty(cgfromcolumnMeta.getFieldDefault())?" DEFAULT "+cgfromcolumnMeta.getFieldDefault():" ");
        return result;
    }

    private String getRenameFieldDesc(ColumnMeta cgfromcolumnMeta) {
        String result = "";

        if ("string".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " varchar(" + cgfromcolumnMeta.getColumnSize() + ")" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("date".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " datetime" + " " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("int".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " int " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if ("double".equalsIgnoreCase(cgfromcolumnMeta.getColunmType())) {
            result = cgfromcolumnMeta.getColumnName() + " float " + ("Y".equals(cgfromcolumnMeta.getIsNullable()) ? "NULL" : "NOT NULL");
        }
        return result;
    }


    @Override
    public String getCommentSql(ColumnMeta columnMeta) {
        StringBuffer commentSql = new StringBuffer("EXECUTE ");
        if (StringUtil.isEmpty(columnMeta.getOldColumnName())) {
            commentSql.append("sp_addextendedproperty");
        } else {
            commentSql.append("sp_updateextendedproperty");
        }
        commentSql.append(" N'MS_Description', '");
        commentSql.append(columnMeta.getComment());
        commentSql.append("', N'SCHEMA', N'dbo', N'TABLE', N'");
        commentSql.append(columnMeta.getTableName());
        commentSql.append("', N'COLUMN', N'");
        commentSql.append(columnMeta.getColumnName() + "'");
        return commentSql.toString();
    }


    @Override
    public String getSpecialHandle(ColumnMeta cgformcolumnMeta,
                                   ColumnMeta datacolumnMeta) {
        return null;
    }

}
