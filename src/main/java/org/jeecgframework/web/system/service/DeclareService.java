package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSAttachment;

import java.util.List;

/**
 * @author 张代浩
 */
public interface DeclareService extends CommonService {

    public List<TSAttachment> getAttachmentsByCode(String businessKey, String description);

}
