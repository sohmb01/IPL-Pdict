// Example starter JavaScript for disabling form submissions if there are invalid fields
var HttpStatus = {
  200: "Submitted Successfully",
  201: "Submitted Successfully",
  400: "Bad Request",
  401: "Unauthorized, Please Sign In Again",
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
      "http://103.78.121.142:58080/iplpredict/landingPage/landing.html";
  }, 1000);
}
var usernamedata = document.getElementById("usernamedata");
var udata = `<input type="text" class="form-control" id="username" placeholder="${username}" value="${username}" required disabled></input>
`;
usernamedata.innerHTML = udata;

var flag = false;
showMatches();
var dropdown = document.getElementById("dropdown");
//dropdown.addEventListener("click", showMatches);
/*document.getElementById("dropdown").onclick = function () {
  showMatches();
};*/
//document.getElementById("dropdown").addEventListener("select", showMatches);
document
  .getElementById("match_prediction")
  .addEventListener("submit", submitPredictions);

async function showMatches() {
  if (flag === false) {
    const response = await fetch("http://103.78.121.142:58080/iplpredict/match/getAllActiveMatches",
    {
          method: "GET", // or 'PUT'
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json",
            HeaderUsername: username,
            AccessToken: accesstoken,
          }});

            if (!response.ok) {
              const message = `An error has occured: ${response.status}`;

             // throw new Error(message);
                  if(response.status != 401){
                   bootbox.alert({ message: HttpStatus[response.status] });
            }

    console.log("resp:", response);
    const matches = await response.json();
    console.log("matches:", matches.data);

dropdown.innerHTML = `<option value="">Choose...</option>`;



  for (var i = 0; i < matches.length; i++) {
    var obj = matches[i];
    var matchId = obj.matchId;
    let match = obj.teamId1 + " vs " + obj.teamId2;
    var row = `<option value="${matchId}">
              ${match}
             </option>`;
    dropdown.innerHTML += row;
  }
/*
    for (var i = 0; i < matches.data.length; i++) {
      var obj = matches.data[i];
      console.log("obj:", obj);
      var matchId = obj.email;
      var match = obj.first_name + " vs " + obj.last_name;
      console.log("mmmm:", match);
      console.log("idididi:", matchId);
      var row = `<option value="${matchId}">
              ${match}
             </option>`;
      dropdown.innerHTML += row;
    }*/
  }
  flag = true;
}

async function submitPredictions(e) {
  e.preventDefault();

  var matchId = dropdown.value;
  //let matchId = document.getElementsByTagName(<option value=match/>);
  let teamWin = document.getElementById("team_win").value;
  let teamHigh1 = document.getElementById("teamhigh1").value;
  let teamLow1 = document.getElementById("teamlow1").value;
  let teamHigh2 = document.getElementById("teamhigh2").value;
  let teamLow2 = document.getElementById("teamlow2").value;
  let wickets = document.getElementById("wickets").value;

  let predictions = {
    "userName": username,
    "matchId": matchId,
    "teamWin": teamWin,
    "teamHigh1": teamHigh1,
    "teamLow1": teamLow1,
    "teamHigh2": teamHigh2,
    "teamLow2": teamLow2,
    "wickets": wickets,
  };

  let flag = await isPredictionAlreadySubmitted(username, matchId);
    if (flag) {
    putreq(predictions);
    console.log("prediction udpation Request");
  } else {
    postreq(predictions);
    console.log("New prediction");
  }
}

async function isPredictionAlreadySubmitted(username, matchId) {
  const prediction = await fetch("http://103.78.121.142:58080/iplpredict/matchPrediction/getMatchPrediction/" + username + "/" + matchId,
    {
        method: "GET", // or 'PUT'
        headers: {
          Accept: "application/json, text/plain, */*",
          "Content-Type": "application/json",
          HeaderUsername: username,
          AccessToken: accesstoken,
        }});
         /* if (!prediction.ok) {
            const message = `An error has occured: ${prediction.status}`;
            bootbox.alert({ message: HttpStatus[prediction.status] });
           // throw new Error(message);
                if(prediction.status === 401){
                window.setTimeout(function () {
                      window.location.href =
                        "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
                    }, 2000);}
          } else {
            bootbox.alert({
              message: HttpStatus[prediction.status],
              closeButton: false,
            });
          }*/
   if (prediction.ok) {
      return true;
    } else {
     return false;
      }
}

async function postreq(data) {
  const response = await fetch("http://103.78.121.142:58080/iplpredict/matchPrediction/createMatchPrediction", {
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
    //throw new Error(message);
        if(response.status === 401){
        window.setTimeout(function () {
              window.location.href =
                "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
            }, 2000);}
  } else {
    bootbox.alert({
      message: HttpStatus[response.status],
      closeButton: false,
    });
  }
}

async function putreq(data) {
  const response = await fetch("http://103.78.121.142:58080/iplpredict/matchPrediction/updateMatchPrediction", {
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
    //throw new Error(message);
    if(response.status === 401){
    window.setTimeout(function () {
          window.location.href =
            "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
        }, 2000);}
  } else {
    bootbox.alert({
      message: HttpStatus[response.status],
      closeButton: false,
    });
  }

}

// function createMatchPrediction(e) {
//   e.preventDefault();

//   let username = document.getElementById("username").value;
//   let matchId = document.getElementById("matchId").value;
//   let teamWin = document.getElementById("team_win").value;
//   let teamHigh1= document.getElementById("teamhigh1").value;
//   let teamLow1= document.getElementById("teamlow1").value;
//   let teamHigh2= document.getElementById("teamhigh2").value;
//   let teamLow2= document.getElementById("teamlow2").value;
//   let wickets= document.getElementById("wickets").value;

//   //const data = { username: 'example' };

//   fetch("https://jsonplaceholder.typicode.com/posts", {
//     method: "POST", // or 'PUT'
//     headers: {
//       Accept: "application/json, text/plain, */*",
//       "Content-Type": "application/json",
//       HeaderUsername: ,
//       AccessToken: ,
//     },
//     body: JSON.stringify({
//       username: username,
//       matchId: matchId,
//       teamWin: teamWin,
//       teamHigh1: teamHigh1,
//       teamLow1: teamLow1,
//       teamHigh2: teamHigh2,
//       teamLow2: teamLow2,
//       wickets: wickets
//     }),
//   })
//     .then((response) => response.json())
//     .then((data) => {
//       console.log("Success:", data);
//     })
//     .catch((error) => {
//       console.error("Error:", error);
//     });
// }

// function myFunction() {
//   var x = document.getElementById("username").async;
//   document.getElementById("test").innerHTML = x;
// }
