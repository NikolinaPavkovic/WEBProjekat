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
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(d, index) in deliverers">
                        <td>{{d.name}}</td>
                        <td>{{d.surname}}</td>
                        <td>{{d.username}}</td>
                    </tr>
                </tbody>
            </table>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/deliverers')
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
		
	}
});