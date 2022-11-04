const message = document.getElementsByClassName("fa-message")
window.onload=()=>{
    changeSize()
}

window.onresize=()=>{
    changeSize()
}

function changeSize(){
    if(window.innerWidth<=960){
        message[0].classList.remove("fa-2xl")
        message[0].classList.add("fa-lg")
    }
    else message[0].classList.add("fa-2xl")
}