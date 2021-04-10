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


var matchId = location.search.substring(1);
console.log("matchID url: ",matchId);
showMatches();



async function showMatches() {

    const response = await fetch("http://103.78.121.142:58080/iplpredict/matchLeaderboard/getMatchLeaderboard/" + matchId,
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
              bootbox.alert({ message: HttpStatus[response.status] });
             // throw new Error(message);
                  if(response.status === 401){
                  window.setTimeout(function () {
                        window.location.href =
                          "http://103.78.121.142:58080/iplpredict/signInPage/signin.html";
                      }, 2000);}
            }

    console.log("resp:", response);
    const result = await response.json();
  //  console.log("matches:", matches);

  var table = document.getElementById("myTable");

     for (var i = 0; i < result.length; i++) {
       var row = `<tr class="text-white">
              <td>${result[i].username}</td>
              <td>${result[i].matchPoints}</td>
            </tr>`;
       table.innerHTML += row;
     }

  }

