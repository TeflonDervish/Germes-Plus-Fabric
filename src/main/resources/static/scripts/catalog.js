const filters = document.querySelectorAll('.filters');
filters.forEach(filter => {
    filter.addEventListener('click', function (event) {
        // Останавливаем всплытие, чтобы клик не доходил до document
        event.stopPropagation();

        // Закрываем все другие фильтры
        filters.forEach(otherFilter => {
            if (otherFilter !== this) {
                otherFilter.classList.remove('active');
                const otherArrow = otherFilter.querySelector('.arrow');
                if (otherArrow) {
                    otherArrow.classList.remove('rotate');
                }
            }
        });

        // Переключаем состояние текущего фильтра
        this.classList.toggle('active');

        // Переключаем стрелку только для текущего фильтра
        const arrow = this.querySelector('.arrow');
        if (arrow) {
            arrow.classList.toggle('rotate');
        }
    });
});

// Закрываем все фильтры при клике вне их области
document.addEventListener('click', function () {
    filters.forEach(filter => {
        filter.classList.remove('active');
        // Возвращаем стрелку в исходное положение
        const arrow = filter.querySelector('.arrow');
        if (arrow) {
            arrow.classList.remove('rotate');
        }
    });
});

// Останавливаем всплытие события для инпутов
const inputs = document.querySelectorAll('.dropdown-content');
inputs.forEach(input => {
    input.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});


let isFiltersOpen = false;
let lastClickedButton = null;

document.querySelector('#toggle-filters').addEventListener('click', function (event) {
    event.stopPropagation(); 

    const filtersContent = document.querySelector('.filt_all');
    const arrow = this.querySelector('.arrow'); 

  
    if (lastClickedButton === event.target) {
        
        filtersContent.classList.toggle('open');
        isFiltersOpen = !isFiltersOpen;

        if (arrow) {
            if (isFiltersOpen) {
                arrow.style.transform = 'scaleY(-1)'; // Поворачиваем стрелку
            } else {
                arrow.style.transform = 'scaleY(1)'; // Возвращаем стрелку в исходное положение
            }
        }
    } else {
        filtersContent.classList.add('open');
        if (arrow) {
            arrow.style.transform = 'scaleY(-1)'; // Поворачиваем стрелку
        }
        isFiltersOpen = true;
    }
    lastClickedButton = event.target;
});