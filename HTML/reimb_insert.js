const url = 'http://localhost:3001/';

document.getElementById('requestTravel').onclick = function() {insertReimb(1)};
document.getElementById('requestFood').onclick = function() {insertReimb(2)};
document.getElementById('requestLodging').onclick = function() {insertReimb(3)};
document.getElementById('requestOther').onclick = function() {insertReimb(4)};
document.getElementById('home').onclick = returnHome;

async function insertReimb(int_type_id) {

    let int_amount = document.getElementById('reimbAmount').value;
    let str_description = document.getElementById('reimbDesc').value;
    let int_author_id = document.cookie.split("=");
    int_author_id = int_author_id[1].split(";");
    int_author_id = int_author_id[0];

    let reimb = { int_amount: int_amount, str_description: str_description, user_author: { user_id: int_author_id }, int_type_id: int_type_id };

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

    returnHome();
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