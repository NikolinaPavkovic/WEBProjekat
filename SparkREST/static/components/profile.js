Vue.component("profile", {
	data: function() {
		return {
			user: null,
			editMode: false,
			nameInput: '',
			surnameInput: '',
			usernameInput: '',
			passwordInput: ''
			
		}
	}, 
	template: `
	<div>
		<div class="all" v-bind:hidden="editMode==true">
			<button style="position: absolute; top: 10px; right: 170px;" v-on:click="changeMode">Izmeni profil</button>
			<div class="wrapper">
				<div class="left">
					<h4>{{user.name}} {{user.surname}}</h4>
					<p>{{user.role}}</p>
				</div>
				<div class="right">
					<div class="info">
						<h3>Informacije</h3>
						<div class="info_data">
							<div class="data">
								<h4>Korisničko ime</h4>
								<p>{{user.username}}</p>
							</div>
							<div class="data">
								<h4>Pol</h4>
								<p>{{user.gender}}</p>
							</div>
							<div class="data">
								<h4>Datum rođenja</h4>
								<p>{{user.dateOfBirth}}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div v-bind:hidden="editMode==false">
			<img src="./images/logo1.png">
			<form >
				<label>Ime:</label>
				<input type="text" v-model="nameInput" name="name" required/>
				
				<label>Prezime:</label>
				<input type="text" v-model="surnameInput" name="surname" required/>
				
				<label>Korisničko ime:</label>
				<input type="text" v-model="usernameInput" name="username" required />
				
				<label>Lozinka:</label>
				<input type="password" v-model="passwordInput" name="password" required />
				
				<input type="submit" v-on:click="editProfile" value="Potvrdi"/>
				<button class="cancel" v-on:click="cancel">Odustani</button>
			</form>
		</div>
	</div>
	`,
	mounted() {
		axios
			.get('/rest/isLogged')
			.then(response => {
				this.user = response.data;
				this.nameInput= this.user.name;
				this.surnameInput= this.user.surname;
				this.usernameInput= this.user.username;
				this.passwordInput= this.user.password;
				
			});
			
	},
	methods: {
		changeMode: function() {
			this.editMode = true;
		},
		editProfile: function() {
			event.preventDefault();
			let editUserParameters = {
				name: this.nameInput,
				surname: this.surnameInput,
				username: this.usernameInput,
				password: this.passwordInput,
				gender: this.user.gender,
				role: this.user.role,
				dateOfBirth: this.user.dateOfBirth
			};
			
			axios
				.post('/rest/editProfile', JSON.stringify(editUserParameters));
			
			this.editMode = false;
		},
		cancel: function() {
			editMode = false;
		}
	}
});