package bpmn;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BpmnController {

	@Autowired
	private RuntimeService runtimeService;

	@RequestMapping(value = "/bpmn", method = RequestMethod.GET)
	public ResponseEntity<byte[]> searchItems(@RequestParam String items) {

		String[] itemsArray = items.split(",");

		int termsSize = itemsArray.length;
		if (termsSize < 1) {
			throw new RuntimeException("There must be at least 1 term!");
		}

		List<String> termList = new ArrayList<String>();
		termList.addAll(Arrays.asList(itemsArray));

		Map<String, Object> variables = new HashMap<String, Object>();

		final Map<String, Object> map = new HashMap<String, Object>();
		for (String term : termList) {
			map.put(term, null);
		}

		variables.put("termList", termList);
		variables.put("termResultsMap", map);
		variables.put("termCounter", 0); // till all terms are processed (termsSize)
		variables.put("termsSize", termsSize);
		variables.put("reportResponse", null);

		System.out.println("Process is starting to run!");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SentimentAnalysisProcess", variables);

		byte[] reportResponse = (byte[]) ((ProcessInstanceWithVariablesImpl) processInstance).getVariables().get("reportResponse");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "TwitterAnalyzation.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(reportResponse, headers, HttpStatus.OK);

		return response;
	}
}
