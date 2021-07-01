Vue.component("profile", {
	data: function() {
		return {
			user: null
		}
	}, 
	template: `
		<div class="all">
			<div class="wrapper">
				<div class="left">
					<h4>{{user.name}} {{user.surname}}</h4>
					<p>{{user.role}}</p>
				</div>
				<div class="right">
					<div class="info">
						<h3>Informacije</h3>
						<div class="info_data">
							<div class="data">
								<h4>Korisničko ime</h4>
								<p>{{user.username}}</p>
							</div>
							<div class="data">
								<h4>Pol</h4>
								<p>{{user.gender}}</p>
							</div>
							<div class="data">
								<h4>Datum rođenja</h4>
								<p>{{user.dateOfBirth}}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/isLogged')
			.then(response => {
				this.user = response.data;
			});
			
	}
});