//document.querySelectorAll('.heart').forEach(button => {
//    button.addEventListener('click', function() {
//        this.closest('.product').remove();
//    });
//});

// Обработчики для кнопок увеличения количества
document.querySelectorAll('.increment').forEach(button => {
    button.addEventListener('click', function () {
        const input = this.parentElement.querySelector('.quantity');
        input.value = parseInt(input.value) + 1;
    });
});

// Обработчики для кнопок уменьшения количества
document.querySelectorAll('.decrement').forEach(button => {
    button.addEventListener('click', function () {
        const input = this.parentElement.querySelector('.quantity');
        if (parseInt(input.value) > 1) {
            input.value = parseInt(input.value) - 1;
        }
    });
});


document.addEventListener('DOMContentLoaded', function () {
    // Переключение между доставкой и самовывозом
    const deliveryRadio = document.getElementById('delivery');
    const pickupRadio = document.getElementById('pickup');
    const deliveryForm = document.getElementById('delivery-form');
    const pickupForm = document.getElementById('pickup-form');

    deliveryRadio.addEventListener('change', function () {
        if (this.checked) {
            deliveryForm.style.display = 'block';
            pickupForm.style.display = 'none';
        }
    });

    pickupRadio.addEventListener('change', function () {
        if (this.checked) {
            deliveryForm.style.display = 'none';
            pickupForm.style.display = 'block';
        }
    });

    // Показать/скрыть детали подъема
    const liftingCheckbox = document.getElementById('lifting');
    const liftingDetails = document.getElementById('lifting-details');

    liftingCheckbox.addEventListener('change', function () {
        liftingDetails.style.display = this.checked ? 'block' : 'none';
    });


    const pickupSelect = document.getElementById('pickup-point');
    const pickupInfo = document.getElementById('pickup-info');
    const pickupAddress = document.getElementById('pickupAddress');
    const pickupPhone = document.getElementById('pickupPhone');
    const pickupMap = document.getElementById('pickup-map');
    let map;

    pickupSelect.addEventListener('change', function () {
        const selected = this.options[this.selectedIndex];

        if (selected.value) {
            // Отображение информации
            pickupInfo.style.display = 'block';
            pickupAddress.textContent = selected.dataset.address || 'Нет данных';
            pickupPhone.textContent = selected.dataset.phone || 'Нет данных';

            const coords = selected.dataset.coords;
            if (coords) {
                const [lat, lon] = coords.split(',');
                ymaps.ready(() => {
                    pickupMap.innerHTML = ''; // Очищаем предыдущую карту
                    map = new ymaps.Map("pickup-map", {
                        center: [lat, lon],
                        zoom: 15
                    });
                    const placemark = new ymaps.Placemark([lat, lon], {
                        hintContent: selected.text,
                        balloonContent: selected.dataset.address
                    });
                    map.geoObjects.add(placemark);
                });
            }
        } else {
            pickupInfo.style.display = 'none';
            pickupMap.innerHTML = '';
        }
    });


});

