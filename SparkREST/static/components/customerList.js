Vue.component("customers", {
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
                        <th>Poeni</th>
                        <th>Tip kupca</th>
                        <th>Blokiran</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(c, index) in customers">
                        <td>{{c.name}}</td>
                        <td>{{c.surname}}</td>
                        <td>{{c.username}}</td>
                        <td>{{c.points}}</td>
                        <td>{{c.customerType.typeName}}</td>
                        <td>{{c.isBlocked}}</td>
                        <td><button v-on:click="deleteCustomer(c)">Obriši<button></td>
                    </tr>
                </tbody>
            </table>
		</div>
	`,
	mounted() {
		axios
			.get('/rest/activeCustomers')
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
		
	},
	methods: {
		deleteCustomer: function(customer) {
			axios
				.delete('/rest/deleteUser/' + customer.username)
				.then(response => (this.$router.go()));
		}
	}
});