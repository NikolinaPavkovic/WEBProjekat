Vue.component("deliverers", {
	data: function() {
		return {
			deliverers: null,
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
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(d, index) in deliverers">
                        <td>{{d.name}}</td>
                        <td>{{d.surname}}</td>
                        <td>{{d.username}}</td>
                        <td><button v-on:click="deleteDeliverer(d)">Obriši<button></td>
                    </tr>
                </tbody>
            </table>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/activeDeliverers')
			.then(response => (this.deliverers = response.data));
			
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
		deleteDeliverer: function(deliverer) {
			axios
				.delete('/rest/deleteUser/' + deliverer.username)
				.then(response => (this.$router.go()));
		}
	}
});