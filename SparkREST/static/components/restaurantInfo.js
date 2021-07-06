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
	    mode: "",
      items: null,
      comments: null,
      avgGrade: ""
    }
  },

  template: `

  <div>
    <img :src="restaurantImage" class="rest-image">

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
		}
  }

});
