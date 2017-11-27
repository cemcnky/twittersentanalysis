
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


public class LoadTwitterData implements JavaDelegate {
    public void execute(DelegateExecution delegate) throws Exception {
//TODO: call Load Twitter Service
        System.out.println("Spring Bean invoked (LoadTwitterData).");

    }

}
