var HttpStatus = {
  200: "Sign In Successfull",
  201: "Sign In Successfull",
  400: "Bad Request",
  401: "Unauthorized",
  403: "Forbidden",
  404: "Not Found",
  406: "Not Acceptable",
  408: "Request Timeout",
  500: "Internal Server Error",
};

document.getElementById("signin").addEventListener("submit", signin);

async function signin(e) {
  e.preventDefault();

  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  /*
  const data = {
    email: "eve.holt@reqres.in",
    password: "cityslicka",
  };

  */

  var data = {
    "userName": username,
    "password": password,
  };

  postreq(data);
}

async function postreq(data) {
  //console.log("data:", data);
  const response = await fetch("http://103.78.121.142:58080/iplpredict/authentication", {
    method: "POST", // or 'PUT'
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    bootbox.alert({ message: HttpStatus[response.status] });
    throw new Error(message);
  } else {
    bootbox.alert({
      message: HttpStatus[response.status],
    });
    //console.log("status:", response.status);
    //console.log("result:", result);
    var now = new Date();
    now.setTime(now.getTime() + 2 * 3600 * 1000);
    document.cookie =
      "HeaderUsername=" +
      data.userName +
      "; expires=" +
      now.toUTCString() +
      "; path=/";
const token = await response.text();
    document.cookie =
      "AccessToken=" +
      token +
      "; expires=" +
      now.toUTCString() +
      "; path=/";

    window.setTimeout(function () {
      window.location.href =
        "http://103.78.121.142:58080/iplpredict/coverPage/cover.html";
    }, 2000);
  }
}
