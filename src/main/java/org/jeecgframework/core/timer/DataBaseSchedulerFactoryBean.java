package org.jeecgframework.core.timer;

import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * 读取数据库 然后判断是否启动任务
 *
 * @author JueYue
 * @version 1.0
 * @date 2013-9-22
 */
public class DataBaseSchedulerFactoryBean extends SchedulerFactoryBean {

    @Autowired
    private TimeTaskServiceI timeTaskService;

    /**
     * 读取数据库判断是否开始定时任务
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

//		String[] trigerrNames = this.getScheduler().getTriggerNames(Scheduler.DEFAULT_GROUP);
        Scheduler scheduler = this.getScheduler();
        List<String> trigerrNames = scheduler.getTriggerGroupNames();
        TSTimeTaskEntity task;

        for (String trigerrName : trigerrNames) {
            task = timeTaskService.findUniqueByProperty(TSTimeTaskEntity.class, "taskId", trigerrName);
            //数据库查询不到的定时任务或者定时任务的运行状态不为1时，都停止
            //TASK #327 定时器任务默认未启动
            if (task == null || !"1".equals(task.getIsStart())) {
//				this.getScheduler().pauseTrigger(trigerrName,Scheduler.DEFAULT_GROUP);
                scheduler.pauseTrigger(new TriggerKey(trigerrName));
            }

        }
    }

}
