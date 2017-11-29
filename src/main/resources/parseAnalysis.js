// fetch execution variables
var response = connector.getVariable("response");
var json = S(response); //camunda spin, parsing json
var variable = connector.getVariable("payloadForAnalysis");

// var elements = json.elements(); // each element is a tweet block (not text)

print(json);
print(response);
print("hi");
// json.prop("tweets");
var tweets = json.prop("tweets");
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

print(tweets);


termCounter = execution.getVariable("termCounter");
termCounter++;
execution.setVariable("termCounter", termCounter);

// use camunda-spin jsonPath to test if date is a holiday
//!holidays.jsonPath(query).elementList().isEmpty();
