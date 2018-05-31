package io.github.cd871127.hodgepodge.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * job的基础类 extends QuartzJobBean
 */
public abstract class BaseJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected final void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Start job: "+getClass().getName());
        doTask(context);
        logger.info("Finish job: "+getClass().getName());
    }

    protected abstract void doTask(JobExecutionContext context) throws JobExecutionException;
}
