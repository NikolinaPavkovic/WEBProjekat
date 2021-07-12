Vue.component("shopping_cart", {
	data: function() {
		return {
			shoppingCart: null,
			user: null,
			items: null,
			error: "",
			nesto: ""
		}
	},
	template: `
	<div>
	  <div class="row-items" v-for="(i, index) in items">
	    <div class="col-with-pic"> </br>
	      <div class="col-picture">
	        <div>
	          <img :src="i.imagePath" class="restaurant-image" alt="i.name"> </br> </br>
	        </div>
	      </div>
	    </div>
	    <div class="col-information">
	      <h1 class="item-name"> {{i.name}} x{{i.amount}}</h1>
	      <h1 class="description"> {{i.description}} </h1>
	      <h1 class="price"> {{i.price}},00 RSD </h1>
	    </div>
	    <div>
    		</br></br></br></br></br>
    		<span>
    			<button class="delete-button" v-on:click="removeFromCart(i)"> Izbaci iz korpe </button>
    			<p>{{nesto}}</p>
    		</span>
    	</div>
	  </div>
	  <button style="position: absolute; right: 40px;">Potvrdi porudžbinu</button>
	 </div>
	`,
	mounted() {
		axios
			.get('/rest/isLogged')
			.then(response => {
				this.user = response.data;	
			});
			
		axios
			.get('/rest/getCustomer')
			.then(response => {
				if(response.data === "ERROR") {
					this.error = "no customer";
				} else {
					this.shoppingCart = response.data.shoppingCart;
					this.items = this.shoppingCart.items;
				}
			});
	},
	methods: {
		removeFromCart: function(i) {
			let itemParameters = {
				name : i.name,
				price : i.price,
				type : i.type,
				restaurant : i.restaurant,
				amount : i.amount,
				description : i.description,
				imagePath : i.imagePath
			};
		
			axios
				.delete('/rest/deleteCustomer/' + i.name)
				.then(response => (this.$router.go()));
			
				
		}
	}
});