Vue.component("search-restaurant", {
  data: function() {
    return {
      restaurantType: ""

    }
  },

  template: `

    <div>
      <form class="form-inline">
        <div class="row">
          <div class="column">
            <input type="search" placeholder="Naziv restorana" name="name"/>
          </div>
          <div class="column">
            <input type="search" placeholder="Lokacija restorana" name="location"/>
          </div>
          <div class="column">
            <select v-model="restaurantType">
              <option value="" disabled selected> Tip </option>
              <option value="italian"> Italian </option>
              <option value="chinese"> Chinese </option>
              <option value="vegan"> Vegan </option>
              <option value="asian"> Asian </option>
              <option value="mexican"> Mexican </option>
              <option value="grill"> Grill </option>
              <option value="vegetarian"> Vegetarian </option>
              <option value="pizza"> Pizza </option>
              <option value="fastfood"> Fast food </option>
            </select>
          </div>
          <div class="column">
            <select v-model="restaurantGrade">
              <option value="" disabled selected> Izaberi ocenu </option>
            </select>
          </div>
          <div class="column">

          </div>
        </div>
      </form>
    </div>
  `,


});
