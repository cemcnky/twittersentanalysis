import org.apache.ibatis.session.SqlSession;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Starter implements InitializingBean {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ResourcePatternResolver resourceLoader;

    public void afterPropertiesSet() throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();

        List<String> termList = new ArrayList<String>();
        termList.add("microsoft");
        termList.add("apple");
        termList.add("google");

        variables.put("termList", termList); // to change the twitter service url dynamically
        variables.put("resultObjectForReport", null); // payload for report service, produced by all analysis responses incrementally
        variables.put("termCounter", 0); //3 terms

        System.out.println("Process is started to run!");

        runtimeService.startProcessInstanceByKey("SentimentAnalysisProcess", variables);

    }


    public void setRuntimeService(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;
    }


    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    public void setResourceLoader(ResourcePatternResolver resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
