const searchInput = document.getElementById("searchInput");

const dropdownCont = document.getElementsByClassName("dropdown-cont")[0]
const dropdownResults = document.getElementsByClassName("dropdown-results")[0]

let searchResults = COLL;
searchResults = searchResults.split("}, ")
console.log(searchResults)

searchInput.addEventListener("input", (e)=> {
    console.log(e.target.value)
})