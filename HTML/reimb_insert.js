const url = 'http://localhost:3001/';

document.getElementById('request').onclick = insertReimb;
document.getElementById('home').onclick = returnHome;

async function insertReimb() {

    let int_amount = document.getElementById('reimbAmount').value;
    let str_description = document.getElementById('reimbDesc').value;
    let int_author_id = document.cookie.split("=");
    int_author_id = int_author_id[1].split(";");
    int_author_id = int_author_id[0];
    let int_type_id = document.getElementById('reimbType').value;

    let reimb = { int_amount: int_amount, str_description: str_description, user_author: { user_id: int_author_id }, int_type_id: int_type_id };
    console.log(reimb);

    let response = await fetch(url + "insert_reimbursement", {
        method: "POST",
        body: JSON.stringify(reimb)
    });

    if (response.status === 202) {
        alert(response.status + ": Reimbursement successfully requested.")
    } else if (response.status === 401) {
        alert(response.status + " Error: This means you didn't log in.");
    } else {
        alert(response.status + " Error : CHECK CONSOLE.");
    }

}

async function returnHome() {
    role_id = document.cookie.split(";");
    role_id = role_id[1].split("=");
    role_id = role_id[1];
    if (role_id == 1) {
        window.location = "reimb_admin.html";
    } else {
        window.location = "reimb_user.html";
    }

}