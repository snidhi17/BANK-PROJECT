function validateForm() {
    var un = document.querySelector('#customerId').value;
    var pw = document.querySelector('#customerPin').value;
    var username = "username";
    var password = "password";
    if ((un == username) && (pw == password)) {
      alert('You are successfully logged in');
    } else {
      alert("Login was unsuccessful, please check your username and password");
      return false;
    }
  }