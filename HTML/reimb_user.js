const url = 'http://localhost:3001/';
var reimbNum = 0;

document.getElementById('getReimbBtn').onclick = getYourReimbs;
document.getElementById('logout').onclick = logoutFunction;

async function getYourReimbs() {

    let userId = document.cookie.split("=");
    userId = userId[1].split(";");
    userId = userId[0];
    let response = await fetch(url + "user_reimbursements/" + userId);

    if (response.status === 200) {
        document.getElementById("reimbBody").innerHTML = "";
        let data = await response.json();
        let i = 0;
        for (let reimb of data) {
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimb.int_reimb_id;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.int_amount;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.time_submitted;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.str_description;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.user_author.first_name + " " + reimb.user_author.last_name;
            row.appendChild(cell);
            cell = document.createElement("td");
            if (reimb.user_resolver) {
                cell.innerHTML = reimb.user_resolver.first_name + " " + reimb.user_resolver.last_name;
            } else {
                cell.innerHTML = "";
            }
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.int_status_id;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.int_type_id;
            row.appendChild(cell);
            cell = document.createElement("td");
            cell.innerHTML = reimb.int_res_id;
            row.appendChild(cell);
            document.getElementById("reimbBody").appendChild(row);
            i++;
            reimbNum++;
        }
    } else if (response.status === 401) {
        alert(response.status + " Error: This means you didn't log in");
    } else {
        alert(response.status + " Error : CHECK CONSOLE");
    }

}

async function logoutFunction() {

    let response = await fetch(url + "logout", {
        method: "POST",
        credentials: "include"
    })

    if (response.status === 202) {
        alert("Goodbye");
        window.location = "login.html";
    } else {
        alert("Not Currently Logged In");
    }
}