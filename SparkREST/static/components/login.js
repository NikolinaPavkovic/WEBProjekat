Vue.component("login", {
	data: function() {
		return {
			mode: "BROWSE",
			usernameInput: '',
			passwordInput: '',
			errorMessage: ''
		}
	},
	template: `
		<div>
			<img alt="" src="./images/logo1.png">
			<h1 style="color: #99CCFF; text-align: center; font-size: 50"></h1>
			<form class="add-form">
				<label>Korisničko ime:</label>
				<input type="text" v-model="usernameInput" name="username" required/>

				<label>Lozinka:</label>
				<input type="password" v-model="passwordInput" name="password" required/>

				<input type="submit" v-on:click="tryLogin" value="Log In"/>

				<p style="color:red;text-transform:none;">{{errorMessage}}</p>
			</form>

			<div class="mapouter"><div class="gmap_canvas"><iframe width="600" height="500" id="gmap_canvas" src="https://maps.google.com/maps?q=2880%20Broadway,%20New%20York&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><a href="https://fmovies2.org">fmoivies</a><br><style>.mapouter{position:relative;text-align:right;height:500px;width:600px;}</style><a href="https://www.embedgooglemap.net">embedgooglemap.net</a><style>.gmap_canvas {overflow:hidden;background:none!important;height:500px;width:600px;}</style></div></div>
		</div>
	`,
	methods: {
		tryLogin : function() {
			event.preventDefault();
			let loginParameters = {
				username: this.usernameInput,
				password: this.passwordInput
			};

			axios
				.post('/rest/login', JSON.stringify(loginParameters))
				.then(response => {
					if(response.data == "") {
						this.errorMessage = "Uneli ste pogrešno korisničko ime ili lozinku!";
					} else {
						this.mode = "LoggedIn";
						router.push(`/`);
					}
				});
		}
	}
});
