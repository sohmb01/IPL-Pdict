// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()


document.getElementById('createUser').addEventListener('submit',createUser);

function addPost(e){
  e.preventDefault();


  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;
  let full_name = document.getElementById('fullName').value;
  let fav_team = document.getElementById('team').value;

//const data = { username: 'example' };

    fetch('http://103.78.121.142:58080/iplpredict/user/createUser/', {
      method: 'POST', // or 'PUT'
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({username:username, password:password, full_name:full_name, fav_team:fav_team}),
    })
      .then(response => response.json())
      .then(data => {
          console.log('Success:', data);
        })
      .catch((error) => {
          console.error('Error:', error);
        })
    };