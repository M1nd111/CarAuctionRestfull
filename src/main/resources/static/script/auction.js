// document.addEventListener("DOMContentLoaded", () => {
//     updateRecordTable();
// });
//
// function updateRecordTable() {
//     fetch('/api/action/all')
//         .then(response => response.json())
//         .then(cars => {
//             console.log('Response from /api/action/all:', cars);
//             const divCars = document.getElementById("cars");
//
//             // Очищаем предыдущие элементы
//             divCars.innerHTML = '';
//
//             cars.forEach((car, index) => {
//                 const div = document.createElement('div');
//                 div.className = 'auction-item';
//                 div.setAttribute('data-id', index + 1);
//                 div.innerHTML = `
//                         <div class="auction-item-details">
//                             <h3><strong>Mark name:</strong> <span class="editable" data-field="markAndModelName">${car.markAndModelName}</span></h3>
//                             <p><strong>Year:</strong> <span class="editable" data-field="year">${car.year}</span></p>
//                             <p><strong>Car number:</strong> <span class="editable" data-field="autoNumber">${car.autoNumber}</span></p>
//                             <p><strong>Km:</strong> <span class="editable" data-field="km">${car.km}</span></p>
//                             <p><strong>Condition:</strong> <span class="editable" data-field="carCondition">${car.carCondition}</span></p>
//                             <p><strong>Start price:</strong> <span class="editable" data-field="price">${car.price}</span></p>
//                         </div>
//                 `;
//                 divCars.appendChild(div);
//             });
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// }
