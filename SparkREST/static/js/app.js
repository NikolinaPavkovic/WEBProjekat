const restaurants = { template : '<restaurants> </restaurants>' }
const login = { template : '<login> </login>' }
const registration = { template : '<registration> </registration>' }
const addEmployee = { template: '<addEmployee> </addEmployee>'}
const add_restaurant = { template: '<add_restaurant> </add_restaurant>' }
const userProfile = { template: '<profile></profile>' }
const restaurant_info = { template: '<restaurant_info> </restaurant_info>' }
const editProfile = { template: '<edit_profile> </edit_profile>'}
const customers = { template: '<customers> </customers>'}
const managers = { template: '<managers> </managers>'}
const deliverers = { template: '<deliverers> </deliverers>' }


const router = new VueRouter({
	mode: 'hash',
		routes : [
			{ path: '/', name: 'home', component: restaurants},
			{ path: '/login', component: login},
			{ path: '/registration', component: registration},
			{ path: '/addEmployee', component: addEmployee},
			{ path: '/add_restaurant', component: add_restaurant },
			{ path: '/profile/:username', component: userProfile },
			{ path: "/details", component: restaurant_info },
			{ path: '/restaurantInfo', component: restaurant_info },
			{ path: '/edit_profile/:username', component: editProfile},
			{ path: '/customers', component: customers},
			{ path: '/managers', component: managers },
			{ path: '/deliverers', component: deliverers }
		]
});

var app = new Vue({
	router,
	el: '#restaurants'
});
