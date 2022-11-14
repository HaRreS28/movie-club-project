const message = document.getElementsByClassName("fa-message")
window.onload=()=>{
    changeSizeMessage()
}
window.addEventListener("resize",changeSizeMessage)

function changeSizeMessage(){
    if(window.innerWidth<=960){
        message[0].classList.remove("fa-2xl")
        message[0].classList.add("fa-lg")
    }
    else message[0].classList.add("fa-2xl")
}