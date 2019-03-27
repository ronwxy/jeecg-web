package org.jeecgframework.core.common.service;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommonService {
    /**
     * 获取所有数据库表
     *
     * @return
     */
    public List<DBTable> getAllDbTableName();

    public Integer getAllDbTableSize();

    public <T> Serializable save(T entity);

    public <T> void saveOrUpdate(T entity);

    public <T> void delete(T entity);

    public <T> void batchSave(List<T> entitys);

    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    <T> T get(Class<T> class1, Serializable id);

    /**
     * 根据实体名称和主键获取实体
     *
     * @param <T>
     * @param entityName
     * @param id
     * @return
     */
    <T> T getEntity(Class entityName, Serializable id);

    /**
     * 根据实体名称和字段名称和字段值获取唯一记录
     *
     * @param <T>
     * @param entityClass
     * @param propertyName
     * @param value
     * @return
     */
    <T> T findUniqueByProperty(Class<T> entityClass,
                               String propertyName, Object value);

    /**
     * 按属性查找对象列表.
     */
    <T> List<T> findByProperty(Class<T> entityClass,
                               String propertyName, Object value);

    /**
     * 加载全部实体
     *
     * @param <T>
     * @param entityClass
     * @return
     */
    <T> List<T> loadAll(final Class<T> entityClass);

    /**
     * 删除实体主键删除
     *
     * @param <T>
     * @param entities
     */
    <T> void deleteEntityById(Class entityName, Serializable id);

    /**
     * 删除实体集合
     *
     * @param <T>
     * @param entities
     */
    <T> void deleteAllEntitie(Collection<T> entities);

    /**
     * 更新指定的实体
     *
     * @param <T>
     * @param pojo
     */
    <T> void updateEntitie(T pojo);

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @param query
     * @return
     */
    <T> List<T> findByQueryString(String hql);

    /**
     * 根据sql更新
     *
     * @param query
     * @return
     */
    int updateBySqlString(String sql);

    /**
     * 根据sql查找List
     *
     * @param <T>
     * @param query
     * @return
     */
    <T> List<T> findListbySql(String query);

    /**
     * 通过属性称获取实体带排序
     *
     * @param <T>
     * @param clas
     * @return
     */
    <T> List<T> findByPropertyisOrder(Class<T> entityClass,
                                      String propertyName, Object value, boolean isAsc);

    <T> List<T> getList(Class clas);

    <T> T singleResult(String hql);

    /**
     * cq方式分页
     *
     * @param cq
     * @param isOffset
     * @return
     */
    PageList getPageList(final CriteriaQuery cq, final boolean isOffset);

    /**
     * 返回DataTableReturn模型
     *
     * @param cq
     * @param isOffset
     * @return
     */
    DataTableReturn getDataTableReturn(final CriteriaQuery cq,
                                       final boolean isOffset);

    /**
     * 返回easyui datagrid模型
     *
     * @param cq
     * @param isOffset
     * @return
     */

    void getDataGridReturn(CriteriaQuery cq,
                           final boolean isOffset);


    /**
     * hqlQuery方式分页
     *
     * @param cq
     * @param isOffset
     * @return
     */
    PageList getPageList(final HqlQuery hqlQuery,
                         final boolean needParameter);

    /**
     * sqlQuery方式分页
     *
     * @param cq
     * @param isOffset
     * @return
     */
    PageList getPageListBySql(final HqlQuery hqlQuery,
                              final boolean isToEntity);

    Session getSession();

    List findByExample(final String entityName,
                       final Object exampleEntity);

    /**
     * 通过cq获取全部实体
     *
     * @param <T>
     * @param cq
     * @return
     */
    <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
                                       Boolean ispage);

    /**
     * 文件上传
     *
     * @param request
     */
    <T> T uploadFile(UploadFile uploadFile);

    HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

    /**
     * 生成XML文件
     *
     * @param fileName XML全路径
     */
    HttpServletResponse createXml(ImportFile importFile);

    /**
     * 解析XML文件
     *
     * @param fileName XML全路径
     */
    void parserXml(String fileName);

    List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree);

    /**
     * 根据模型生成JSON
     *
     * @param all 全部对象
     * @param in 已拥有的对象
     * @param recursive 是否递归加载所有子节点
     * @return List<ComboTree>
     */
    List<ComboTree> comboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive);


    /**
     * 构建树形数据表
     *
     * @param all
     * @param treeGridModel
     * @return
     */
    List<TreeGrid> treegrid(List<?> all, TreeGridModel treeGridModel);

    /**
     * 获取自动完成列表
     *
     * @param <T>
     * @return
     */
    <T> List<T> getAutoList(Autocomplete autocomplete);

    /**
     * 执行SQL
     */
    Integer executeSql(String sql, List<Object> param);

    /**
     * 执行SQL
     */
    Integer executeSql(String sql, Object... param);

    /**
     * 执行SQL 使用:name占位符
     */
    Integer executeSql(String sql, Map<String, Object> param);

    /**
     * 执行SQL 使用:name占位符,并返回执行后的主键值
     */
    Object executeSqlReturnKey(String sql, Map<String, Object> param);

    /**
     * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
     */
    List<Map<String, Object>> findForJdbc(String sql, Object... objs);

    /**
     * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
     */
    Map<String, Object> findOneForJdbc(String sql, Object... objs);

    /**
     * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
     */
    List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

    /**
     * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
     */
    <T> List<T> findObjForJdbc(String sql, int page, int rows,
                               Class<T> clazz);

    /**
     * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
     *
     * @param criteria
     * @param firstResult
     * @param maxResults
     * @return
     * @throws DataAccessException
     */
    List<Map<String, Object>> findForJdbcParam(String sql, int page,
                                               int rows, Object... objs);

    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC
     */
    Long getCountForJdbc(String sql);

    /**
     * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
     */
    Long getCountForJdbcParam(String sql, Object... objs);

    /**
     * 通过hql 查询语句查找对象
     *
     * @param <T>
     * @param query
     * @return
     */
    <T> List<T> findHql(String hql, Object... param);

    <T> List<T> pageList(DetachedCriteria dc, int firstResult,
                         int maxResult);

    <T> List<T> findByDetached(DetachedCriteria dc);

    /**
     * 执行存储过程
     *
     * @param executeSql
     * @param params
     * @return
     */
    <T> List<T> executeProcedure(String procedureSql, Object... params);

    Integer getRowCount(DetachedCriteria criteria);

    Long  getAggregateResult(DetachedCriteria cq);

}
