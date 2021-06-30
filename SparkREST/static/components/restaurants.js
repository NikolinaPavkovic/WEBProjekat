Vue.component("restaurants", {
	data: function() {
		return {
			restaurants: null,
			mode: "notLogged"
		}
	},
	template: `
	<div>
		</br> </br> </br>
		<button style="position: absolute; top: 10px; right: 170px;" v-if="mode=='notLogged'" v-on:click="login">Prijavi se</button>
		<button style="position: absolute; top: 10px; right: 40px;" v-if="mode=='notLogged'" v-on:click="register">Registruj se</button></br> </br>

		<div class="row-restaurants" v-for="r in restaurants">

			<div class="col-with-pic"> </br>
				<div class="col-picture">
					<div>
						<img :src="r.imgPath" class="restaurant-image" alt="r.name"> </br> </br>
					</div>
				</div>
			</div>

			<div class="col-information">
			  <h1 class="restaurant-name"> {{r.name}} </h1>
				<h1 class="info"> Adresa: {{r.location.address.address + ", " + r.location.address.city.city}} </h1>
				<h1 class="info"> Tip restorana: {{r.type}} </h1>
				<h1 class="info"> {{r.status}} </h1>
			</div>

		</div>

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
