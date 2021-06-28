Vue.component("registration", {
	data: function() {
		return {
			mode: "BROWSE",
			nameInput: '',
			surnameInput: '',
			usernameInput: '',
			roleInput: '',
			dateOfBirthInput: '',
			passwordInput: '',
			genderInput: ''
		}
	},
	template: `
		<div>
			<img src="logo1.png">
			<form >
				<label>Ime:</label>
				<input type="text" v-model="nameInput" name="name"/>
				
				<label>Prezime:</label>
				<input type="text" v-model="surnameInput" name="surname"/>
				
				<label>Pol:</label>
				<select v-model="genderInput">
					<option value="male">Muško</option>
					<option value="female">Žensko</option>
				</select>
				
				<label>Datum rođenja:</label>
				<input type="date" v-model="dateOfBirthInput" name="dateOfBirth"/>
				
				<label>Korisničko ime:</label>
				<input type="text" v-model="usernameInput" name="username" required />
				
				<label>Lozinka:</label>
				<input type="password" v-model="passwordInput" name="password" required />
				
				<input type="submit" v-on:click="register" value="Register"/>
			</form>
		</div>
	`,
	methods: {
		register : function() {
			event.preventDefault();
			let registrationParameters = {
				name: this.nameInput,
				surname: this.surnameInput,
				username: this.usernameInput,
				password: this.passwordInput,
				gender: this.genderInput,
				role: this.roleInput,
				dateOfBirth: this.dateOfBirthInput
			};
			axios
			.post('/rest/register', JSON.stringify(registrationParameters))
			.then(response => (router.push(`/`)));
		}
	}
});