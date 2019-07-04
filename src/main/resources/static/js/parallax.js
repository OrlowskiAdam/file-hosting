window.onload = function () {

    var parallaxBox = document.getElementById('box');
    var c1left = document.getElementById('1').offsetLeft,
        c1top = document.getElementById('1').offsetTop;

    parallaxBox.onmousemove = function (event) {
        event = event || window.event;
        var x = event.clientX - parallaxBox.offsetLeft,
            y = event.clientY - parallaxBox.offsetTop;


        mouseParallax('1', c1left, c1top, x, y, 100);
    }
}

function mouseParallax(id, left, top, mouseX, mouseY, speed) {
    var obj = document.getElementById(id);
    var parentObj = obj.parentNode,
        containerWidth = parseInt(parentObj.offsetWidth),
        containerHeight = parseInt(parentObj.offsetHeight);
    obj.style.left = left - (((mouseX - (parseInt(obj.offsetWidth) / 2 + left)) / containerWidth) * speed) + 'px';
    obj.style.top = top - (((mouseY - (parseInt(obj.offsetHeight) / 2 + top)) / containerHeight) * speed) + 'px';
}