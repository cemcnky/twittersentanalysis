
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
public class GenerateReport implements JavaDelegate{
    public void execute(DelegateExecution delegate) throws Exception {
//TODO: call Report Service
        System.out.println("Spring Bean invoked (CreateReport).");

    }
}
