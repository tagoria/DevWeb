window.onload=function() {
    boutonsSupprArray = document.getElementsByClassName("boutonSuppr");
    for (var i = 0; i < boutonsSupprArray.length; i++) {
        boutonsSupprArray[i].onclick = function () {
            var id = this.getAttribute("id");
            var suppr = confirm("Supprimer le son : " + document.getElementById("nomSon" + id).innerHTML+" ?");
            if (suppr) {
                console.log(id);
                supprimer(id);
            }
        }
    }
};
function supprimer(id) {
    var maRequeteAJAX = new XMLHttpRequest();
    maRequeteAJAX.open("POST", "supprimerSon?id=" + id, true);
    maRequeteAJAX.send();
    maRequeteAJAX.onload=function () {
        if(this.status === 200) {
            rafraichir(id)
        }else{
            alert("Echec de la suppression")
        }
    }
}
function rafraichir(id) {
    var ligne = document.getElementById("ligneSon"+id);
    ligne.parentNode.removeChild(ligne);
}