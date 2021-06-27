const restaurants = { template : '<restaurants> </restaurants>' }
const login = { template : '<login> </login>' }
const registration = { template : '<registration> </registration>' }

const router = new VueRouter({
	mode: 'hash',
		routes : [
			{ path: '/', name: 'home', component: restaurants},
			{ path: '/login', component: login},
			{ path: '/registration', component: registration}
		]	
});

var app = new Vue({
	router,
	el: '#restaurants'
});