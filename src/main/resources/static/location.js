var stompClient = null;

function setConnected(connected) {
    $("#enable").prop("disabled", connected);
    $("#disable").prop("disabled", !connected);
    if (connected) {
        $("#nearby").show();
    }
    else {
        $("#nearby").hide();
    }
    $("#userdata").html("");
}

function connect() {
    var socket = new SockJS('/guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/nearby/users', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendLocation() {
    stompClient.send("/app/location", {}, JSON.stringify(
    {
    'longitude': parseFloat($("#longitude").val()),
    'latitude': parseFloat($("#latitude").val()),
    'userId' : "1"
    }
    ));
}

function showGreeting(message) {
    console.log("#############################", message);
    $("#userdata").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#enable" ).click(function() { connect(); });
    $( "#disable" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendLocation(); });
});

