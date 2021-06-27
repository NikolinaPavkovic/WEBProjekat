Vue.component("login", {
	data: function() {
		return {
			mode: "BROWSE"
		}
	},
	template: `
		<div>
			<img alt="" src="logo1.png">
			<h1 style="color: #99CCFF; text-align: center; font-size: 50"></h1>
			<form action="post" @submit="">
				<label>Korisničko ime:</label>
				<input type="text" name="username" required/>
				<label>Lozinka:</label>
				<input type="password" name="password" required/>
				<input type="submit" value="Log In"/>
			</form>
		</div>
	`
});