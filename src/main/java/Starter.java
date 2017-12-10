import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;

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

        final Map<String, Object> map = new HashMap<String, Object>();
        for (String term : termList) {
        map.put(term, null);
        }

        variables.put("termList", termList);
        variables.put("termResultsMap", map);
        variables.put("termCounter", 0); //3 terms
        variables.put("reportResponse", null);

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
