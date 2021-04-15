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
if (!username || !accesstoken) {
  bootbox.alert({
    message: "Please Sign IN",
  });

  window.setTimeout(function () {
    window.location.href =
      "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
  }, 2000);
}

var usernamedata = document.getElementById("usernamedata");
usernamedata.innerHTML = `Hello! ${username}`;



showMatches();
var getmatch = document.getElementById("getmatch");

//showmatchprediction();

var showmatch = document
                  .getElementById("showmatchprediction");
showmatch.onclick = showmatchprediction;

var showtournament = document
                  .getElementById("showtournamentprediction");
showtournament.onclick = showtournamentprediction;



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
    var num = i+1;
    let match = "Match " + num +" : " + obj.teamId1 + " vs " + obj.teamId2;
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
       <tr>
              <td>Team Win : </td>
              <td>${result.teamWin}</td>
        </tr>
        <tr>
              <td>Wickets : </td>
              <td>${result.wickets}</td>
        </tr>
        <tr>
              <td>Team 1 Score : </td>
              <td>${result.teamLow1} - ${result.teamHigh1}</td>
        </tr>
        <tr>
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
         <tr>
                <td>Winning Team : </td>
                <td>${result.winningTeam}</td>
          </tr>
          <tr>
                <td>Semi Finalists : </td>
                <td>${result.semiFinalists[0]}
                    ${result.semiFinalists[1]}
                    ${result.semiFinalists[2]}
                    ${result.semiFinalists[3]}
                </td>
          </tr>
          <tr>
                <td>Orange Cap : </td>
                <td>${result.orangeCaps[0]}
                    ${result.orangeCaps[1]}
                    ${result.orangeCaps[2]}
                    ${result.orangeCaps[3]}
                </td>
          </tr>
          <tr>
                <td>Purple Cap : </td>
                <td>${result.purpleCaps[0]}
                    ${result.purpleCaps[1]}
                    ${result.purpleCaps[2]}
                    ${result.purpleCaps[3]}
                </td>
          </tr>
          `;

  table.innerHTML = row;
}
