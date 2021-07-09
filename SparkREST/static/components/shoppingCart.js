Vue.component("shopping_cart", {
	data: function() {
		return {
			shoppingCart: null,
			user: null,
			items: null,
			error: ""
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
	}
});