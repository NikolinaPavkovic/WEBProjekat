const Restaurant = { template: '<edit-restaurant> </edit-restaurant>' }
const Restaurants = { template: '<restaurants> </restaurants>' }

const router = new VueRouter({
	mode: 'hash',
		routes : [
			{ path: '/', name: 'home', component: Restaurants},
			{ path: '' }
		]	
});

var app = new Vue({
	router,
	el: '#restaurants'
});