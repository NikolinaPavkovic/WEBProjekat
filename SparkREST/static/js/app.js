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
const add_item = { template: '<add_item> </add_item>' }
const search_restaurant = { template: '<search-restaurant> </search-restaurant>' }
const shoppingCart = { template: '<shopping_cart> </shopping_cart>' }
const add_manager = { template: '<add_manager> </add_manager>' }


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
			{ path: '/deliverers', component: deliverers },
			{ path: "/add_item", component: add_item},
			{ path: "/search_restaurant", component: search_restaurant },
			{ path: "/shoppingCart/:username", component: shoppingCart },
		  { path: "/search_restaurant", component: search_restaurant },
			{ path: "/shoppingCart", component: shoppingCart },
			{ path: "/add_manager", component: add_manager }
		]
});

var app = new Vue({
	router,
	el: '#restaurants'
});
