// fetch execution variables
var response = connector.getVariable("response");
var json = S(response); //camunda spin, parsing json

// var elements = json.elements(); // each element is a tweet block (not text)

// json.prop("tweets");
var tweets = json.prop("tweets");
var tweetsElements = tweets.elements();
var jsonObjForReportSer = [];

print("hii");

for (var i = 0; i < tweetsElements.length; i++) {
    var indicoScore = tweetsElements.get(i).prop("indicoScore").value();
    var tweet = tweetsElements.get(i).prop("tweet");
    var tweetText = tweet.prop("tweetText").value();

    var item = {};
    item ["positivity"] = parseFloat(indicoScore);
    item ["tweet"] = tweetText;

    jsonObjForReportSer.push(item);

}

var jsonStringfied = JSON.stringify(jsonObjForReportSer);

print(tweets);
print(jsonStringfied);

/*

for (var i = 0; i < elements.length; i++) {

    var tweetTextProperty = elements.get(i).prop("tweetText"); //tweetText property of the element

    tweets.push(tweetTextProperty);

    //print(tweetTextProperty);
}

payload = encodeURIComponent("tweet=");

for (index = 0; index < tweets.length; ++index) {
    payload += encodeURIComponent(tweets[index]) + '&';
}

*/


var resultObjectForReport = connector.getVariable("resultObjectForReport");
if (resultObjectForReport == null) {
    resultObjectForReport = [];
}
var concat = resultObjectForReport.concat(jsonObjForReportSer);
connector.setVariable("resultObjectForReport", concat);

termCounter = connector.getVariable("termCounter");
print("counter:" + termCounter);
termCounter++;
connector.setVariable("termCounter", termCounter);

// use camunda-spin jsonPath to test if date is a holiday
//!holidays.jsonPath(query).elementList().isEmpty();
jsonStringfied;