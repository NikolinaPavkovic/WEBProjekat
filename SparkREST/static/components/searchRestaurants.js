function cyrilicToLatinic(string) {
    var cyrillic = 'А_Б_В_Г_Д_Ђ_Е_Ё_Ж_З_И_Й_Ј_К_Л_Љ_М_Н_Њ_О_П_Р_С_Т_Ћ_У_Ф_Х_Ц_Ч_Џ_Ш_Щ_Ъ_Ы_Ь_Э_Ю_Я_а_б_в_г_д_ђ_е_ё_ж_з_и_й_ј_к_л_љ_м_н_њ_о_п_р_с_т_ћ_у_ф_х_ц_ч_џ_ш_щ_ъ_ы_ь_э_ю_я'.split('_')
    var latin = 'A_B_V_G_D_Đ_E_Ë_Ž_Z_I_J_J_K_L_Lj_M_N_Nj_O_P_R_S_T_Ć_U_F_H_C_Č_Dž_Š_Ŝ_ʺ_Y_ʹ_È_Û_Â_a_b_v_g_d_đ_e_ë_ž_z_i_j_j_k_l_lj_m_n_nj_o_p_r_s_t_ć_u_f_h_c_č_dž_š_ŝ_ʺ_y_ʹ_è_û_â'.split('_')

    return string.split('').map(function(char) {
      var index = cyrillic.indexOf(char)
      if (!~index)
        return char
      return latin[index]
    }).join('')
  }

function removeFromArray(arr, value) {
  return arr.filter(function(ele) {
    return ele != value;
  });
}

Vue.component("search-restaurant", {
  data: function() {
    return {
      restaurantType: "",
      restaurantName: "",
      restaurantGrade: "",
      location: "",
      locations: null,
      locationSearch: "",
      restaurants: null,
      allLocations: null,
      autocompleteInstance: [],
      showResults: false,
      message: "",
      filters_show: false,
      filters: false,
      options: [],
      type: [],
      sort_show: false

    }
  },

  template: `

    <div>

      <div class="search-form">
        <div class="row">
          <div class="column">
            <input type="search" v-model="restaurantName" placeholder="Naziv restorana" name="name"/>
          </div>
          <div class="column">
            <input type="search" id="autocomplete-dataset" placeholder="Lokacija restorana" name="location"/>
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
              <option value="1"> 1 </option>
              <option value="2"> 2 </option>
              <option value="3"> 3 </option>
              <option value="4"> 4 </option>
              <option value="5"> 5 </option>
            </select>
          </div>
          <button v-on:click="searchRestaurant" class="search-button"> <img class="search-image" src="./images/search.png"> </button>
        </div> </br>

        <a href="#search_restaurant" @click="showFilters" style="margin-left: 41px;"> Filteri </a>
        <div v-bind:hidden="filters_show == false">

          <div v-bind:hidden="filters_show == false">
            <h1 class="filter-restaurants"> Tip restorana: </h1>
            <div class="byType">
            <label class="container"> Italian
              <input type="checkbox" value="italian" name="type" id="italian">
              <span class="checkmark"></span>
            </label>

            <label class="container"> Chinese
              <input type="checkbox" value="chinese" name="type" id="chinese">
              <span class="checkmark"></span>
            </label>

            <label class="container"> Vegan
              <input type="checkbox" value="vegan" name="type" id="vegan">
              <span class="checkmark"></span>
            </label>

            <label class="container"> Grill
              <input type="checkbox" value="grill" name="type" id="grill">
              <span class="checkmark"></span>
            </label>

            <label class="container"> Pizza
              <input type="checkbox" value="pizza" name="type" id="pizza">
              <span class="checkmark"></span>
            </label>

            <label class="container"> Mexican
              <input type="checkbox" value="mexican" name="type" id="mexican">
              <span class="checkmark"></span>
            </label>
            </div>
              <button class="submit" @click="filterRestaurants"> Pretrazi </button>
          </div>
        </div> </br> </br>

        <a href="#search_restaurant" @click="showSortingMethods" style="margin-left: 41px;"> Sortiraj </a>
        <div v-bind:hidden="sort_show==false">
          <h1 class="filter-restaurants"> Po nazivu: </h1>
          <div class="byType">
            <label class="container"> rastuce
              <input type="checkbox" value="rastuce" name="sortByName" id="sortByNameAsc">
              <span class="checkmark"></span>
            </label>

            <label class="container"> opadajuce
              <input type="checkbox" value="opadajuce" name="sortByName" id="sortByNameDesc">
              <span class="checkmark"></span>
            </label>

            <h1 class="sort-restaurants"> Po lokaciji: </h1>
            <label class="container"> rastuce
              <input type="checkbox" value="rastuce" name="sortByLoc" id="sortByLocAsc">
              <span class="checkmark"></span>
            </label>

            <label class="container"> opadajuce
              <input type="checkbox" value="opadajuce" name="sortByLoc" id="sortByLocDesc">
              <span class="checkmark"></span>
            </label>

            <h1 class="sort-restaurants"> Po oceni: </h1>
            <label class="container"> rastuce
              <input type="checkbox" value="rastuce" name="sortByGrade" id="sortByGradeAsc">
              <span class="checkmark"></span>
            </label>

            <label class="container"> opadajuce
              <input type="checkbox" value="opadajuce" name="sortByGrade" id="sortByGradeDesc">
              <span class="checkmark"></span>
            </label>
            <div>
              <button class="submit" @click="sortRestaurants"> Sortiraj </button>
            </div>
          </div>
        </div>
      </div>

      <p class="search-message"> {{message}} </p>
          <div class="row-restaurants" v-for="r in restaurants" v-bind:hidden="showResults==false">
            <div class="col-with-pic"> </br>
              <div class="col-picture">
                <div>
                  <img :src="r.imgPath" class="restaurant-image" alt="r.name"> </br> </br>
                </div>
                <button class="see-more"><a :href="'#/details?name=' + r.name" class="link" > Pregledaj restoran </a> </button>
              </div>
            </div>

            <div class="col-information">
              <h1 class="restaurant-name"> {{r.name}} </h1>
              <h1 class="info"> {{r.location.address.address + ", " + r.location.address.city.city}} </h1>
              <h1 class="info"> Tip restorana: {{r.type}} </h1>
              <h1 class="info"> {{r.status}} </h1>
            </div>
          </div>

      <input type="text" class="hidden" id="city" hidden/>
      <input type="text" class="hidden" id="country" hidden/>

    </div>
  `,

  mounted() {

    var placesCountry = placesAutocompleteDataset({
      algoliasearch: algoliasearch,
      templates: {
        header: '<div class="ad-example-header"> Drzave </div>',
        footer: '<div class="ad-example_footer"/>'
      },
      hitsPerPage: 3,
      type: ["country"],
      getRankingInfo: true
    });

    var placesCity = placesAutocompleteDataset({
      algoliasearch: algoliasearch,
      templates: {
        header: '<div class="ad-example-header"> Gradovi </div>'
      },
      hitsPerPage: 3,
      type: ["city"],
      getRankingInfo: true
    });

    var autocompleteInstance = autocomplete(
      document.querySelector("#autocomplete-dataset"),
      {
        hint: false,
        debug: true,
        cssClasses: { prefix: "ad-example"}
      },
      [placesCountry, placesCity]
    );

    var autocompleteChangeEvents = ["selected", "close"];

    autocompleteChangeEvents.forEach(function(eventName) {
      autocompleteInstance.on("autocomplete:" + eventName, function (
        event,
        suggestion,
        datasetName
      ) {
        console.log(datasetName, suggestion);

        if(suggestion.type === 'city') {
          document.querySelector("#city").value = suggestion.name || '';
          document.querySelector("#country").value = suggestion.country || '';
        } else if (suggestion.type === 'country') {
          document.querySelector("#city").value = '';
          document.querySelector("#country").value = suggestion.value || '';
        }
        document.querySelector('#autocomplete-dataset').value = suggestion.value || '';
      });
    });

    document.querySelector("#autocomplete-dataset").on("change", evt => {
      document.querySelector('#autocomplete-dataset').value = e.suggestion.value || '';
    });
  },

  methods: {

      searchRestaurant: function() {
        this.locationSearch = cyrilicToLatinic(document.querySelector('#autocomplete-dataset').value);
        let city = cyrilicToLatinic(document.querySelector('#city').value);
        let country = cyrilicToLatinic(document.querySelector('#country').value);
        console.log(this.locationSearch);

        if (this.restaurantType === 'chinese') {
          this.restaurantType = "chinese";
        } else if (this.restaurantType === 'mexican') {
          this.restaurantType = "mexican";
        } else if (this.restaurantType === 'pizza') {
          this.restaurantType = "pizza";
        } else if (this.restaurantType === 'asian') {
          this.restaurantType = "asian";
        } else if (this.restaurantType === 'italian') {
          this.restaurantType = "italian";
        } else if (this.restaurantType === 'vegan') {
          this.restaurantType = "vegan";
        } else if (this.restaurantType === 'vegetarian') {
          this.restaurantType = "vegetarian";
        } else if (this.restaurantType === 'grill') {
          this.restaurantType = "grill";
        } else if (this.restaurantType === 'american') {
          this.restaurantType = "american";
        } else {
          this.restaurantType = "";
        }

        if (this.restaurantGrade === '1') {
          this.restaurantGrade = 1.0;
        } else if (this.restaurantGrade === '2') {
          this.restaurantGrade = 2.0;
        } else if (this.restaurantGrade === '3') {
          this.restaurantGrade = 3.0;
        } else if (this.restaurantGrade === '4') {
          this.restaurantGrade = 4.0;
        } else if (this.restaurantGrade === '5'){
          this.restaurantGrade = 5.0;
        } else {
          this.restaurantGrade = 0.0;
        }

        if (this.restaurantName == '') {
          this.restaurantName = "";
        }

        let searchParams = {
          restaurantName: this.restaurantName,
          city: city,
          location: this.locationSearch,
          country: country,
          grade: this.restaurantGrade,
          restaurantType: this.restaurantType
        }

        if (this.restaurantName == "") {
          axios
            .post("/rest/restaurants/findByGrade", JSON.stringify(searchParams))
            .then(response => {
              this.restaurants = response.data;
              if (response.data.length == 0) {
                this.restaurants = [];
                toast("Nema rezultata pretrage");
              }
            })
        }

        console.log(searchParams.dateFrom);

        axios
          .post("/rest/restaurants/findRestaurants", JSON.stringify(searchParams))
          .then(response => {
            this.restaurants = response.data;
            if (response.data.length == 0) {
              this.restaurants = [];
              //this.message = "Nema rezultata";
              toast("Nema rezultata pretrage");
            }
          })

        this.showResults = true;
      },

      showFilters: function() {
        this.filters_show = !this.filters_show;
      },

      showSortingMethods: function() {
        this.sort_show = !this.sort_show;
      },

      uncheckRadioType: function() {
        document.getElementById('open').checked = false;
        document.getElementById('closed').checked = false;
        this.restaurantStatus = "";
      },

      filterRestaurants: function() {

        if (document.getElementById('italian').checked == true) {
          this.type.push("italian");
        }
        if (document.getElementById('chinese').checked == true) {
          this.type.push("chinese");
        }
        if (document.getElementById('vegan').checked == true) {
          this.type.push("vegan");
        }
        if (document.getElementById('grill').checked == true) {
          this.type.push("grill");
        }
        if (document.getElementById('pizza').checked == true) {
          this.type.push("pizza");
        }
        if (document.getElementById('mexican').checked == true) {
          this.type.push("mexican");
        }

        for (let t of this.type) {
          if (document.getElementById(t).checked == false) {
            this.type = removeFromArray(this.type, t);
          }
        }

        console.log(this.type);
        this.restaurantStatus = "open";

        let filterParams = {
          type: this.type,
          status: this.restaurantStatus,
          restaurants: this.restaurants
        }

        axios
          .post("/rest/restaurants/filterRestaurants", JSON.stringify(filterParams))
          .then(response => {
            this.restaurants = response.data;
            if (response.data.length == 0) {
              this.restaurants = [];
              //this.message = "Nema rezultata";
              toast("Nema rezultata pretrage");
            }
          })

      },

      sortRestaurants: function() {

      }
  }


});
