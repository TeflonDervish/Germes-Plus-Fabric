
// Находим все кнопки и контент вкладок
const tabButtons = document.querySelectorAll('.tab-button');
const tabPanes = document.querySelectorAll('.tab-pane');

// Добавляем обработчик событий для каждой кнопки
tabButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Убираем активный класс у всех кнопок и контента
        tabButtons.forEach(btn => btn.classList.remove('active'));
        tabPanes.forEach(pane => pane.classList.remove('active'));

        // Добавляем активный класс к выбранной кнопке и соответствующему контенту
        button.classList.add('active');
        const tabId = button.getAttribute('data-tab');
        document.getElementById(tabId).classList.add('active');
    });
});