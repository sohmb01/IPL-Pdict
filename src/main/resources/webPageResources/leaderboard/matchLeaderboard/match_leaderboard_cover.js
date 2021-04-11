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


showMatches();



async function showMatches() {

    const response = await fetch("http://103.78.121.142:58080/iplpredict/match/getAllFinishedMatches",
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
    const matches = await response.json();
    console.log("matches:", matches);

 var table = document.getElementById("myTable");

    for (var i = 0; i < matches.length; i++) {
    var num = i+1;
       let match = "Match : " + num.toString() + "  " + matches[i].teamId1 + " vs " + matches[i].teamId2;
      // let url = "http://103.78.121.142:58080/iplpredict/leaderboardPage/matchLeaderboard/match_leaderboard.html?" + matches[i].matchId;
      //console.log("matchid : ",matches[i].matchId);
      //var val = matches[i].matchId;
           let url = "http://103.78.121.142:58080/iplpredict/leaderboardPage/matchLeaderboard/match_leaderboard.html?" + matches[i].matchId;
          var row = `<tr class="text-white">
                 <td id="${matches[i].matchId}">${match}</td>
                 <td><a href=${url} class="btn btn-lg btn-secondary fw-bold border-white bg-white">Show</a></td>`

      table.innerHTML += row;
     // document.getElementById("${matches[i].matchId}").setAttribute("href",url);
  }

  }

//<script>
//   var scrt_var = 10;
//   document.getElementById("link").setAttribute("href",scrt_var);
//</script>
//<a id="link">this is a link</a>
//
//<a id="${matches[i].matchId}" class="btn btn-lg btn-secondary fw-bold border-white bg-white">Show</a>

//href="http://103.78.121.142:58080/iplpredict/leaderboardPage/matchLeaderboard/match_leaderboard.html/"
//onclick="location.href=this.href+'?'+matches[i].matchId;return false;"
//var loc = "https://www.google.com/?xyz="+val;
//            document.write('<a href="' + loc + '"  class="btn btn-lg btn-secondary fw-bold border-white bg-white">Show</a>')
