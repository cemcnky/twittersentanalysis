
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.HashMap;
import java.util.Map;

public class Starter implements InitializingBean {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ResourcePatternResolver resourceLoader;

    public void afterPropertiesSet() throws Exception {

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("url", "http://localhost:8081/tweets?search=microsoft");
        variables.put("termCounter", 0);
        variables.put("payloadForAnalysis", " sad");

        runtimeService.startProcessInstanceByKey("SentimentAnalysisProcess", variables);

        System.out.println("Process is started to run!");
    }



    public void setRuntimeService(RuntimeService runtimeService) {

        this.runtimeService = runtimeService;
    }

    public void setResourceLoader(ResourcePatternResolver resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
