const marks = document.getElementsByClassName("marks");

window.addEventListener("resize",changeSize)

function changeSize(){
    if(window.innerWidth<=960){
        for (const mark of marks) {
            mark.classList.remove("fa-2xl")
            mark.classList.add("fa-xl")
        }
    }
    else{
        for (const mark of marks) {
            mark.classList.remove("fa-xl")
            mark.classList.add("fa-2xl")
        }
    }
}