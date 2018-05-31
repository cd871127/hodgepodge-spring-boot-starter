package io.github.cd871127.hodgepodge.quartz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "com.paic.amc.quartz")
public class QuartzJobProperties {

    private List<Map<String, Object>> jobs;

    public List<Map<String, Object>> getJobs() {
        return jobs;
    }

    public void setJobs(List<Map<String, Object>> jobs) {
        this.jobs = jobs;
    }
}


