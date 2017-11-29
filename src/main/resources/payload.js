var data = {
    "tweet": "deneme"
};

smsContent = Object.keys(data).map(function(k) {
    return encodeURIComponent(k) + '=' + encodeURIComponent(data[k])
}).join('&');

smsContent;


