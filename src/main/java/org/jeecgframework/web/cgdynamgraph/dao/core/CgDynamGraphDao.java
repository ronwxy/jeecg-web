package org.jeecgframework.web.cgdynamgraph.dao.core;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdaihao
 */
@Repository("cgDynamGraphDao")
public interface CgDynamGraphDao {

    @Arguments("configId")
    List<Map<String, Object>> queryCgDynamGraphItems(String configId);

    @Arguments("id")
    Map queryCgDynamGraphMainConfig(String id);
}
