var addCarsButton = document.getElementById("add_cars");
var modalAddCars = document.getElementById("modalAddCars");
var spanAdd = document.getElementsByClassName("closeAdd")[0];

addCarsButton.onclick = function() {
    modalAddCars.style.display = "block";
}

spanAdd.onclick = function() {
    modalAddCars.style.display = "none";
}

document.getElementById('modalFormAdd').addEventListener('submit', function(event) {
    event.preventDefault();

    var formData = new FormData(this);

    fetch('/api/action/add', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                showNotification('Запись успешно добавлена', 'Новая запись была добавлена.');
                updateRecordTable(); // Обновляем таблицу
                this.reset();
            } else {
                showNotification('Ошибка добавления записи', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Ошибка', 'Произошла ошибка при добавлении записи.');
        });
});

function updateRecordTable() {
    fetch('/api/action/all')
        .then(response => response.json())
        .then(cars => {
            console.log('Response from /api/action/all:', cars);
            const divCars = document.getElementById("cars");

            // Очищаем предыдущие элементы
            divCars.innerHTML = '';

            cars.forEach(car => {
                const div = document.createElement('div');
                div.className = 'auction-item';
                div.innerHTML = `
                    <img src="https://via.placeholder.com/100x75" alt="Car">
                    <div class="auction-item-details">
                        <h3><strong>Mark name:</strong> <span>${car.markAndModelName}</span></h3>
                        <p><strong>Year:</strong> <span>${car.year}</span></p>
                        <p><strong>Km:</strong> <span>${car.km}</span></p>
                        <p><strong>Condition:</strong> <span>${car.carCondition}</span></p>
                        <p><strong>Start price:</strong> <span>${car.price}</span></p>
                    </div>
                    <button class="bid-button">Start auction</button>
                `;
                divCars.appendChild(div);
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