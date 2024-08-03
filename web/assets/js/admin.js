const hamBurger = document.querySelector(".toggle-btn");
const sidebarCustomLinks = document.querySelectorAll(".sidebar-custom__link");

hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});

sidebarCustomLinks.forEach(link => {
  link.addEventListener("click", function (e) {
      e.preventDefault();
      const targetId = this.getAttribute("data-target");
      const targetElement = document.getElementById(targetId);

      if (targetElement) {
        document.querySelectorAll(".content").forEach(content => {
          content.classList.add("d-none");
        });
        targetElement.classList.remove("d-none");
      }
  });
});

document.addEventListener('DOMContentLoaded', function () {
  sidebarCustomLinks.forEach(link => {
    link.addEventListener('click', function (e) {
      e.preventDefault();
      const targetId = this.getAttribute('data-target');
      const targetElement = document.getElementById(targetId);

      if (targetElement) {
        document.querySelectorAll('.content').forEach(content => {
          content.classList.add('d-none');
        });
        targetElement.classList.remove('d-none');
      }
    });
  });
});

document.addEventListener('DOMContentLoaded', function() {
    const customLinks = document.querySelectorAll('.sidebar-custom__link');
    customLinks.forEach(link => {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const target = link.getAttribute('data-target');
            if (target) {
                window.location.href = link.href;
            }
        });
    });
});

 document.addEventListener('DOMContentLoaded', function () {
                const tabLinks = document.querySelectorAll('.tab-link');

                tabLinks.forEach(link => {
                    link.addEventListener('click', function (event) {
                        event.preventDefault();
                        const indexTab = this.getAttribute('href').split('indexTab=')[1];

                        fetch(`LoadController?action=Load&indexTab=${indexTab}`)
                                .then(response => response.text())
                                .then(data => {
                                    document.getElementById('tab-wrapper').innerHTML = data;

                                    // Update the active tab
                                    tabLinks.forEach(link => link.parentElement.classList.remove('active'));
                                    this.parentElement.classList.add('active');
                                })
                                .catch(error => console.error('Error:', error));
                    });
                });
            });
