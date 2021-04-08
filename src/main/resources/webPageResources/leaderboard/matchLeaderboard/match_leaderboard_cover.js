/*

fetch("https://reqres.in/api/users")
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

/*
function buildTable(data) {
  var table = document.getElementById("myTable");

  for (var i = 0; i < data.length; i++) {
    var row = `<tr>
            <td>${data[i].email}</td>
            <td>${data[i].first_name}</td>
            <td>${data[i].last_name}</td>
            <td>${data[i].avatar}</td>
          </tr>`;
    table.innerHTML += row;
  }
}
*/
