var getAllTask = function () {

};

var ajax = function (url, method, params, success) {
    var headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };
    $.ajax({
        url: "/api"+url,
        type: method,
        headers: headers,
        data: params,
        success: success
    });
};