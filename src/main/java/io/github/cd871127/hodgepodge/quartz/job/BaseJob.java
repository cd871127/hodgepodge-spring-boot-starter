package io.github.cd871127.hodgepodge.quartz.job;

import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

/**
 * job的基础类 extends QuartzJobBean
 */
public abstract class BaseJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected final void executeInternal(JobExecutionContext context){
        JobKey jobKey = context.getJobDetail().getKey();
        logger.info("Start job: {}.{}", jobKey.getGroup(), jobKey.getName());
        if (logger.isDebugEnabled()) {
            Map<String, Object> paraMap = context.getJobDetail().getJobDataMap().getWrappedMap();
            logger.debug("Job parameter: {}", JSONObject.toJSONString(paraMap));
        }
        doTask(context);
        logger.info("Finish job: {}.{}", jobKey.getGroup(), jobKey.getName());
    }

    protected abstract void doTask(JobExecutionContext context);
}
