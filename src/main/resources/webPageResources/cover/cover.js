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
let username = Cookies.get("HeaderUsername");
let accesstoken = Cookies.get("AccessToken");

var usernamedata = document.getElementById("usernamedata");
usernamedata.innerHTML = `Hello! ${username}`;



showMatches();
var getmatch = document.getElementById("getmatch");

showmatchprediction();

//document
//  .getElementById("showmatchprediction")
//  .addEventListener("submit", showmatchprediction);
var showtournament= document.getElementById("showtournamentprediction");
console.log(showtournament);
document
  .getElementById("showtournamentprediction")
  .addEventListener("submit", showtournamentprediction);

/*
if (!username || !accesstoken) {
  bootbox.alert({
    message: "Please Sign IN",
  });

  window.setTimeout(function () {
    window.location.href =
      "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
  }, 2000);
}
*/

async function showMatches() {
  const response = await fetch(
    "http://103.78.121.142:58080/iplpredict/match/getAllFinishedMatches",
    {
      method: "GET", // or 'PUT'
      headers: {
        Accept: "application/json, text/plain, */*",
        "Content-Type": "application/json",
        HeaderUsername: username,
        AccessToken: accesstoken,
      },
    }
  );

  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;

    // throw new Error(message);
    if (response.status != 401) {
      bootbox.alert({ message: HttpStatus[response.status] });
    }
  }
  console.log("resp:", response);
  const matches = await response.json();
  //console.log("matches:", matches.data);

  getmatch.innerHTML = `<option value="">Choose...</option>`;

  for (var i = 0; i < matches.length; i++) {
    var obj = matches[i];
    var matchId = obj.matchId;
    let match = "Team 1: " + obj.teamId1 + " vs " + "Team 2: " + obj.teamId2;
    var row = `<option value="${matchId}">
                ${match}
               </option>`;
    getmatch.innerHTML += row;
  }
}

var matchId = getmatch.value;
// "http://103.78.121.142:58080/iplpredict/matchPrediction/getMatchPrediction/"+ username + "/" + matchId
async function showmatchprediction() {
  const response = await fetch(
    "http://103.78.121.142:58080/iplpredict/matchPrediction/getMatchPrediction/rdx/s2021-1",
    {
      method: "GET", // or 'PUT'
      headers: {
        Accept: "application/json, text/plain, */*",
        "Content-Type": "application/json",
        HeaderUsername: username,
        AccessToken: accesstoken,
      },
    }
  );

  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    bootbox.alert({ message: HttpStatus[response.status] });
    // throw new Error(message);
    if (response.status === 401) {
      window.setTimeout(function () {
        window.location.href =
          "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
      }, 2000);
    }
  }

  console.log("resp:", response);
  const result = await response.json();
  //  console.log("matches:", matches);

  var table = document.getElementById("myTable1");

  var row = `
       <tr class="text-white">
              <td>Team Win : </td>
              <td>${result.teamWin}</td>
        </tr>
        <tr class="text-white">
              <td>Wickets : </td>
              <td>${result.wickets}</td>
        </tr>
        <tr class="text-white">
              <td>Team 1 Score : </td>
              <td>${result.teamLow1} - ${result.teamHigh1}</td>
        </tr>
        <tr class="text-white">
              <td>Team 2 Score : </td>
              <td>${result.teamLow2} - ${result.teamHigh2}</td>
        </tr>

        `;

  table.innerHTML = row;
}

async function showtournamentprediction() {
  const response = await fetch(
    "http://103.78.121.142:58080/iplpredict/tournamentPrediction/getTournamentPrediction/rdx/2021",
    {
      method: "GET", // or 'PUT'
      headers: {
        Accept: "application/json, text/plain, */*",
        "Content-Type": "application/json",
        HeaderUsername: username,
        AccessToken: accesstoken,
      },
    }
  );

  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    bootbox.alert({ message: HttpStatus[response.status] });
    // throw new Error(message);
    if (response.status === 401) {
      window.setTimeout(function () {
        window.location.href =
          "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
      }, 2000);
    }
  }

  console.log("resp:", response);
  const result = await response.json();
  //  console.log("matches:", matches);

  var table = document.getElementById("myTable2");

  var row = `
         <tr class="text-white">
                <td>Winning Team : </td>
                <td>${result.winningTeam}</td>
          </tr>
          <tr class="text-white">
                <td>Semi Finalists : </td>
                <td>${result.semiFinalists[1]}</td>
          </tr>
          <tr class="text-white">
                <td>Orange Cap : </td>
                <td>${result.semiFinalists[2]}</td>
          </tr>
          <tr class="text-white">
                <td>Purple Cap : </td>
                <td>${result.semiFinalists[2]}</td>
          </tr>

          `;

  table.innerHTML = row;
}
