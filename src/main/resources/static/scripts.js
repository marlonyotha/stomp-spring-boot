let stompClient = null;
let notificationCount = 0;

$(document).ready(function () {
  console.log("Index page is ready");
  connect();

  $("#send").click(function () {
    sendMessage();
  });

  $("#send-private").click(function () {
    sendPrivateMessage();
  });

  $("#notifications").click(function () {
    resetNotificationCount();
  });
});

function connect() {
  let socket = new SockJS("/connect");
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("Connected: " + frame);

    updateNotificationDisplay();
    stompClient.subscribe("/topic/messages", function (message) {
      showMessage(JSON.parse(message.body).content);
    });

    stompClient.subscribe("/user/topic/private-messages", function (message) {
      showMessage(JSON.parse(message.body).content);
    });

    stompClient.subscribe("/topic/global-notifications", function (message) {
      notificationCount = notificationCount + 1;
      updateNotificationDisplay();
    });

    stompClient.subscribe("/user/topic/private-notifications", function () {
      notificationCount = notificationCount + 1;
      updateNotificationDisplay();
    });

    stompClient.subscribe("/topic/errors", function (message) {
      alert("Error " + message.body);
    });
  });
}

function showMessage(message) {
  $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
  console.log("sending message");
  stompClient.send(
    "/app/message",
    {},
    JSON.stringify({ messageContent: $("#message").val() })
  );
}

function sendPrivateMessage() {
  console.log("sending private message");
  stompClient.send(
    "/app/private-message",
    {},
    JSON.stringify({ messageContent: $("#private-message").val() })
  );
}

function updateNotificationDisplay() {
  if (notificationCount == 0) {
    $("#notifications").hide();
  } else {
    $("#notifications").show();
    $("#notifications").text(notificationCount);
  }
}

function resetNotificationCount() {
  notificationCount = 0;
  updateNotificationDisplay();
}
