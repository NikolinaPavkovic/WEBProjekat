Vue.component("manager_order_list", {
	data: function() {
		return {
			orders: null
		}
	},
	template: `
		<div>
		  <button style="position: absolute; top: 10px; right: 40px;" v-on:click="viewRequests">Zahtevi od dostavljača</button>
		  <div class="row-items" v-for="(o, index) in orders">
		    <div class="col-with-pic"> </br>
	          <div class="col-picture">
	            <div>
	          <img :src="o.restLogo" class="restaurant-image"> </br> </br>
	            </div>
	          </div>
	        </div>
		    <div class="col-information">
		      <h1 class="item-name"> {{o.restaurant}}</h1>
		      <h1 class="price"> {{o.price}},00 RSD </h1>
		      <h1 class="price"> {{o.status}} </h1>
		      <div v-for="(i, index) in o.orderedItems" >
		  		 <label>&nbsp; &nbsp; &nbsp; &nbsp;{{i.name}} x{{i.amount}}</label>
		  	  </div>
		    </div>
		    <div>
	    		</br></br></br>
	    		<p v-bind:hidden="isVisible(o)"> Promeni status porudžbine: </p>
	    		<button v-bind:hidden="isDisabledPrepare(o)" v-on:click="changeStatusToPreparing(o)"> U pripremi </button>
	    		<button v-bind:hidden="isDisabledWait(o)" v-on:click="changeStatusToWaiting(o)"> Čeka dostavljača </button>
	    	</div>
		  </div>	  
		 </div>
	`,
	mounted() {
		axios
			.get('/rest/getManagerOrders')
			.then(response => {
				this.orders = response.data;
			});
		
	},
	methods: {
		isDisabledPrepare: function(order) {
			if(order.status != 'processing') {
				return true;
			} else {
				return false;
			}
		},
		isDisabledWait: function(order) {
			if(order.status != 'preparing') {
				return true;
			} else {
				return false;
			}
		},
		isVisible: function(order) {
			if(order.status == 'processing' || order.status == 'preparing') {
				return false;
			} else {
				return true;
			}
		},
		changeStatusToPreparing: function(order) {
			axios
				.post('/rest/changeStatusToPreparing', order.id)
				.then(response => (router.push(`/`)));
		},
		changeStatusToWaiting: function(order) {
			axios
				.post('/rest/changeStatusToWaiting', order.id)
				.then(response => (router.push(`/`)));
		},
		viewRequests: function() {
		}
	}
});