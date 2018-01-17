package bpmn;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public ResponseEntity<byte[]> searchItems(@RequestParam String items){

		String[] itemsArray = items.split(",");

		if (itemsArray.length != 3) {
			return null;
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
		variables.put("termCounter", 0); //3 terms
		variables.put("reportResponse", null);

		System.out.println("Process is starting to run!");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("SentimentAnalysisProcess", variables);

		while (!processInstance.isEnded()) {

		}

		Map<String, Object> serviceVariables = runtimeService.getVariables(processInstance.getId());
		byte[] reportResponse = (byte[]) serviceVariables.get("reportResponse");



		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String filename = "TwitterAnalyzation.pdf";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(reportResponse, headers, HttpStatus.OK);

		return response;
	}
}
