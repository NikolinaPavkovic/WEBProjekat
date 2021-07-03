Vue.component("managers", {
	data: function() {
		return {
			customers: null,
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
                    <tr v-for="(c, index) in customers">
                        <td>{{c.name}}</td>
                        <td>{{c.surname}}</td>
                        <td>{{c.username}}</td>
                    </tr>
                </tbody>
            </table>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/managers')
			.then(response => (this.customers = response.data));
			
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