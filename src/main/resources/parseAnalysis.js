// fetch execution variables
var response = connector.getVariable("response");
var json = S(response); //camunda spin, parsing json

// var elements = json.elements(); // each element is a tweet block (not text)

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


termCounter = connector.getVariable("termCounter");
print("counter:" + termCounter);
termCounter++;
connector.setVariable("termCounter", termCounter);

// use camunda-spin jsonPath to test if date is a holiday
//!holidays.jsonPath(query).elementList().isEmpty();
