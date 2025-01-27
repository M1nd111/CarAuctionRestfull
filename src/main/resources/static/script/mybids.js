document.addEventListener("DOMContentLoaded", () => {
    updateRecordBid();
    setInterval(updateRecordBid, 1000);
});

function updateRecordBid() {
    const url = new URL('/api/action/vinBidList', window.location.origin); // Создаем URL
    fetch(url.toString())
        .then(response => response.json())
        .then(bids => {
            const ulBids = document.getElementById("bidListBuyer");

            // Очищаем предыдущие элементы
            ulBids.innerHTML = '';

            bids.forEach((bid, index) => {
                const li = document.createElement('li');
                li.setAttribute('data-id', index + 1);
                li.innerHTML = `
                        <li><span data-field="bid">${bid.autoNumber} ${bid.sellerPhone} ${bid.buyerPhone}
                        ${bid.initialBid} ${bid.date} ${bid.time} </span></li>
                `;
                ulBids.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}