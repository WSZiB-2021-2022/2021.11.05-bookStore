function validate() {
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var login = document.getElementById("login").value;
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;

    var nameSurnamePattern = /[A-Z][a-z]+/;
    var loginPassPattern = /.{2}.*/

    if(!nameSurnamePattern.test(name)) {
        return false;
    }

    if(!nameSurnamePattern.test(surname)) {
        return false;
    }

    if(!loginPassPattern.test(login)) {
        return false;
    }

    if(!loginPassPattern.test(pass)) {
        return false;
    }

    if(pass != pass2) {
        return false;
    }

    return true;
}