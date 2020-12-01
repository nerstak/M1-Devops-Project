/**
 * Autocomplete function for inputs
 * @param inp Input to complete
 * @param arr Array of possible values
 */
function autocomplete(inp, arr) {
    let currentFocus;

    // Starting function when input is used
    inp.addEventListener("input", function (e) {
        let a, b, i, val = this.value;

        // Close any other input's autocomplete
        closeAllLists();
        if (!val) {
            return false;
        }
        currentFocus = -1;

        // Create div element containing all future items
        a = document.createElement("DIV");
        a.setAttribute("id", this.id + "autocomplete-list");
        a.setAttribute("class", "autocomplete-items");
        this.parentNode.appendChild(a);


        for (i = 0; i < arr.length; i++) {
            // Check if the item starts with the same letters as the text field value
            if (arr[i].substr(0, val.length).toUpperCase() === val.toUpperCase()) {
                // Create div for each match
                b = document.createElement("DIV");

                // Matching letters bold
                let strong = document.createElement('strong');
                let textStrong = document.createTextNode(arr[i].substr(0, val.length));
                strong.appendChild(textStrong)
                let textNormal = document.createTextNode(arr[i].substr(val.length));
                b.appendChild(strong);
                b.appendChild(textNormal);

                // Keep in memory current item value
                let input = document.createElement('input');
                input.setAttribute('type','hidden');
                input.setAttribute('value',arr[i]);
                b.appendChild(input);

                // Function when we click
                b.addEventListener("click", function (f) {
                    // Adding value for autocomplete field
                    inp.value = this.getElementsByTagName("input")[0].value;
                    closeAllLists();
                });
                a.appendChild(b);
            }
        }
    });
    // Function when we use keyboard
    inp.addEventListener("keydown", function (e) {
        let x = document.getElementById(this.id + "autocomplete-list");
        if (x) {
            x = x.getElementsByTagName("div");
        }
        if (e.keyCode === 40) {
            // Down key
            currentFocus++;
            addActive(x);
        } else if (e.keyCode === 38) {
            // Up key
            currentFocus--;
            addActive(x);
        } else if (e.keyCode === 13) {
            // Enter key
            // We DON'T submit
            e.preventDefault();
            if (currentFocus > -1) {
                // Simulate click instead (to complete field)
                if (x) x[currentFocus].click();
            }
        }
    });

    /**
     * Mark an element as "active"
     * @param x Elements
     * @returns {boolean} False if no elements
     */
    function addActive(x) {
        if (!x) return false;

        // We remove focus everywhere
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);

        x[currentFocus].classList.add("autocomplete-active");
    }

    /**
     * Remove "active" class from all elements
     * @param x Elements
     */
    function removeActive(x) {
        for (let i = 0; i < x.length; i++) {
            x[i].classList.remove("autocomplete-active");
        }
    }

    /**
     * Close all opened lists
     * @param elt Current list not to close
     */
    function closeAllLists(elt) {
        const x = document.getElementsByClassName("autocomplete-items");
        for (let i = 0; i < x.length; i++) {
            if (elt !== x[i] && elt !== inp) {
                x[i].parentNode.removeChild(x[i]);
            }
        }
    }

    // Function when clicking outside
    document.addEventListener("click", function (e) {
        closeAllLists(e.target);
    });
}