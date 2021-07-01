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
	  name: ''
    }
  },

  template: `

  <div>
    <img :src="restaurantImage" class="rest-image">

    <div class="all">
    <div class="wrapper-restaurant">
      <div class="left">
        <h4> {{restaurant.name}} </h4>
        <p> Ocena: </p>
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

  </div>

  `,

  mounted() {

    axios
      .get('/rest/restaurants/' + this.$route.query.name)
      .then(response => {
        this.restaurant = response.data;
		    this.name = response.data.name;
        this.restaurantImage = response.data.imgPath;
      });

  }

});
