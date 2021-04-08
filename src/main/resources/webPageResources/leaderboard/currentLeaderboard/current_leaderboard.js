
/*

fetch("http://103.78.121.142:58080/iplpredict/currentLeaderboard/getCurrentLeaderboard/2021",
  method: "GET", // or 'PUT'
      headers: {
         HeaderUsername: Cookies.get("HeaderUsername"),
        AccessToken: Cookies.get("AccessToken"),
      }
  )
  .then((response) => response.json())
  .then((result) => {
    console.log(result.data);
    // buildTable(result);
    var table = document.getElementById("myTable");

    for (var i = 0; i < result.data.length; i++) {
      var row = `<tr class="text-white">
             <td>${result.data[i].email}</td>
             <td>${result.data[i].first_name}</td>
           </tr>`;
      table.innerHTML += row;
    }
  });



*/
