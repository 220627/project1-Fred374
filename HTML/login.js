const url = 'http://localhost:3001/';

document.getElementById('btnLogin').onclick = loginFunction;

async function loginFunction() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let user = {username:username,password:password};

    console.log(JSON.stringify(user))
    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    })

    if (response.status === 202) {
        let data = await response.json();
        alert("Welcome " + data.first_name);
        window.location = "reimb.html";
    } else {
        alert("Failed to Log In, Check Your Username and Password");
    }
}