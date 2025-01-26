document.addEventListener("DOMContentLoaded", () => {
    updateRecordTable();
    setInterval(updateRecordTable, 10000);
});

var participateCarsButton = document.getElementById("participate_auction");
var spanParticipate = document.getElementsByClassName("closeParticipate")[0];
var modalParticipateCars = document.getElementById("modalParticipate");


participateCarsButton.onclick = function() {
    modalParticipateCars.style.display = "block";
}

spanParticipate.onclick = function() {
    modalParticipateCars.style.display = "none";
}
function updateRecordTable() {
    fetch('/api/action/allAuction')
        .then(response => response.json())
        .then(auctions => {
            const divCars = document.getElementById("list-available");

            // Очищаем предыдущие элементы
            divCars.innerHTML = '';

            auctions.forEach((auction, index) => {
                const div = document.createElement('div');
                div.className = 'auction-item';
                div.setAttribute('data-id', index + 1);
                div.innerHTML = `
                        <div class="auction-item-details">
                            <h3><strong>Mark name:</strong> <span data-field="markAndModelName">${auction.markAndModelName}</span></h3>
                            <p><strong>Year:</strong> <span  data-field="year">${auction.year}</span></p>
                            <p><strong>Car number:</strong> <span  data-field="autoNumber">${auction.autoNumber}</span></p>
                            <p><strong>Km:</strong> <span  data-field="km">${auction.km}</span></p>
                            <p><strong>Condition:</strong> <span  data-field="carCondition">${auction.carCondition}</span></p>
                            <p><strong>Start price:</strong> <span  data-field="price">${auction.price}</span></p>
                            <p><strong>Date:</strong> <span  data-field="price">${auction.date}</span></p>
                            <p><strong>Time start:</strong> <span  data-field="price">${auction.time}</span></p>
                        </div>
                `;
                divCars.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

