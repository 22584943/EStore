const searchInput = document.getElementById("searchInput");

const dropdownCont = document.getElementsByClassName("dropdown-cont")[0]
const dropdownResults = document.getElementsByClassName("dropdown-results")[0]
let searchType = ""
let searchResults = COLL;
searchResults = searchResults.split("}, ")
console.log(searchResults)

searchInput.addEventListener("input", (e)=> {
    searchType = document.getElementById("searchType")
    let inputText = e.target.value
    console.log(searchType)
    for (product in searchResults) {
        if (product[searchType].includes(inputText)) {
            dropdownResults.innerHTML += `<li>${productSearchType}</li>`
        }
    }
    console.log(e.target.value)
})

