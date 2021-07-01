Vue.component("restaurant_info", {

  data: function() {
    return {
      name: '',
      restaurantType: '',
      restaurantImage: "",
      restaurant: null,
      address: "",
      city: "",
      state: ""
    }
  },

  template: `

  <div class="restaurant-info">
    <h1> HEJ </h1>
    <div class="restaurant-details">

      <div class="image-column">
        <div>
          <img :src="restaurantImage" class="restaurant-image">
        </div>

      </div>

    </div>

  </div>


  `,

  mounted() {

    axios
      .get("/restaurants" + this.$route.query.name)
      .then(response => {
        this.restaurant = response.data;
        this.restaurantImage = response.data.imgPath;
      });

  }

});
