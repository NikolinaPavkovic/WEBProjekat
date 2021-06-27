Vue.component("registration", {
	data: function() {
		return {
			mode: "BROWSE"
		}
	},
	template: `
		<div>
			<img src="logo1.png">
			<form>
				<label>Ime:</label>
				<input type="text" name="name"/>
				
				<label>Prezime:</label>
				<input type="text" name="surname"/>
				
				<label>Pol:</label>
				<select>
					<option value="male">Muško</option>
					<option value="female">Žensko</option>
				</select>
				
				<label>Datum rođenja:</label>
				<input type="date" name="dateOfBirth"/>
				
				<label>Korisničko ime:</label>
				<input type="text" name="username" required />
				
				<label>Lozinka:</label>
				<input type="password" name="password" required />
				
				<input type="submit" value="Register"/>
			</form>
		</div>
	`
});