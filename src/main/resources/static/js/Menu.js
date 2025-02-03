
document.addEventListener('DOMContentLoaded', function() {

	var toggleBtn = document.getElementById('toggle-btn');
	var sidebar = document.getElementById('sidebar');
	var spans = document.querySelectorAll('.nav-link span');
	var navLinks = document.querySelectorAll('.nav-link');

	// Alternar a classe "closed" para o sidebar
	if (toggleBtn) {
		toggleBtn.addEventListener('click', function() {

			if (sidebar) {
				sidebar.classList.toggle('closed');

				spans.forEach(function(span) {
					span.classList.toggle('hidden');
				});
			} else {
				console.log('Elementos não encontrados');
			}
		});
	} else {
		console.log('Botão de alternância não encontrado');
	}

	// Alternar a classe "active" para nav-links
	navLinks.forEach(function(navLink) {
		navLink.addEventListener('click', function() {


			// Remover a classe "active" de todos os nav-links
			navLinks.forEach(function(link) {
				link.classList.remove('active');
			});

			// Adicionar a classe "active" ao nav-link clicado
			this.classList.add('active');
		});
	});
});