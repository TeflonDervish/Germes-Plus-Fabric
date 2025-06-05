const roleSwitch = document.getElementById('roleSwitch');
const clientForm = document.getElementById('clientForm');
const partnerForm = document.getElementById('partnerForm');
const clientFooter = document.getElementById('clientFooter');
const partnerFooter = document.getElementById('partnerFooter');
const clientHeader = document.getElementById('clientHeader');
const partnerHeader = document.getElementById('partnerHeader');

roleSwitch.addEventListener('change', function() {
  if (this.checked) {
    clientForm.classList.add('hidden');
    partnerForm.classList.remove('hidden');
    clientFooter.classList.add('hidden');
    partnerFooter.classList.remove('hidden');
    clientHeader.classList.add('hidden');
    partnerHeader.classList.remove('hidden')
  } else {
    clientForm.classList.remove('hidden');
    partnerForm.classList.add('hidden');
    clientFooter.classList.remove('hidden');
    partnerFooter.classList.add('hidden');
    clientHeader.classList.remove('hidden');
    partnerHeader.classList.add('hidden');
  }
});

