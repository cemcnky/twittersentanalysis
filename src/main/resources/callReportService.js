
var termResultsMap = execution.getVariable("termResultsMap");
var payload = {};
payload["analyzedKeywords"] = null;
for (var key in termResultsMap) {
    var value = JSON.parse(termResultsMap[key]);
    print("value: " + termResultsMap[key]);
    if (payload["analyzedKeywords"] != null) {
        payload["analyzedKeywords"] = payload["analyzedKeywords"].concat(value);
    }else {
        payload["analyzedKeywords"] = value;
    }
}

var payloadStringfied = JSON.stringify(payload);
print("payload for report: " + payloadStringfied);

var bodyStream = downloadFile(payloadStringfied);

execution.setVariable("reportResponse", bodyStream);


function downloadFile(payloadStringfied)
{
    with (new JavaImporter(org.jsoup, java.io.BufferedInputStream))
    {
        var doc = Jsoup.connect("http://localhost:5003/getPDF")
            .method(Java.type('org.jsoup.Connection.Method').POST)
            .header('Accept', 'application/pdf')
            .header('Content-Type', 'application/json')
            // .data('filterABC', 'subgroup1')
            .requestBody(payloadStringfied)
            .timeout(30000)
            .ignoreContentType(true) // This is used because Jsoup "approved" content-types parsing is enabled by default by Jsoup
            .execute();

        var bodyStream = doc.bodyStream();

        return bodyStream
    }
}
