// fetch execution variables

var response = connector.getVariable("response");

var json = S(response); //camunda spin, parsing json

var elements = json.elements(); // each element is a tweet block (not text)

print(json);

var tweets = [];

for (var i = 0; i < elements.length; i++) {

    var tweetTextProperty = elements.get(i).prop("tweetText"); //tweetText property of the element

    tweets.push(tweetTextProperty);

    //print(tweetTextProperty);
}

payload = "tweet=";

for (index = 0; index < tweets.length; ++index) {
    payload += encodeURIComponent(tweets[index]) + '&' + "tweet=";
}


print(payload);

payloadForAnalysis = "tweet=I+hate+it";
payloadForAnalysis; //payloadForAnalysis=payload
