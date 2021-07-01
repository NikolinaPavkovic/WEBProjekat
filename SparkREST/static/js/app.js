const restaurants = { template : '<restaurants> </restaurants>' }
const login = { template : '<login> </login>' }
const registration = { template : '<registration> </registration>' }
const addEmployee = { template: '<addEmployee> </addEmployee>'}
const add_restaurant = { template: '<add_restaurant> </add_restaurant>' }

const userProfile = { template: '<profile></profile>' }


const restaurant_info = { template: '<restaurant_info< </restaurantName_info>' }


const router = new VueRouter({
	mode: 'hash',
		routes : [
			{ path: '/', name: 'home', component: restaurants},
			{ path: '/login', component: login},
			{ path: '/registration', component: registration},
			{ path: '/addEmployee', component: addEmployee},
			{ path: '/add_restaurant', component: add_restaurant },

			{ path: '/profile/:username', component: userProfile },

			{ path: '/restaurantInfo', component: restaurant_info }


		]
});

var app = new Vue({
	router,
	el: '#restaurants'
});
