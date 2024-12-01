// const navbar = document.querySelector(".menu");
// const menuIcons = document.querySelectorAll(".menu-link");
// const burgerIcon = document.querySelector(".hamburger-menu");
//
// burgerIcon.addEventListener("click", () => {
//     navbar.classList.toggle("menu-hide");
//     menuIcons.forEach(icon => {
//         icon.classList.toggle("icons-hide");
//     })
// });

const arrowIndicators = document.querySelectorAll(".arrow-indicator");
const dropdowns = document.querySelectorAll(".dropdown");

arrowIndicators.forEach((arrow, index) => {
    arrow.addEventListener("click", () => {
        // Hide all dropdowns
        dropdowns.forEach((dropdown, i) => {
            if (i !== index) {
                dropdown.classList.remove("dropdown-show");
                arrowIndicators[i].classList.remove("arrow-clicked");
            }
        });
        // Toggle the clicked dropdown
        dropdowns[index].classList.toggle("dropdown-show");
        arrow.classList.toggle("arrow-clicked");
    });
});

document.addEventListener("click", (event) => {
    dropdowns.forEach((dropdown, index) => {
        if (!dropdown.contains(event.target) && !arrowIndicators[index].contains(event.target)) {
            dropdown.classList.remove("dropdown-show");
            arrowIndicators[index].classList.remove("arrow-clicked");
        }
    });
});