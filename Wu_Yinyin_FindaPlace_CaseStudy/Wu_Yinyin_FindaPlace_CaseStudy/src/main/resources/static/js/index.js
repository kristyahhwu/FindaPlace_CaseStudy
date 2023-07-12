// from my CSS&JS SBA

function toggle() {
    let x = document.getElementById("toggle_shows");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function pages() {
    let x = document.getElementById("pages");
    let string = `<li class=\"page-item active\"><a class=\"page-link\" href=\"#\">1</a></li>`;

    for (let i = 2; i < 4; i++) {
        string += `<li class=\"page-item\"><a class=\"page-link\" href=\"#\">${i}</a></li>`;
    }

    x.innerHTML = string;
}

// alert on click
function showAlert() {
    alert('please go to register page');
}

function refresh() {
    window.location.reload();
}

