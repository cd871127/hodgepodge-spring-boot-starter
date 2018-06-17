//package io.github.cd871127.hodgepodge.quartz.context;
//
//import io.github.cd871127.hodgepodge.quartz.service.QuartzService;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import javax.annotation.Resource;
//
///**
// * app启动后,会调用这个类,
// */
//public class QuartzJobStartupListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Resource
//    private QuartzService quartzService;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        //启动所有在配置文件中的job
//        quartzService.startStaticTasks();
//    }
//}
