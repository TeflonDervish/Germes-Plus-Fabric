document.querySelectorAll('.heart').forEach(button => {
    button.addEventListener('click', function() {
        this.closest('.product').remove();
    });
});