Vue.component("restaurants", {
	data: function() {
		return {
			restaurants: null,
			mode: "notLogged"
		}
	},
	template: `
	<div>
		<h3>Restorani:</h3>
		<table class="styled-table">
			<thead>
				<tr bgcolor="lightgrey">
					<th>Logo</th>
					<th>Naziv</th>
					<th>Tip</th>
					<th>Status</th>
					<th>Lokacija</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="r in restaurants">
					<td><img :src="r.imgPath"></td>
					<td>{{r.name}}</td>
					<td>{{r.type}}</td>
					<td>{{r.status}}</td>
					<td>{{r.location.address.address + ", " + r.location.address.city.city}}</td>
				</tr>
			</tbody>
		</table>
		<button v-if="mode=='notLogged'" v-on:click="login">Prijavi se</button>
		<button v-if="mode=='notLogged'" v-on:click="register">Registruj se</button></br>
		<button v-if="mode=='admin'" v-on:click="addEmployee">Dodaj zaposlenog</button>
		<button v-on:click="addRestaurant"> Dodaj restoran </button>

	</div>
	`,
	mounted() {
		axios
			.get('rest/restaurants/')
			.then(response => (this.restaurants = response.data));
			
	
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
		login: function() {
			router.push(`/login`);
		},
		register: function() {
			router.push(`/registration`);
		},

		addEmployee: function() {
			router.push('/addEmployee');
		},	
		addRestaurant: function() {
			router.push(`/add_restaurant`);

		}
	}
});
