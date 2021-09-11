Vue.component("add_manager", {
  data: function() {
    return {
      name: "",
      surname: "",
      username: "",
      password: "",
      role: "",
      dateOfBirth: "",
      gender: "",
      isBlocked: false,
      deleted: false,
      restaurant: [],
      emptyMessage: ''
    }
  },

  template:
  `
  <div>

  <form class="add-form">
    <label>Ime:</label>
    <input type="text" v-model="name" name="name" required/>

    <label>Prezime:</label>
    <input type="text" v-model="surname" name="surname" required/>

    <label>Datum rođenja:</label>
    <input type="date" v-model="dateOfBirth" name="dateOfBirth" required/>

    <label>Pol:</label>
    <select v-model="gender" required>
      <option value="male">Muško</option>
      <option value="female">Žensko</option>
    </select>

    <label>Korisničko ime:</label>
    <input type="text" v-model="username" name="username" required />
    
    <p style="color:red;text-transform:none;">{{emptyMessage}}</p>

    <label>Lozinka:</label>
    <input type="password" v-model="password" name="password" required />

    <input type="submit" v-on:click="add" value="Dodaj menadžera"/>
  </form>

  </div>
  `,

  methods: {

    add: function() {
	  event.preventDefault();
      this.role = "menadžer";

      if (this.gender == 'male') {
        this.gender = "male";
      } else if (this.gender == 'female') {
        this.gender = "female";
      }

      let managerParams = {
        username: this.username,
        password: this.password,
        name: this.name,
        surname: this.surname,
        gender: this.gender,
        dateOfBirth: this.dateOfBirth,
        role: this.role,
        isBlocked: this.isBlocked,
        deleted: this.deleted
      }

      axios
        .post('/rest/addNewManager', JSON.stringify(managerParams))
        .then(response => {
        	if(response.data == 'EMPTY') {
        		this.emptyMessage = "Morate popuniti sva polja!";
        	} else if(response.data == 'USERNAME EXISTS') {
        		this.emptyMessage = "Username je već u upotrebi!";
        	}
        	 else {
        		router.push(`/`);
        	}
        });
    }
  }

});
