let timer;
let timeLeft = 60; // 60 seconds per bidding round

function startTimer() {
    timer = setInterval(() => {
        if (timeLeft > 0) {
            timeLeft--;
            document.getElementById('timer').textContent = `${timeLeft} сек.`;
        } else {
            clearInterval(timer);
            alert('Время для текущего этапа истекло!');
        }
    }, 1000);
}

function placeBid() {
    const currentBid = parseFloat(document.getElementById('currentBid').textContent);
    const newBid = parseFloat(document.getElementById('bidInput').value);

    if (newBid > currentBid) {
        document.getElementById('currentBid').textContent = newBid.toFixed(2);
        const bidList = document.getElementById('bidList');
        const newBidElement = document.createElement('li');
        newBidElement.textContent = `Участник сделал ставку: ${newBid.toFixed(2)} ₽`;
        bidList.appendChild(newBidElement);

        timeLeft = 60; // Сбрасываем таймер на 60 секунд
        alert('Ставка принята!');
    } else {
        alert('Ставка должна быть выше текущей цены!');
    }

    document.getElementById('bidInput').value = '';
}

window.onload = startTimer;