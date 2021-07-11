Vue.component("restaurant_info", {

  data: function() {
    return {
      restaurantType: '',
      restaurantImage: "",
      restaurant: null,
      address: "",
      city: "",
      state: "",
      restaurantName: "",
	    name: '',
	    mode: "notLogged",
      items: null,
      comments: null,
      avgGrade: "",
      selectedItem: null,
      amount: 0
    }
  },

  template: `

  <div>
    <img :src="restaurantImage" class="rest-image">
	<button v-if="mode=='customer'" style="position: absolute; top: 10px; right: 40px;" v-on:click="viewShoppingCart">Korpa</button>
    <div class="all">
    <div class="wrapper-restaurant">
      <div class="left">
        <h4> {{restaurant.name}} </h4>
        <p> Ocena: {{avgGrade}} </p>
      </div>

      <div class ="right">
        <div class="info">
          <h3> Informacije </h3>
          <div class="info_data">
            <div class="data">
              <h4> Adresa: </h4>
              <p> {{restaurant.location.address.address + ", " + restaurant.location.address.city.city}} </p>
            </div>
            <div class="data">
              <h4> Tip restorana: </h4>
              <p> {{restaurant.type}} </p>
            </div>
            <div class="data">
              <h4> Status: </h4>
              <p> {{restaurant.status}} </p>
            </div>
            </div>
        </div>
      </div>
    </div>
  </div>

  </br> </br> </br> </br> </br> </br> </br> </br> </br>
  <button v-if="mode=='manager'" v-on:click="addItem"> Dodaj artikal </button>

  <div class="row-items" v-for="(i, index) in items">
    <div class="col-with-pic"> </br>
      <div class="col-picture">
        <div>
          <img :src="i.imagePath" class="restaurant-image" alt="i.name"> </br> </br>
        </div>
      </div>
    </div>

    <div class="col-information">
      <h1 class="item-name"> {{i.name}} </h1>
      <h1 class="description"> {{i.description}} </h1>
      <h1 class="price"> {{i.price}},00 RSD </h1>
    </div>
    <div>
    	</br></br></br></br></br>
    	<span>
	    	<button v-if="mode=='customer'" v-on:click="increment(index)" >+</button>
	    	<label v-if="mode=='customer'" id="index">0</label>
	    	<button v-if="mode=='customer'" v-on:click="decrement(index)">-</button>
	    	<button v-if="mode=='customer'" class="see-more" v-on:click="addToCart(i, index)"> Dodaj u korpu </button>
    	</span>
    </div>
  </div>

  </div>
  `,

  mounted() {

    axios
      .get('/rest/restaurants/' + this.$route.query.name)
      .then(response => {
        this.restaurant = response.data;
		    this.name = response.data.name;
        this.restaurantImage = response.data.imgPath;
        this.items = response.data.items;
      });

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

    axios
      .get('/rest/getComments')
      .then(response => {
        this.comments = response.data;
        let cnt = 0;
        let avg = 0;
        let sum = 0;
        for (let comment of this.comments) {
          if (comment.restaurant.name == this.name) {
            sum += comment.grade;
            cnt ++;
          }
        }
        avg = sum/cnt;
        this.avgGrade = avg;
      });

  },

  methods: {

		addItem: function() {
			router.push(`/add_item`);
		},
		addToCart: function(item, index) {
			var amountInput = document.getElementById("index").innerHTML;
			let itemParameters = {
				name : item.name,
				price : item.price,
				type : item.type,
				restaurant : item.restaurant,
				amount : amountInput,
				description : item.description,
				imagePath : item.imagePath
			};
			axios
				.post('/rest/addToCart', JSON.stringify(itemParameters));
			document.getElementById("index").innerHTML = '0';
		},
		increment: function(index) {
			var amount = 0;
			amount = parseFloat(document.getElementById("index").innerHTML);
			amount += 1;
			document.getElementById("index").innerHTML = amount;
		},
		decrement: function(index) {
			var amount = 0;
			amount = parseFloat(document.getElementById("index").innerHTML);
			if(amount > 0) {
				amount -= 1;
				document.getElementById("index").innerHTML = amount;
			}
		},
		viewShoppingCart: function() {
			axios
				.get('/rest/setShoppingCart');
			router.push(`/shoppingCart`);
		}
  }

});
