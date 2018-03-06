"use strict";

const registrationForm = document.getElementById("registrationForm");
registrationForm.addEventListener("submit",validateRegistration);

function validateRegistration(e) {
  const password = document.getElementsByName("password")[0].value;
  const confirmPassword =document.getElementsByName("confirmPassword")[0].value;

  if(password !== confirmPassword){
    document.getElementById("errorMessage").innerText = "Your password does not match";
    e.preventDefault();
    return false;
  }
    return confirm("Do you want to register new user?");
}
