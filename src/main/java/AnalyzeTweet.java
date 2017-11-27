
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
public class AnalyzeTweet implements JavaDelegate{

    public void execute(DelegateExecution delegate) throws Exception {
//TODO: call Analyse Service
        System.out.println("Spring Bean invoked (AnalyzeTweet).");

    }
}
