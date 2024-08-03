/* HEADRER FIXED */
window.onscroll = function () {
  if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
    document.getElementById("header-fixed").style.transform = "translate(-50%, 0)";
  } else {
    document.getElementById("header-fixed").style.transform = "translate(-50%, -100%)";
  }
};

const a = document.querySelector.bind(document);
const aa = document.querySelectorAll.bind(document);

const navs = aa(".nav-item");

const navActive = a(".nav-item.active");

navs.forEach((nav, index) => {
  nav.onclick = function () {
    a(".nav-item.active").classList.remove("active");

    this.classList.add("active");
  };
});

/* PRODUCT SHOP */
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const tabs = $$("#shop .tab-item");
const panes = $$("#shop .tab-pane");

const tabActive = $("#shop .tab-item.active");
const line = $("#shop .tabs .line");


requestIdleCallback(function () {
  line.style.left = tabActive.offsetLeft + "px";
  line.style.width = tabActive.offsetWidth + "px";
});

tabs.forEach((tab, index) => {
  const pane = panes[index];

  tab.onclick = function () {
    $("#shop .tab-item.active").classList.remove("active");
    $("#shop .tab-pane.active").classList.remove("active");

    line.style.left = this.offsetLeft + "px";
    line.style.width = this.offsetWidth + "px";

    this.classList.add("active");
    pane.classList.add("active");
  };
});

/* PRODUCT INFO */ 
document.addEventListener('DOMContentLoaded', function() {
  const productInfos = document.querySelectorAll('.product-quantity');

  productInfos.forEach(function(info) {
    const decreaseButton = info.querySelector('.decrease-button');
    const increaseButton = info.querySelector('.increase-button');
    const quantityInput = info.querySelector('input[name="quantity"]');

    decreaseButton.addEventListener('click', function() {
        let currentValue = parseInt(quantityInput.value);
        if (currentValue > parseInt(quantityInput.min)) {
            quantityInput.value = currentValue - 1;
        }
    });

    increaseButton.addEventListener('click', function() {
        let currentValue = parseInt(quantityInput.value);
        if (!quantityInput.max || currentValue < parseInt(quantityInput.max)) {
            quantityInput.value = currentValue + 1;
        }
    });
  });
});


