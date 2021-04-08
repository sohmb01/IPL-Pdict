// Example starter JavaScript for disabling form submissions if there are invalid fields
var HttpStatus = {
  200: "OK",
  201: "Submitted Successfully",
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
let username = Cookies.get("HeaderUsername");
let accesstoken = Cookies.get("AccessToken");
if (!username || !accesstoken) {
  bootbox.alert({
    message: "Please Sign IN",
  });

  window.setTimeout(function () {
    window.location.href =
      "http://103.78.121.142:58080/iplpredict/coverPage/cover.html";
  }, 1000);
}
var usernamedata = document.getElementById("usernamedata");
var tournament_year = document.getElementById("tournament_year");
var udata = `<input type="text" class="form-control" id="username" placeholder="${username}" value="${username}" required disabled></input>
`;
var tdata = `<input type="text" class="form-control" id="tournament_year" placeholder="2021" value="2021" required disabled></input>
`;
usernamedata.innerHTML = udata;
tournament_year.innerHTML = tdata;

document
  .getElementById("tournament_prediction")
  .addEventListener("submit", submitPredictions);

async function submitPredictions(e) {
  e.preventDefault();

  // let username = document.getElementById("username").value;
  let tournamentYear = 2021;
  let orangeCaps = [];
  orangeCaps.push(document.getElementById("oc1").value);
  orangeCaps.push(document.getElementById("oc2").value);
  orangeCaps.push(document.getElementById("oc3").value);
  let purpleCaps = [];
  purpleCaps.push(document.getElementById("pc1").value);
  purpleCaps.push(document.getElementById("pc2").value);
  purpleCaps.push(document.getElementById("pc3").value);
  let semiFinalists = [];
  semiFinalists.push(document.getElementById("sf1").value);
  semiFinalists.push(document.getElementById("sf2").value);
  semiFinalists.push(document.getElementById("sf3").value);
  semiFinalists.push(document.getElementById("sf4").value);
  let winningTeam = document.getElementById("team_win").value;

  let predictions = {
    "userName": username,
    "tournamentYear": tournamentYear,
    "winningTeam": winningTeam,
    "semiFinalists": semiFinalists,
    "orangeCaps": orangeCaps,
    "purpleCaps": purpleCaps,
  };
  let flag = await isPredictionAlreadySubmitted(username, tournamentYear);
  if (flag) {
    putreq(predictions);
    console.log("prediction udpation Request");
  } else {
    postreq(predictions);
    console.log("New prediction");
  }
}

async function isPredictionAlreadySubmitted(username, tournamentYear) {

  const prediction = await fetch("http://103.78.121.142:58080/iplpredict/tournamentPrediction/getTournamentPrediction/" + username + "/" + tournamentYear,
  {
      method: "GET", // or 'PUT'
      headers: {
        Accept: "application/json, text/plain, */*",
        "Content-Type": "application/json",
        HeaderUsername: username,
        AccessToken: accesstoken,
      }});

   if (prediction.ok) {
      return true;
    } else {
     return false;
      }
}


async function postreq(data) {
  const response = await fetch("http://103.78.121.142:58080/iplpredict/tournamentPrediction/createTournamentPrediction", {
    method: "POST", // or 'PUT'
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json",
      HeaderUsername: username,
      AccessToken: accesstoken,
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
      closeButton: false,
    });
  }
}

async function putreq(data) {
  const response = await fetch("http://103.78.121.142:58080/iplpredict/tournamentPrediction/updateTournamentPrediction", {
    method: "PUT",
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json",
      HeaderUsername: username,
      AccessToken: accesstoken,
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
      closeButton: false,
    });
  }

}
