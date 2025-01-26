let globalTimeLeft = 0;
document.addEventListener("DOMContentLoaded", () => {
    updateRecordBid();
    setInterval(updateRecordBid, 1000);
    startGlobalTimer();

});
document.getElementById('form-bid-put').addEventListener('submit', function(event) {
    event.preventDefault();
    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    const formData = new FormData(this);
    const idElement = document.getElementById('id');
    const idValue = idElement.textContent || idElement.innerText;

    formData.append("id", idValue);

    fetch('/api/action/addBid', {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен
        },
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                showNotification('Ставка успешно добавлена', 'Новая ставка была добавлена.');
                updateRecordBid(); // Обновляем таблицу
                this.reset();
            } else {
                showNotification('Ошибка добавления ставки', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Ошибка', 'Произошла ошибка при добавлении ставки.');
        });
});


function updateRecordBid() {
    const autoNumber = document.getElementById('autoNumber');
    const autoNumberValue = autoNumber.textContent || autoNumber.innerText;
    const url = new URL('/api/action/allBid', window.location.origin); // Создаем URL
    url.searchParams.append('autoNumber', autoNumberValue);
    fetch(url.toString())
        .then(response => response.json())
        .then(bids => {
            const ulBids = document.getElementById("bidList");

            // Очищаем предыдущие элементы
            ulBids.innerHTML = '';

            bids.forEach((bid, index) => {
                const li = document.createElement('li');
                li.setAttribute('data-id', index + 1);
                li.innerHTML = `
                        <li><span data-field="bid">${bid.initialBid}</span></li>
                `;
                ulBids.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function showNotification(title, body) {
    if (!("Notification" in window)) {
        alert("Этот браузер не поддерживает уведомления.");
    } else if (Notification.permission === "granted") {
        new Notification(title, { body: body });
    } else if (Notification.permission !== "denied") {
        Notification.requestPermission().then(function (permission) {
            if (permission === "granted") {
                new Notification(title, { body: body });
            }
        });
    }
}

function fetchGlobalTimer() {
    fetch('/timer/get')
        .then(response => response.json())
        .then(serverTime => {
            globalTimeLeft = serverTime;
            updateTimerDisplay(globalTimeLeft);
        })
        .catch(error => console.error('Error fetching timer:', error));
}

function updateTimerDisplay(timeLeft) {
    const timerElement = document.getElementById('timer');
    timerElement.textContent = `${timeLeft} сек.`;
}
function startGlobalTimer() {
    fetchGlobalTimer();
    setInterval(() => {
        if (globalTimeLeft > 0) {
            fetchGlobalTimer();
            updateTimerDisplay(globalTimeLeft);
        } else {
            fetch('/timer/reset', {
                method: 'POST',
            }).then(() => {
                fetchGlobalTimer(); // Обновляем текущее время с сервера
            });
            alert('Аукцион закончен!');
        }
    }, 1000);
}



