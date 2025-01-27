document.addEventListener("DOMContentLoaded", () => {
    updateRecordTable();
});

var addCarsButton = document.getElementById("add_cars");
var putCarsButton = document.getElementById("put_auction");
var editCarsButton = document.getElementById("edit_info");
var dellCarsButton = document.getElementById("delete_car");
var modalAddCars = document.getElementById("modalAddCars");
var spanAdd = document.getElementsByClassName("closeAdd")[0];
var spanDell = document.getElementsByClassName("closeDell")[0];
var spanEdit = document.getElementsByClassName("closeEdit")[0];
var spanPut = document.getElementsByClassName("closePut")[0];
var modalEditCars = document.getElementById("modalEditCars");
var modalDellCars = document.getElementById("modalDellCars");
var modalPutCars = document.getElementById("modalPutCars");

addCarsButton.onclick = function() {
    modalAddCars.style.display = "block";
    modalEditCars.style.display =  "none";
    modalDellCars.style.display =  "none";
    modalPutCars.style.display = "none"

}

editCarsButton.onclick = function() {
    modalAddCars.style.display = "none";
    modalEditCars.style.display =  "block";
    modalDellCars.style.display =  "none";
    modalPutCars.style.display = "none"

}

dellCarsButton.onclick = function() {
    modalAddCars.style.display = "none";
    modalEditCars.style.display =  "none";
    modalDellCars.style.display =  "block";
    modalPutCars.style.display = "none"

}

putCarsButton.onclick = function() {
    modalAddCars.style.display = "none";
    modalEditCars.style.display =  "none";
    modalDellCars.style.display =  "none";
    modalPutCars.style.display = "block"
}

spanAdd.onclick = function() {
    modalAddCars.style.display = "none";
}
spanEdit.onclick = function() {
    modalEditCars.style.display = "none";
}
spanDell.onclick = function() {
    modalDellCars.style.display = "none";
}

spanPut.onclick = function() {
    modalPutCars.style.display = "none";
}

// document.getElementById('formPut').addEventListener('submit', function (event) {
//     event.preventDefault(); // Отменяем стандартное поведение формы
//
//     const csrfToken = document.querySelector('input[name="_csrf"]').value; // Получаем CSRF-токен
//     const autoNumber = document.getElementById('autoNumberPut'); // Получаем поле ввода
//     const autoNumberValue = autoNumber.value.trim(); // Берем значение поля (value), убираем пробелы
//     if (!autoNumberValue) {
//         console.error('Поле авто номер пустое!');
//         return;
//     }
//     console.log('Введенное значение:', autoNumberValue);
//
//     // const url1 = new URL('/redirect/put', window.location.origin); // URL для первого запроса
//     const url2 = new URL('/timer/start', window.location.origin); // URL для второго запроса
//     // url1.searchParams.append('autoNumber', autoNumberValue);
//     url2.searchParams.append('autoNumber', autoNumberValue);
//
//     // Отправка первого запроса
//     fetch(url2.toString(), {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': csrfToken // Передаем CSRF-токен
//         }
//     })

    // // Отправка второго запроса
    // fetch(url1.toString(), {
    //     method: 'GET',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'X-CSRF-TOKEN': csrfToken // Передаем CSRF-токен
    //     }
    // })
    //     .then(response => {
    //         if (!response.ok) {
    //             throw new Error(`Ошибка сервера: ${response.status}`);
    //         }
    //         return response.text(); // Получаем текстовый ответ (имя страницы)
    //     })
    //     .then(pageName => {
    //         console.log('Переход на страницу:', pageName);
    //         // Перенаправляем пользователя на страницу
    //         window.location.href = pageName;
    //     })
    //     .catch(error => console.error('Ошибка при запросе:', error));
// });

document.getElementById('formEdit').addEventListener('submit', function(event) {
    event.preventDefault();
    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    const formData = new FormData(this);

    fetch('/api/action/edit', {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен
        },
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                showNotification('Запись успешно изменена', 'Запись была изменена.');
                updateRecordTable(); // Обновляем таблицу
                this.reset();
            } else {
                showNotification('Ошибка изменения записи', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Ошибка', 'Произошла ошибка при изменении записи.');
        });
});

document.getElementById('formDellCar').addEventListener('submit', function(event) {
    event.preventDefault();
    const csrfToken = document.querySelector('input[name="_csrf"]').value;
    const formData = new FormData(this);

    fetch('/api/action/dell', {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен
        },
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data.status === 'success') {
                showNotification('Запись успешно удалена', 'Запись была удалена.');
                updateRecordTable(); // Обновляем таблицу
                this.reset();
            } else {
                showNotification('Ошибка удаления записи', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            showNotification('Ошибка', 'Произошла ошибка при удалении записи.');
        });
});

document.getElementById('modalFormAdd').addEventListener('submit', function(event) {
    event.preventDefault();
    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    const formData = new FormData(this);

    fetch('/api/action/add', {
        method: 'POST',
        headers: {
            'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен
        },
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

            cars.forEach((car, index) => {
                const div = document.createElement('div');
                div.className = 'auction-item';
                div.setAttribute('data-id', index + 1);
                div.innerHTML = `
                        <div class="auction-item-details">
                            <h3><strong>Mark name:</strong> <span class="editable" data-field="markAndModelName">${car.markAndModelName}</span></h3>
                            <p><strong>Year:</strong> <span class="editable" data-field="year">${car.year}</span></p>
                            <p><strong>Car number:</strong> <span class="editable" data-field="autoNumber">${car.autoNumber}</span></p>
                            <p><strong>Km:</strong> <span class="editable" data-field="km">${car.km}</span></p>
                            <p><strong>Condition:</strong> <span class="editable" data-field="carCondition">${car.carCondition}</span></p>
                            <p><strong>Start price:</strong> <span class="editable" data-field="price">${car.price}</span></p>
                        </div>
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
