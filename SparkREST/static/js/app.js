const restaurants = { template : '<restaurants> </restaurants>' }
const login = { template : '<login> </login>' }
const registration = { template : '<registration> </registration>' }
const addEmployee = { template: '<addEmployee> </addEmployee>'}

const router = new VueRouter({
	mode: 'hash',
		routes : [
			{ path: '/', name: 'home', component: restaurants},
			{ path: '/login', component: login},
			{ path: '/registration', component: registration},
			{ path: '/addEmployee', component: addEmployee}
		]	
});

var app = new Vue({
	router,
	el: '#restaurants'
});