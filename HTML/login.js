const url = 'http://localhost:3001/';

document.getElementById('btnLogin').onclick = loginFunction;

async function loginFunction() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    let user = { username: username, password: password };

    let response = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    })

    if (response.status === 202) {
        let data = await response.json();
        document.cookie = "user_id=" + data.user_id;
        document.cookie = "role_id=" + data.role.role_id;
        alert("Welcome " + data.first_name);
        if (data.role.role_id == 1) {
            window.location = "reimb_admin.html";
        } else {
            window.location = "reimb_user.html";
        }
    } else {
        alert("Failed to Log In, Check Your Username and Password");
    }
}