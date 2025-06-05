const images = document.querySelectorAll('.gallery-images img');
const prevButton = document.querySelector('.prev-button');
const nextButton = document.querySelector('.next-button');
let currentIndex = 0;

function showImage(index) {
    // Скрываем все изображения
    images.forEach(img => img.classList.remove('active'));
    // Показываем текущее изображение
    images[index].classList.add('active');
}

prevButton.addEventListener('click', () => {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    showImage(currentIndex);
});

nextButton.addEventListener('click', () => {
    currentIndex = (currentIndex + 1) % images.length;
    showImage(currentIndex);
});

// Показываем первое изображение при загрузке страницы
showImage(currentIndex);
