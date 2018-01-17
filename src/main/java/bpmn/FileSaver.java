package bpmn;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.File;
import java.io.FileOutputStream;

public class FileSaver implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		File downloadfile = new File("/home/cemcnky/testnew1.pdf");
		byte[] output = (byte[]) execution.getVariable("reportResponse");
		FileOutputStream fos = new FileOutputStream(downloadfile);
		fos.write(output);
		fos.flush();
		fos.close();

	}
}
