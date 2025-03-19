// document.getElementById('buyer-register-form').addEventListener('submit', function(event) {
//     event.preventDefault();
//     const csrfToken = document.querySelector('input[name="_csrf"]').value;
//     const formData = new FormData(this);
//
//     fetch('/authorization/buyer/register', {
//         method: 'POST',
//         headers: {
//             'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен
//         },
//         body: formData
//     })
//         .then(response => response.json())
//         .then(response => {
//             if (response.redirected) {
//                 window.location.href = response.url; // Делаем редирект, если сервер перенаправил
//             } else {
//                 return response.text();
//             }
//         })
//         .catch(error => {
//             console.error('Error:', error);
//             showNotification('Ошибка', 'Произошла ошибка при регистрации.');
//         });
// });
//
// function showNotification(title, body) {
//     if (!("Notification" in window)) {
//         alert("Этот браузер не поддерживает уведомления.");
//     } else if (Notification.permission === "granted") {
//         new Notification(title, { body: body });
//     } else if (Notification.permission !== "denied") {
//         Notification.requestPermission().then(function (permission) {
//             if (permission === "granted") {
//                 new Notification(title, { body: body });
//             }
//         });
//     }
// }