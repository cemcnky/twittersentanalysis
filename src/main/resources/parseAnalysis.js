// fetch execution variables
var response = connector.getVariable("response");

//print("response from analysis service: " + response);
var json = S(response); //camunda spin, parsing json

var tweets = json.prop("tweets");
var jsonObjForReportSer = [];

var termCounter = connector.getVariable("termCounter");
var termResultsMap = connector.getVariable("termResultsMap");
var termList = connector.getVariable("termList");
var term = termList.get(termCounter);

var item = {};
item ["keyword"] = term;
item ["tweets"] = JSON.parse(tweets.elements());

jsonObjForReportSer.push(item);


var jsonStringified = JSON.stringify(jsonObjForReportSer);
termResultsMap.put(term.toString(), jsonStringified);
//print(jsonStringified);

connector.setVariable("termResultsMap", termResultsMap);

print("counter:" + termCounter);
termCounter++;
connector.setVariable("termCounter", termCounter);

