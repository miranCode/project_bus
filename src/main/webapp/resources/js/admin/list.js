window.onload = function() {
    var hash = window.location.hash;
    if (hash) {
        var target = document.querySelector(hash);
        if (target) {
            target.scrollIntoView({behavior: "smooth"});
        }
    }
}
