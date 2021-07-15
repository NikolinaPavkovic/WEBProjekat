Vue.component("managers", {
	data: function() {
		return {
			managers: null,
			mode: "notLogged"
		}
	},
	template:`
		<div class="table-body">
			</br></br></br>
			<img class="logo" alt="" src="./images/logo1.png">
			<table class="table-all">
                <thead>
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Korisničko ime</th>
                        <th>Restoran</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(m, index) in managers">
                        <td>{{m.name}}</td>
                        <td>{{m.surname}}</td>
                        <td>{{m.username}}</td>
                        <td>{{m.restaurant.name}}</td>
                        <td><button v-on:click="deleteManager(m)">Obriši<button></td>
                    </tr>
                </tbody>
            </table>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/activeManagers')
			.then(response => (this.managers = response.data));
			
		axios
			.get('/rest/isLogged')
			.then(response => {
				if(response.data != null) {
					this.mode = response.data.role;
				} else {
					this.mode = "notLogged";
				}
			});
		
	},
	methods: {
		deleteManager: function(manager) {
			axios
				.delete('/rest/deleteUser/' + manager.username)
				.then(response => (this.$router.go()));
		}
	}
});