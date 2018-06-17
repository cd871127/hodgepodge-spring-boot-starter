package io.github.cd871127.hodgepodge.quartz.job;

import org.quartz.JobExecutionContext;

import java.util.Map;

public abstract class SimpleJob extends BaseJob {

    @SuppressWarnings("unchecked")
    @Override
    protected final void doTask(JobExecutionContext context) {
        Map paraMap = context.getJobDetail().getJobDataMap().getWrappedMap();
        doTask(paraMap);
    }

    protected abstract void doTask(Map<String, String> jobParam);
}
