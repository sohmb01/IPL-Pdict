
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

fetch("http://103.78.121.142:58080/iplpredict/currentLeaderboard/getCurrentLeaderboard/2021",
    {
        method: "GET", // or 'PUT'
        headers: {
        HeaderUsername: username,
        AccessToken: accesstoken,
      }
    }
  )
  .then((response) => response.json())
  .then((result) => {
    console.log("result",result);
    console.log("result length",result.length);
//    console.log("result body",result.body);
//    console.log("result body length",result.body.length);

    // buildTable(result);
    var table = document.getElementById("myTable");

    for (var i = 0; i < result.length; i++) {
      var row = `<tr class="text-white">
             <td>${result[i].username}</td>
             <td>${result[i].matchPoints}</td>
           </tr>`;
      table.innerHTML += row;
    }
  });


