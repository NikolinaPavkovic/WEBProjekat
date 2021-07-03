Vue.component("restaurants", {
	data: function() {
		return {
			restaurants: null,
			mode: "notLogged",
			user: null,
			hover: false
		}
	},
	template: `
	<div>
		</br> </br> </br>
		<img class="logo" alt="" src="./images/logo1.png">
		<button style="position: absolute; top: 10px; right: 170px;" v-if="mode=='notLogged'" v-on:click="login">Prijavi se</button>
		<button style="position: absolute; top: 10px; right: 40px;" v-if="mode=='notLogged'" v-on:click="register">Registruj se</button>
		<button style="position: absolute; top: 10px; right: 40px;" v-if="mode!='notLogged'" v-on:click="profileInfo">Profil</button>
		<button style="position: absolute; top: 10px; right: 170px;" v-if="mode!='notLogged'" v-on:click="logout">Log out</button></br> </br>


		<div class="row-restaurants" v-for="(r, index) in restaurants" @mouseover="hover = true" @mouseleave="hover = false">
			<div class="col-with-pic"> </br>
				<div class="col-picture">
					<div>
						<img :src="r.imgPath" class="restaurant-image" alt="r.name"> </br> </br>
					</div>
					<button class="see-more"><a :href="'#/details?name=' + r.name" class="link" > Pregledaj restoran </a> </button>


				</div>
			</div>

			<div class="col-information">
			  <h1 class="restaurant-name"> {{r.name}} </h1>
				<h1 class="info"> {{r.location.address.address + ", " + r.location.address.city.city}} </h1>
				<h1 class="info"> Tip restorana: {{r.type}} </h1>
				<h1 class="info"> {{r.status}} </h1>
			</div>
		</div>

		<button v-if="mode=='admin'" v-on:click="addEmployee">Dodaj zaposlenog</button>
		<button v-if="mode=='admin'" v-on:click="addRestaurant"> Dodaj restoran </button></br>
		<button v-if="mode=='admin'" v-on:click="getCustomers"> Pregled kupaca </button>
		<button v-if="mode=='admin'" v-on:click="getManagers"> Pregled menadžera </button>
		<button v-if="mode=='admin'" v-on:click="getDeliverers"> Pregled dostavljača </button>
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
					this.user = response.data;
				} else {
					this.mode = "notLogged";
				}
			});
	},

	watch: {
		restaurants(value) {
			this.restaurants = value;
		}
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
		},
		profileInfo: function() {
			router.push(`/profile/`+ this.user.username);
		},
		logout: function() {
			axios
				.get('/rest/logout')
				.then(response => (this.$router.go()));
		},
		getCustomers: function() {
			router.push(`/customers`);
		},
		getManagers: function() {
			router.push(`/managers`);
		},
		getDeliverers: function() {
			router.push(`/deliverers`);
		}
	}
});
