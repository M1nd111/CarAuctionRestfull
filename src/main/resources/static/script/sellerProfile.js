var deleteBtnSeller = document.getElementById("delete-button-seller");

var modalLogout = document.getElementById("modalDell");

deleteBtnSeller.onclick = function() {
    modalLogout.style.display = "block";
}

document.getElementById("delete-button-seller").addEventListener("click", function () {
    const csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch('/api/action/dellProfile', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен в заголовок
        },
        body: JSON.stringify({}) // Можно передать данные, если необходимо
    })
        .then(response => {
            if (response.ok) {
                alert("Профиль успешно удален!");
                // Если нужно обновить страницу или скрыть профиль, выполните дополнительные действия
            } else {
                alert("Ошибка при удалении профиля.");
            }
        })
        .catch(error => {
            console.error("Ошибка:", error);
            alert("Произошла ошибка при запросе.");
        });
});