const table = document.querySelector("table");
const headers = table.querySelectorAll("th");
const tbody = table.querySelector("tbody");
let sortState = {}; // ذخیره وضعیت هر ستون

headers.forEach((th, index) => {
    if (!th.dataset.type) return; // ستون Action یا غیر قابل sort
    sortState[index] = 0; // 0 = default, 1 = asc, 2 = desc

    th.addEventListener("click", () => {
        const type = th.dataset.type;
        sortState[index] = (sortState[index] + 1) % 3; // cycle: 0 -> 1 -> 2 -> 0

        const rowsArray = Array.from(tbody.querySelectorAll("tr"));

        if (sortState[index] === 0) {
            // بازگشت به حالت اولیه (می‌توانیم table را دوباره render کنیم)
            rowsArray.sort((a,b)=>a.dataset.originalIndex - b.dataset.originalIndex);
        } else {
            rowsArray.sort((a, b) => {
                let aText = a.children[index].textContent.trim();
                let bText = b.children[index].textContent.trim();

                if (type === "number") {
                    aText = parseFloat(aText.replace("$",""));
                    bText = parseFloat(bText.replace("$",""));
                } else if (type === "datetime") {
                    aText = new Date(aText);
                    bText = new Date(bText);
                }

                if (aText < bText) return sortState[index] === 1 ? -1 : 1;
                if (aText > bText) return sortState[index] === 1 ? 1 : -1;
                return 0;
            });
        }

        // رندر دوباره
        rowsArray.forEach((row, i) => tbody.appendChild(row));
    });
});

// ذخیره index اولیه برای بازگشت به حالت default
tbody.querySelectorAll("tr").forEach((tr, i) => {
    tr.dataset.originalIndex = i;
});
