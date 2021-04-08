// Example starter JavaScript for disabling form submissions if there are invalid fields
var HttpStatus = {
  200: "OK",
  201: "User Successfully Created",
  400: "Bad Request",
  401: "Unauthorized",
  403: "Forbidden",
  404: "Not Found",
  406: "Not Acceptable",
  408: "Request Timeout",
  500: "Internal Server Error",
};

(function () {
  "use strict";

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll(".needs-validation");

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener(
      "submit",
      function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add("was-validated");
      },
      false
    );
  });
})();

document.getElementById("createUser").addEventListener("submit", createUser);

async function createUser(e) {
  e.preventDefault();

  let username = document.getElementById("username").value;
  let password = document.getElementById("password").value;
  let fullName = document.getElementById("fullName").value;
  let favTeam = document.getElementById("team").value;

  //const data = { username: 'example' };
  var data = JSON.stringify({
    "userName": username,
    "password": password,
    "fullName": fullName,
    "favTeam": favTeam,
  });

  postreq(data);
}

async function postreq(data) {
  //console.log("data:", data);
  const response = await fetch("http://103.78.121.142:58080/iplpredict/user/createUser", {
    method: "POST", // or 'PUT'
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json",
    },
    body: data,
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
    //const result = await response.json();
    //console.log("result:", result);
    window.setTimeout(function () {
      window.location.href =
        "http://103.78.121.142:58080/iplpredict/landingPage/landing.html";
    }, 5000);

    // window.location.replace("../cover/cover.html");
  }
}
