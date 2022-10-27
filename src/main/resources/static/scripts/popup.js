const popup=document.querySelector('.modal')
const acceptButton=document.querySelector('.accept-button')

acceptButton.addEventListener("click",()=>{
    popup.classList.add("hide")
    window.localStorage.setItem("acceptTerms","true")
})

window.onload=()=>{
    if(window.localStorage.getItem("acceptTerms")!=="true"){
        popup.classList.remove("hide")
    }
}